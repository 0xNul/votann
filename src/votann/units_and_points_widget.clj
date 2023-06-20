(ns votann.units-and-points-widget
  (:require [votann.util :refer [get-units]]
            [cljfx.api :as fx]))


(def units-points-label-widget
  {:fx/type :label
   :style "-fx-font-size: 18px; -fx-font-weight: bold;"
   :text "Units & Points"})

(def unit-points-list-widget
  (vec (for [unit get-units]
         {:fx/type :v-box
          :padding 5
          :children [{:fx/type :label
                      :text (:name unit)}
                     {:fx/type :h-box
                      :alignment :center-left
                      :spacing 5
                      :children [{:fx/type :button
                                  :on-mouse-clicked {:event/type :event/add-unit-click
                                                     :event/target {:name (:name unit)
                                                                    :points (:points unit)}}
                                  :on-key-pressed {:event/type :event/add-unit-enter
                                                   :event/target {:name (:name unit)
                                                                  :points (:points unit)}}
                                  :text "Add"}
                                 {:fx/type :label
                                  :text (str (:points unit) " pts")}]}]})))

(def unit-and-points-widget
  {:fx/type :scroll-pane
   :fit-to-width true
   :min-width 175
   :content
   {:fx/type :v-box
    :alignment :center
    :children (vec (apply merge [units-points-label-widget] unit-points-list-widget))}})
