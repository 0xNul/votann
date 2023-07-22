(ns votann.list-widget
  (:require [votann.point-chart-widget :refer [point-chart-widget]]
            [votann.units-and-points-widget :refer [unit-and-points-widget]]
            [votann.current-list-widget :refer [current-list-widget]]
            [cljfx.api :as fx]))

(defn list-view-widget [data]
  {:fx/type :h-box
   :style "-fx-background-color: #e1e0e1;"
   :alignment :top-center
   :children [(current-list-widget data) (point-chart-widget data) unit-and-points-widget]})
