(ns votann.point-chart-widget
  (:require [cljfx.api :as fx]
            [votann.util :refer [ext-recreate-on-key-changed]]))

(defn chart-data [units]
  (for [unit (set units)]
       {:fx/type :pie-chart-data
        :name (:name unit)
        :pie-value (* (:points unit) (count (filter #(= (:name %) (:name unit)) units)))}))

(defn point-chart-widget [{:keys [units]}]
  {:fx/type ext-recreate-on-key-changed
   :key units
   :desc {:fx/type :pie-chart
          :pref-height 600
          :title "Point Distribution"
          :data (chart-data units)}})
