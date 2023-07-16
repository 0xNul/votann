(ns votann.models.leagues-of-votann
  (:require [votann.model]
            [votann.records.points]
            [votann.weapons.leagues-of-votann :as weapon])
  (:import [votann.model Model]
           [votann.records.points Points]))

(def uthar-the-destined (Model. "Ûthar the Destined" 5 5 3 5 7 1
                                (Points. "x1" 115)
                                [weapon/volkanite-disintegrator-khal]
                                [weapon/blade-of-the-ancestors]
                                {:core ["Leader"]
                                 :faction ["Eye of the Ancestors"]
                                 :other [{:name "Ancestral Fortune"
                                          :description "Once per turn, you can change one Hit roll, one Wound roll or one Damage roll made for this model to a 6"}
                                         {:name "The Destined"
                                          :description "Each time an attack is allocated to this model, change the Damage characteristic of that attack to 1."}
                                         {:name "Grim Efficiency"
                                          :description "Once per battle round, in your Command phase, you can select one model from your army with this ability, then select one enemy unit that is visibile to that model. That enemy unit gains 1 Judgement token."}]
                                 :wargear-abilities [{:name "Rampart Crest"
                                                      :description "While the bearer is leading a unit, models in that unit have a 5+ invulnerable save"}]
                                 :invulnerable-save 4}
                                []
                                {:composition ["1 Ûthar the Destined - Epic Hero"]
                                 :equipment ["volkanite disintegrator" "Blade of the Ancestors" "rampart crest"]}
                                ["Einhyr Hearthguard" "Hearthkyn Warriors"]
                                {:model ["Infantry" "Character" "Epic Hero" "Ûthar the Destined"]
                                 :faction ["Leagues of Votann"]}
                                "As the most accomplished hero of the Greater Thurian League, Kahl Ûthar the Destined is marked for greatness. Few can assess the foe as swiftly or mercilessly as Ûthar and - once he has his enemies' measure- he soon cuts them to pieces with the glowing Blade of the Ancestors."))
