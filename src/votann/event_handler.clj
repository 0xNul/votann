(ns votann.event-handler
  (:require [votann.codex :refer [kin-models]]
            [votann.util :as util]
            [votann-battle-simulator.battle-round :refer [combat-phase]])
  (:import [javafx.scene.input KeyCode KeyEvent]))

(def *state (atom {:list
                   {:points 0
                    :units []}
                   :battle-simulator
                   {:data []
                    :total-damage {}
                    :data-type "Total"
                    :count "0"
                    :weapon-type "Ranged"
                    :model (:name (first kin-models))
                    :target (:name (first kin-models))
                    :rolls "1"
                    :target-count "1"
                    :tokens "0                  "
                    :disabled false}}))

;; TODO find a better place for this
(defn battle-modifiers [state]
  (let [judgment-count (clojure.string/trim (get-in @state [:battle-simulator :tokens]))]
    (cond
      (= "0" judgment-count)
      {:hit []
       :wound []}

      (= "1" judgment-count)
      {:hit ["Judgment Token"]
       :wound []}

      (= "2" judgment-count)
      {:hit ["Judgment Token"]
       :wound ["Judgment Token"]})))

;; TODO find a better place for
(defn total-damage [state]
  (doseq [data (get-in @state [:battle-simulator :data])]
    (if-not (nil? (get-in @state [:battle-simulator :total-damage (:weapon data)]))
      (swap! state assoc-in [:battle-simulator :total-damage (:weapon data)] (conj (get-in @state [:battle-simulator :total-damage (:weapon data)]) (:damage data)))
      (swap! state assoc-in [:battle-simulator :total-damage (:weapon data)] [(:damage data)]))))

(defn enter-event [function e]
  (if (= KeyCode/ENTER (.getCode ^KeyEvent (:fx/event e)))
    (function e)))

(defn undo-points [_]
  (if-not (empty? (get-in @*state [:list :units]))
    (let [points (:points (last (get-in @*state [:list :units])))]
      (swap! *state assoc-in [:list :points] (max 0 (- (get-in @*state [:list :points]) points)))
      (swap! *state update-in [:list :units] pop))))

(defn restart-list [_]
  (if-not (empty? (get-in @*state [:list :units]))
    (do
      (swap! *state assoc-in [:list :points] 0)
      (swap! *state assoc-in [:list :units] []))))

(defn add-unit [e]
  (swap! *state assoc-in [:list :points] (+ (get-in @*state [:list :points]) (:points (:event/target e))))
  (swap! *state update-in [:list :units] conj (:event/target e)))

