(ns votann.enhancements-widget
  (:require [votann.util :refer [get-resource-path]]
            [cljfx.api :as fx]))

(def enhancements-view-widget
  {:fx/type :scroll-pane
   :content {:fx/type :h-box
             :children [{:fx/type :image-view
                         :fit-width 1233
                         :preserve-ratio true
                         :image (get-resource-path (str "leagues-of-votann/enhancements.png"))}]}})
