(ns votann.unit-widget
  (:require [votann.util :refer [get-resource-path]]
            [cljfx.api :as fx]))

(defn unit-view-widget [unit-file-name]
  {:fx/type :scroll-pane
   :content {:fx/type :v-box
             :alignment :center
             :children [{:fx/type :image-view
                         :image (get-resource-path (str "leagues-of-votann/" unit-file-name "-front.png"))}
                        {:fx/type :image-view
                         :image (get-resource-path (str "leagues-of-votann/" unit-file-name "-back.png"))}]}})
