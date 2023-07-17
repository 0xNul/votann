(ns votann.enhancements-widget
  (:require [votann.util :refer [get-resource-path]]
            [cljfx.api :as fx]))

(def enhancements-view-widget
  {:fx/type :scroll-pane
   :content {:fx/type :h-box
             :children [{:fx/type :label
                         :text "coming soon (TM)"}]}})
