(ns votann.core
  (:gen-class)
  (:require [votann.util :refer [get-resource-path]]
            [votann.event-handler :refer [map-event-handler *list-units]]
            [votann.tab_widget :refer [tab-widget]]
            [cljfx.api :as fx]))

(defn root-view [data]
  {:fx/type :stage
   :max-width 1253
   :max-height 860
   :on-close-request (fn [_] (System/exit 0))
   :icons [(get-resource-path (str "leagues-of-votann/icon256x256.png"))
           (get-resource-path (str "leagues-of-votann/icon48x48.png"))
           (get-resource-path (str "leagues-of-votann/icon32x32.png"))]
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
  (fx/mount-renderer *list-units renderer))
