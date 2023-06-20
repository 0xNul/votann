(ns votann.units-and-points-widget
  (:require [votann.util :refer [get-units]]
            [cljfx.api :as fx]))


(def units-points-label-widget
  {:fx/type :v-box
   :alignment :center
   :children [
              {:fx/type :label
               :style "-fx-font-size: 18px; -fx-font-weight: bold;"
               :text "Units & Points"}
              {:fx/type :h-box
               :alignment :center
               :spacing 100
               :children [{:fx/type :button
                           :on-mouse-clicked {:event/type :event/undo-unit-click}
                           :on-key-pressed {:event/type :event/undo-unit-enter}
                           :text "Undo"}
                          {:fx/type :button
                           :on-mouse-clicked {:event/type :event/restart-click}
                           :on-key-pressed {:event/type :event/restart-enter}
                           :text "Restart"}]}]})


(def unit-points-list-widget
  (vec (for [unit get-units]
         {:fx/type :v-box
          :padding 5
          :children [{:fx/type :label
                      :style "-fx-font-size: 16px; -fx-font-weight: bold;"
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
                                  :style "-fx-font-size: 16px;"
                                  :text (str (:points unit) " pts")}]}]})))

(def unit-and-points-widget
  {:fx/type :v-box
   :alignment :top-center
   :children [units-points-label-widget
              {:fx/type :scroll-pane
               :fit-to-width true
               :pref-width 300
               :content
               {:fx/type :v-box
                :alignment :center
                :children  unit-points-list-widget}}]})
