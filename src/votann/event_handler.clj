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
                    :target-distance "2"
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

(defprotocol EventHandler
  (handle-event [this]))

(defrecord UndoUnitClick [e]
  EventHandler
  (handle-event [this]
    (undo-points e)))

(defrecord UndoUnitEnter [e]
  EventHandler
  (handle-event [this]
    (enter-event undo-points e)))

(defrecord RestartClick [e]
  EventHandler
  (handle-event [this]
    (restart-list e)))

(defrecord RestartEnter [e]
  EventHandler
  (handle-event [this]
    (restart-list e)))

(defrecord RestartUnitClick [e]
  EventHandler
  (handle-event [this]
    (remove-unit e)))

(defrecord RemoveUnitEnter [e]
  EventHandler
  (handle-event [this]
    (enter-event remove-unit e)))

(defrecord AddUnitClick [e]
  EventHandler
  (handle-event [this]
    (add-unit e)))

(defrecord AddUnitEnter [e]
  EventHandler
  (handle-event [this]
    (enter-event add-unit e)))

(defrecord ExportListClick [e]
  EventHandler
  (handle-event [this]
    (export-list e)))

(defrecord ExportListEnter [e]
  EventHandler
  (handle-event [this]
    (enter-event export-list e)))

(defrecord ChoiceSelectWeapon [e]
  EventHandler
  (handle-event [this]
    (swap! *state assoc-in [:battle-simulator :weapon-type] (.getValue (.getTarget (:fx/event e))))
    (if (= "Ranged" (get-in @*state [:battle-simulator :weapon-type]))
      (swap! *state assoc-in [:battle-simulator :target-distance] "2")
      (swap! *state assoc-in [:battle-simulator :target-distance] "1"))))

(defrecord ChoiceSelectModel [e]
  EventHandler
  (handle-event [this]
    (swap! *state assoc-in [:battle-simulator :model] (.getValue (.getTarget (:fx/event e))))
    (swap! *state assoc-in [:battle-simulator :count] "0")
    (swap! *state assoc-in [:battle-simulator :rolls] "1")
    (swap! *state assoc-in [:battle-simulator :data] [])
    (swap! *state assoc-in [:battle-simulator :total-damage] {})))

(defrecord ChoiceSelectReset [e]
  EventHandler
  (handle-event [this]
    (swap! *state assoc-in [:battle-simulator :count] "0")
    (swap! *state assoc-in [:battle-simulator :data] [])
    (swap! *state assoc-in [:battle-simulator :total-damage] {})))

(defrecord ChoiceSelectTarget [e]
  EventHandler
  (handle-event [this]
    (swap! *state assoc-in [:battle-simulator :target] (.getValue (.getTarget (:fx/event e))))
    (swap! *state assoc-in [:battle-simulator :count] "0")
    (swap! *state assoc-in [:battle-simulator :data] [])
    (swap! *state assoc-in [:battle-simulator :total-damage] {})))

(defrecord ChoiceSelectRolls [e]
  EventHandler
  (handle-event [this]
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
             (swap! *state assoc-in [:battle-simulator :disabled] true)))))

(defrecord ChoiceSelectSize [e]
  EventHandler
  (handle-event [this]
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
             (swap! *state assoc-in [:battle-simulator :disabled] true)))))

(defrecord ChoiceSelectTargetDistance [e]
  EventHandler
  (handle-event [this]
    (try
      (let [count (Integer/parseInt (.getText (.getTarget (:fx/event e))))]
        (if (< count 1)
          (do
            (swap! *state assoc-in [:battle-simulator :target-distance] "")
            (.setText (.getTarget (:fx/event e)) ""))
          (swap! *state assoc-in [:battle-simulator :target-distance] (str count))))
      (catch java.lang.NumberFormatException | java.lang.NullPointerException e
             (swap! *state assoc-in [:battle-simulator :target-distance] "")
             (.setText (.getTarget (:fx/event e)) "")))))

(defrecord ChoiceSelectTokens [e]
  EventHandler
  (handle-event [this]
    (swap! *state assoc-in [:battle-simulator :tokens] (.getValue (.getTarget (:fx/event e))))))

(defrecord ChoiceSelectStats [e]
  EventHandler
  (handle-event [this]
    (swap! *state assoc-in [:battle-simulator :data-type] (.getValue (.getTarget (:fx/event e))))))

(defrecord ChoiceSelectStart [e]
  EventHandler
  (handle-event [this]
    (cond (= "Ranged" (get-in @*state [:battle-simulator :weapon-type]))
          (do
            (swap! *state assoc-in [:battle-simulator :data]
                   (combat-phase :ranged-weapons
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
                   (combat-phase :melee-weapons
                                 (Integer/parseInt (get-in @*state [:battle-simulator :rolls]))
                                 (util/get-model (get-in @*state [:battle-simulator :model]))
                                 (Integer/parseInt (get-in @*state [:battle-simulator :target-count]))
                                 (util/get-model (get-in @*state [:battle-simulator :target]))
                                 (battle-modifiers *state)))
            (swap! *state assoc-in [:battle-simulator :count] (str (+ (Integer/parseInt (get-in @*state [:battle-simulator :count])) 1)))
            (total-damage *state)))))

(def event-handlers
  {:event/undo-unit-click (fn [e] (handle-event (UndoUnitClick. e)))
   :event/undo-unit-enter (fn [e] (handle-event (UndoUnitEnter. e)))
   :event/restart-click (fn [e] (handle-event (RestartClick. e)))
   :event/restart-enter (fn [e] (handle-event (RestartEnter. e)))
   :event/remove-unit-click (fn [e] (handle-event (RestartUnitClick. e)))
   :event/remove-unit-enter (fn [e] (handle-event (RemoveUnitEnter. e)))
   :event/add-unit-click (fn [e] (handle-event (AddUnitClick. e)))
   :event/add-unit-enter (fn [e] (handle-event (AddUnitEnter. e)))
   :event/export-list-click (fn [e] (handle-event (ExportListClick. e)))
   :event/export-list-enter (fn [e] (handle-event (ExportListEnter. e)))
   :event/choice-select-weapon (fn [e] (handle-event (ChoiceSelectWeapon. e)))
   :event/choice-select-model (fn [e] (handle-event (ChoiceSelectModel. e)))
   :event/choice-select-reset (fn [e] (handle-event (ChoiceSelectReset. e)))
   :event/choice-select-target (fn [e] (handle-event (ChoiceSelectTarget. e)))
   :event/choice-select-rolls (fn [e] (handle-event (ChoiceSelectRolls. e)))
   :event/choice-select-target-size (fn [e] (handle-event (ChoiceSelectSize. e)))
   :event/choice-select-target-distance (fn [e] (handle-event (ChoiceSelectTargetDistance. e)))
   :event/choice-select-tokens (fn [e] (handle-event (ChoiceSelectTokens. e)))
   :event/choice-select-stats (fn [e] (handle-event (ChoiceSelectStats. e)))
   :event/choice-select-start (fn [e] (handle-event (ChoiceSelectStart. e)))})

(defn map-event-handler [e]
  (let [event-type (:event/type e)
        event (event-type event-handlers)]
    (event e)))
