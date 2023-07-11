(ns votann.tab_widget
  (:require [votann.list-widget :refer [list-view-widget]]
            [votann.util :refer [get-models unit-file-name]]
            [votann.unit-widget :refer [unit-view-widget]]
            [votann.enhancements-widget :refer [enhancements-view-widget]]
            [cljfx.api :as fx]))

(defn list-view-tab [data]
  [{:fx/type :tab
    :text "List"
    :closable false
    :content (list-view-widget data)}])

(def unit-view-tab
  (vec (for [unit get-models]
         {:fx/type :tab
          :text unit
          :closable false
          :content (unit-view-widget (unit-file-name unit))})))

(def enhancements-view-tab
  [{:fx/type :tab
    :text "Enhancements"
    :closable false
    :content enhancements-view-widget}])

(defn tab-widget [data]
  (vec (apply concat [(list-view-tab data) unit-view-tab enhancements-view-tab])))
