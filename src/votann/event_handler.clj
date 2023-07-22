(ns votann.event-handler
  (:require [votann.codex :refer [kin-models]])
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
  (cond (= :event/undo-unit-click (:event/type e))
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
        (enter-event export-list e)))
