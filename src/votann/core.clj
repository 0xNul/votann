(ns votann.core
  (:gen-class)
  (:require [votann.util :refer [get-resource-path]]
            [votann.event-handler :refer [map-event-handler *state]]
            [votann.tab_widget :refer [tab-widget]]
            [cljfx.api :as fx]))

(defn root-view [data]
  {:fx/type :stage
   :max-width 1340
   :min-width 1340
   :max-height 1018
   :min-height 1018
   :on-close-request (fn [_] (System/exit 0))
   :icons [(get-resource-path (str "icons/256x256.png"))
           (get-resource-path (str "icons/48x48.png"))
           (get-resource-path (str "icons/32x32.png"))]
   :showing true
   :title "Votann"
   :scene {:fx/type :scene
           :root {:fx/type :stack-pane
                  :children [{:fx/type :tab-pane
                              :tabs (tab-widget data)}]}}})

(def renderer
  (fx/create-renderer
   :middleware (fx/wrap-map-desc assoc :fx/type root-view)
   :opts {:fx.opt/map-event-handler map-event-handler}))

(defn -main
  [& args]
  (fx/mount-renderer *state renderer))
