(ns votann.battle-simulator-widget
  (:require [votann.codex :refer [kin-models kin-bodyguards]]
            [votann.util :as util]
            [cljfx.api :as fx]))

(def models-widget-select
  {:fx/type :choice-box
   :on-action {:event/type :event/choice-select-model}
   :value (:name (first (apply conj kin-models kin-bodyguards)))
   :items (mapv #(:name %) (apply conj kin-models kin-bodyguards))})

(def models-widget-target
  {:fx/type :choice-box
   :on-action {:event/type :event/choice-select-target}
   :value (:name (first (apply conj kin-models kin-bodyguards)))
   :items (mapv #(:name %) (apply conj kin-models kin-bodyguards))})

(defn chart-data [data]
  (vec (for [d data]
         {:fx/type :xy-chart-data :x-value (:damage d) :y-value (:weapon d)})))

(defn damage-data [data type]
  (vec (for [[weapon damage]  data]
         (cond (= "Mean" type)
               {:fx/type :xy-chart-data :x-value (util/mean damage) :y-value weapon}

               (= "Median" type)
               {:fx/type :xy-chart-data :x-value (util/median damage) :y-value weapon}

               (= "Total" type)
               {:fx/type :xy-chart-data :x-value (util/total damage) :y-value weapon}))))

(defn battle-simulator-widget [{:keys [data total-damage data-type count weapon-type rolls target-count tokens disabled]}]
  {:fx/type :v-box
   :children [{:fx/type :h-box
               :alignment :center
               :padding 12
               :spacing 12
               :children [
                          {:fx/type :v-box
                           :children [
                                      {:fx/type :label
                                       :text "Weapon Type"}
                                      {:fx/type :choice-box
                                       :on-action {:event/type :event/choice-select-weapon}
                                       :value weapon-type
                                       :items ["Ranged"
                                               "Melee"
                                               ]}]}
                          {:fx/type :v-box
                           :children [
                                      {:fx/type :label
                                       :text "Model"}
                                      models-widget-select]}
                          {:fx/type :v-box
                           :children [
                                      {:fx/type :label
                                       :text "Model Count"}
                                      {:fx/type :text-field
                                       :on-key-typed {:event/type :event/choice-select-rolls}
                                       :max-width 80
                                       :text rolls}
                                      ]}
                          {:fx/type :v-box
                           :children [
                                      {:fx/type :label
                                       :text "Target"}
                                      models-widget-target]}
                          {:fx/type :v-box
                           :children [
                                      {:fx/type :label
                                       :text "Target Count"}
                                      {:fx/type :text-field
                                       :on-key-typed {:event/type :event/choice-select-target-size}
                                       :max-width 82
                                       :text target-count}
                                      ]}
                          {:fx/type :v-box
                           :children [
                                      {:fx/type :label
                                       :text "Judgment Tokens"}
                                      {:fx/type :choice-box
                                       :min-width 108
                                       :on-action {:event/type :event/choice-select-tokens}
                                       :value tokens
                                       :items ["0                  "
                                               "1                  "
                                               "2                  "]}]}
                          {:fx/type :v-box
                           :children [
                                      {:fx/type :label
                                       :text ""}
                                      {:fx/type :button
                                       :disable disabled
                                       :on-action {:event/type :event/choice-select-start}
                                       :text "Roll"}
                                      ]}
                          {:fx/type :v-box
                           :children [
                                      {:fx/type :label
                                       :text ""}
                                      {:fx/type :button
                                       :on-action {:event/type :event/choice-select-reset}
                                       :text "Reset"}
                                      ]}
                          ]}
              {:fx/type util/ext-recreate-on-key-changed
               :key data
               :desc
               {:fx/type :bar-chart
                :title (:dialog (first data))
                :legend-visible false
                :x-axis {:fx/type :number-axis :label "Damage"}
                :y-axis {:fx/type :category-axis}
                :data [{:fx/type :xy-chart-series
                        :data (chart-data data)}]}}

              {:fx/type :h-box
               :alignment :center
               :spacing 12
               :children [{:fx/type :label
                           :style "-fx-font-size: 18px;"
                           :text (str "Weapon damage after " count " rolls")}
                          {:fx/type :choice-box
                           :on-action {:event/type :event/choice-select-stats}
                           :value data-type
                           :items ["Total"
                                   "Mean"
                                   "Median"]}]
               }

              {:fx/type util/ext-recreate-on-key-changed
               :key total-damage
               :desc
               {:fx/type :bar-chart
                :legend-visible false
                :x-axis {:fx/type :number-axis :label "Damage"}
                :y-axis {:fx/type :category-axis}
                :data [{:fx/type :xy-chart-series
                        :data (damage-data total-damage data-type)}]}}]})
