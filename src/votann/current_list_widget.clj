(ns votann.current-list-widget
  (:require [cljfx.api :as fx]))

(defn current-list [data]
  (vec (for [[index unit] (map-indexed vector data)]
         {:fx/type :v-box
          :padding 5
          :children [{:fx/type :label
                      :style "-fx-font-size: 16px; -fx-font-weight: bold;"
                      :text (:name unit)}
                     {:fx/type :h-box
                      :alignment :center-left
                      :spacing 5
                      :children [{:fx/type :button
                                  :on-mouse-clicked {:event/type :event/remove-unit-click
                                                     :event/target {:index index
                                                                    :points (:points unit)}}
                                  :on-key-pressed {:event/type :event/remove-unit-enter
                                                     :event/target {:index index
                                                                    :points (:points unit)}}
                                  :text "Remove"}
                                 {:fx/type :label
                                  :style "-fx-font-size: 16px;"
                                  :text (str (:points unit) "pts")}]}]})))

(defn current-list-label-widget [{:keys [points]}]
  {:fx/type :v-box
   :alignment :center
   :children [
              {:fx/type :label
               :style "-fx-font-size: 18px; -fx-font-weight: bold;"
               :text "Current List"}
              {:fx/type :h-box
               :alignment :center
               :spacing 5
               :children [{:fx/type :label
                           :style "-fx-font-size: 18px; -fx-font-weight: bold;"
                           :text (str points " pts")}
                          {:fx/type :button
                           :on-mouse-clicked {:event/type :event/export-list-click}
                           :on-key-pressed {:event/type :event/export-list-enter}
                           :text "Export"}]}]})

(defn current-list-widget [{:keys [units]}]
  {:fx/type :scroll-pane
   :pref-height 860
   :pref-width 300
   :content {:fx/type :v-box
             :alignment :top-center
             :children (current-list units)}})
