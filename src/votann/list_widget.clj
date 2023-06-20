(ns votann.list-widget
  (:require [votann.point-chart-widget :refer [point-chart-widget]]
            [votann.units-and-points-widget :refer [unit-and-points-widget]]
            [votann.current-list-widget :refer [current-list-label-widget current-list-widget]]
            [cljfx.api :as fx]))

(defn left-widget [data]
  {:fx/type :v-box
   :alignment :top-center
   :children [(current-list-label-widget data) (current-list-widget data)]})

(defn list-view-widget [data]
  {:fx/type :h-box
   :alignment :top-center
   :children [(left-widget data) (point-chart-widget data) unit-and-points-widget]})