(defn remove-unit [e]
  (do
    (swap! *state assoc-in [:list :points] (max 0 (- (get-in @*state [:list :points]) (:points (:event/target e)))))
    (swap! *state update-in [:list :units] #(vec (concat (subvec % 0 (:index (:event/target e))) (subvec % (inc (:index (:event/target e)))))))))

(defn export-list [_]
  (if-not (empty? (get-in @*state [:list :units]))
    (spit (str (.toString (java.time.Instant/now)) ".list") (:list @*state))))

(defn map-event-handler [e]
  (cond
    ;; list events
    (= :event/undo-unit-click (:event/type e))
    (undo-points e)

    (= :event/undo-unit-enter (:event/type e))
    (enter-event undo-points e)

    (= :event/restart-click (:event/type e))
    (restart-list e)

    (= :event/restart-enter (:event/type e))
    (enter-event restart-list e)

    (= :event/remove-unit-click (:event/type e))
    (remove-unit e)

    (= :event/remove-unit-enter (:event/type e))
    (enter-event remove-unit e)

    (= :event/add-unit-click (:event/type e))
    (add-unit e)

    (= :event/add-unit-enter (:event/type e))
    (enter-event add-unit e)

    (= :event/export-list-click (:event/type e))
    (export-list e)

    (= :event/export-list-enter (:event/type e))
    (enter-event export-list e)

    ;;battle-simulator events
    (= :event/choice-select-weapon (:event/type e))
    (swap! *state assoc-in [:battle-simulator :weapon-type] (.getValue (.getTarget (:fx/event e))))

    (= :event/choice-select-model (:event/type e))
    (do
      (swap! *state assoc-in [:battle-simulator :model] (.getValue (.getTarget (:fx/event e))))
      (swap! *state assoc-in [:battle-simulator :count] "0")
      (swap! *state assoc-in [:battle-simulator :rolls] "1")
      (swap! *state assoc-in [:battle-simulator :data] [])
      (swap! *state assoc-in [:battle-simulator :total-damage] {}))

    (= :event/choice-select-reset (:event/type e))
    (do
      (swap! *state assoc-in [:battle-simulator :count] "0")
      (swap! *state assoc-in [:battle-simulator :data] [])
      (swap! *state assoc-in [:battle-simulator :total-damage] {}))

    (= :event/choice-select-target (:event/type e))
    (do
      (swap! *state assoc-in [:battle-simulator :target] (.getValue (.getTarget (:fx/event e))))
      (swap! *state assoc-in [:battle-simulator :count] "0")
      (swap! *state assoc-in [:battle-simulator :data] [])
      (swap! *state assoc-in [:battle-simulator :total-damage] {}))

    (= :event/choice-select-rolls (:event/type e))
    (try
      (let [roll (Integer/parseInt (.getText (.getTarget (:fx/event e))))]
        (if (< roll 1)
          (do
            (swap! *state assoc-in [:battle-simulator :rolls] "")
            (.setText (.getTarget (:fx/event e)) "")
            (swap! *state assoc-in [:battle-simulator :disabled] true))
          (do
            (swap! *state assoc-in [:battle-simulator :rolls] (str roll))
            (swap! *state assoc-in [:battle-simulator :disabled] false))))
      (catch java.lang.NumberFormatException | java.lang.NullPointerException e
             (swap! *state assoc-in [:battle-simulator :rolls] "")
             (.setText (.getTarget (:fx/event e)) "")
             (swap! *state assoc-in [:battle-simulator :disabled] true)))

    (= :event/choice-select-target-size (:event/type e))
    (try
      (let [count (Integer/parseInt (.getText (.getTarget (:fx/event e))))]
        (if (< count 1)
          (do
            (swap! *state assoc-in [:battle-simulator :target-count] "")
            (.setText (.getTarget (:fx/event e)) "")
            (swap! *state assoc-in [:battle-simulator :disabled] true))
          (do
            (swap! *state assoc-in [:battle-simulator :target-count] (str count))
            (swap! *state assoc-in [:battle-simulator :disabled] false))))
      (catch java.lang.NumberFormatException | java.lang.NullPointerException e
             (swap! *state assoc-in [:battle-simulator :target-count] "")
             (.setText (.getTarget (:fx/event e)) "")
             (swap! *state assoc-in [:battle-simulator :disabled] true)))

    (= :event/choice-select-tokens (:event/type e))
    (swap! *state assoc-in [:battle-simulator :tokens] (.getValue (.getTarget (:fx/event e))))

    (= :event/choice-select-stats (:event/type e))
    (swap! *state assoc-in [:battle-simulator :data-type] (.getValue (.getTarget (:fx/event e))))

    (= :event/choice-select-start (:event/type e))
    (cond (= "Ranged" (get-in @*state [:battle-simulator :weapon-type]))
          (do
            (swap! *state assoc-in [:battle-simulator :data]
                   (combat-phase "shooting"
                                 (Integer/parseInt (get-in @*state [:battle-simulator :rolls]))
                                 (util/get-model (get-in @*state [:battle-simulator :model]))
                                 (Integer/parseInt (get-in @*state [:battle-simulator :target-count]))
                                 (util/get-model (get-in @*state [:battle-simulator :target]))
                                 (battle-modifiers *state)))
            (swap! *state assoc-in [:battle-simulator :count] (str (+ (Integer/parseInt (get-in @*state [:battle-simulator :count])) 1)))
            (total-damage *state))

          (= "Melee" (get-in @*state [:battle-simulator :weapon-type]))
          (do
            (swap! *state assoc-in [:battle-simulator :data]
                   (combat-phase "fight"
                                 (Integer/parseInt (get-in @*state [:battle-simulator :rolls]))
                                 (util/get-model (get-in @*state [:battle-simulator :model]))
                                 (Integer/parseInt (get-in @*state [:battle-simulator :target-count]))
                                 (util/get-model (get-in @*state [:battle-simulator :target]))
                                 (battle-modifiers *state)))
            (swap! *state assoc-in [:battle-simulator :count] (str (+ (Integer/parseInt (get-in @*state [:battle-simulator :count])) 1)))
            (total-damage *state)))))
