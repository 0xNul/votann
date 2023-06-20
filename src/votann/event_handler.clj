(ns votann.event-handler
  (:import [javafx.scene.input KeyCode KeyEvent]))

(def *list-units (atom {:points 0 :units []}))

(defn enter-event [function e]
  (if (= KeyCode/ENTER (.getCode ^KeyEvent (:fx/event e)))
    (function e)))

(defn undo-points [_]
  (if-not (empty? (get-in @*list-units [:units]))
    (let [points (:points (last (:units @*list-units)))]
      (swap! *list-units assoc :points (max 0 (- (:points @*list-units) points)))
      (swap! *list-units update-in [:units] pop))))

(defn restart-list [_]
  (if-not (empty? (get-in @*list-units [:units]))
    (do
      (swap! *list-units assoc :points 0)
      (swap! *list-units assoc-in [:units] []))))

(defn add-unit [e]
  (swap! *list-units assoc :points (+ (:points @*list-units) (:points (:event/target e))))
  (swap! *list-units update-in [:units] conj (:event/target e)))

(defn remove-unit [e]
  (do
    (swap! *list-units assoc :points (max 0 (- (:points @*list-units) (:points (:event/target e)))))
    (swap! *list-units update :units #(vec (concat (subvec % 0 (:index (:event/target e))) (subvec % (inc (:index (:event/target e)))))))))

(defn export-list [_]
  (if-not (empty? (get-in @*list-units [:units]))
    (spit (str (.toString (java.time.Instant/now)) ".list") @*list-units)))

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
        (enter-event export-list e)
        ))
