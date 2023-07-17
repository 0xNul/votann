(ns votann.models.leagues-of-votann
  (:require [votann.records.model]
            [votann.records.points]
            [votann.weapons.leagues-of-votann :as weapon])
  (:import [votann.records.model Model]
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
                                []
                                {:model ["Infantry" "Character" "Epic Hero" "Ûthar the Destined"]
                                 :faction ["Leagues of Votann"]}
                                "As the most accomplished hero of the Greater Thurian League, Kahl Ûthar the Destined is marked for greatness. Few can assess the foe as swiftly or mercilessly as Ûthar and - once he has his enemies' measure- he soon cuts them to pieces with the glowing Blade of the Ancestors."))

(def khal (Model. "Khâl" 5 5 3 4 7 1
                  (Points. "x1" 90)
                  [weapon/autoch-pattern-combi-bolter weapon/volkanite-disintegrator-khal]
                  [weapon/forgewrought-plasma-axe weapon/mass-guantlet]
                  {:core ["Leader"]
                   :faction ["Eye of the Ancestors"]
                   :other [{:name "Kindered Hero"
                            :description "While this model is leading a unit, weapons equipped by a models in that unit have the [LETHAL HITS] ability."}
                           {:name "Grim Efficiency"
                            :description "Once per battle round, in your Command phase, you can select one model from your army with this ability, then select one enemy unit that is visibile to that model. That enemy unit gains 1 Judgement token."}]
                   :wargear-abilities [{:name "Rampart Crest"
                                        :description "While the bearer is leading a unit, models in that unit have a 5+ invulnerable save"}
                                       {:name "Teleport Crest"
                                        :description "While this model is leading a unit, models in that unit have the Deep Strike ability."}]
                   :invulnerable-save 4}
                  []
                  {:composition ["1 Khâl"]
                   :equipment ["Autoch-pattern combi-bolter" "forgewrought plasma axe" "rampart crest"]}
                  ["Einhyr Hearthguard" "Hearthkyn Warriors"]
                  []
                  {:model ["Infantry" "Character" "Khâl"]
                   :faction ["Leagues of Votann"]}
                  "Most Oathbands are led by a Khâl, whose strategic wisdom, determination and martial might are an inspiration to their warriors. Khâls are often equipped with especially powerful weapons and potent field or teleportation technologies, the better to lead the fight from the front and bring down the deadliest foes."))

(def einhyr-champion (Model. "Einhyr Champion" 5 6 2 5 7 1
                             (Points. "x1" 75)
                             [weapon/autoch-pattern-combi-bolter]
                             [weapon/mass-hammer weapon/darkstar-axe]
                             {:core ["Leader"]
                              :faction ["Eye of the Ancestors"]
                              :other [{:name "Exemplar of the Einhyr"
                                       :description "While this model is leading a unit, you can re-roll Charge rolls made for that unit."}{:name "Mass Driver Accelerators"
                                                                                                                                            :description "Each time this model ends a Charge move, you can select one enemy unit within Engagement Range of this unit and roll one D6: on a 2-5 that enemy suffers D3 mortal wounds; on a 6 that enemy unit suffers D3+3 mortal wounds."}]
                              :wargear-abilities [{:name "Teleport Crest"
                                                   :description "While this model is leading a unit, models in that unit have the Deep Strike ability."}
                                                  {:name "Weavefield Crest"
                                                   :description "The bearer has a 4+ invulnerable save."}]
                              :invulnerable-save nil}
                             ["This model's mass hammer can be replaced with 1 darkstar axe." "This model's weavefield crest can be replaced with 1 teleport crest."]
                             {:composition ["1 Einhyr Champion"]
                              :equipment ["Autoch-pattern combi-bolter" "mass hammer" "weavefield crest"]}
                             ["Einhyr Hearthguard"]
                             []
                             {:model ["Infantry" "Character" "Exo-Armour" "Einhyr Champion"]
                              :faction ["Leagues of Votann"]}                                                        "Einhyr Champions wear modified exo-armour fitted with mass drivers. Combined with their formidable close-quarters weaponry and bulky RAM shields, this wargear transforms them into living battering rams whose accelerated charge hits hard enough to smash clean through armoured fortress gates."))

(def corv (Model. "CORV" 5 5 4 1 6 1
                  []
                  [weapon/autoch-pattern-bolter]
                  [weapon/close-combat-weapon]
                  {}
                  []
                  {}
                  []
                  []
                  {}
                  ""))

(def grimnyr (Model. "Grimnyr" 5 5 4 4 6 1
                     (Points. "x3" 75)
                     [weapon/ancestral-wrath-witchfire weapon/ancestral-wrath-focused-witchfire]
                     [weapon/ancestral-ward-stave]
                     {:core ["Leader"]
                      :faction ["Eye of the Ancestors"]
                      :other [{:name "Fortify (Psychic)"
                               :description "While this model is leading a unit, improve the Toughness characteristic of the models in that unit by 1."}
                              {:name "Grimnyr's Regard"
                               :description "Once per battle, after this model's unit fails a Battle-shock test, this model can use this ability. If it does, that unit is no longer Battle-shocked."}]
                      :invulnerable-save 4}
                     []
                     {:composition ["1 Grimnyr" "2 CORVs*"]
                      :composition-notice ["If this unit's Grimny model is ever destroyed, all of this unit's remaining CORV's models are also destroyed."]
                      :equipment ["Ancestral Wrath" "ancestral ward stave" "Autoch-pattern bolter" "close combat weapon"]}
                     ["Hearthkyn Warriors"]
                     [corv]
                     {:all-models ["Infantry" "Grimnyr"]
                      :model ["Character" "Psyker"]
                      :faction ["Leagues of Votann"]}
                     "The Grimnyr - or Living Ancestors - are privy to the wisdom of the Votann, and as close as to priests as the secular Kin get. Equipped with barrier tech such as ward staves and energy-focused CORVs, they are able to rouse the fury of the immaterium against their foes."))

(def ironkin-assistant (Model. "Ironkin Assistant" 5 5 4 2 7 1
                               []
                               [weapon/las-beam-cutter]
                               [weapon/close-combat-weapon]
                               {}
                               []
                               {}
                               []
                               []
                               {}
                               ""))

(def e-cog (Model. "E-COG" 5 5 4 1 7 1
                   []
                   [weapon/autoch-pattern-bolt-pistol]
                   [weapon/close-combat-weapon weapon/plasma-torch weapon/manipulator-arms]
                   {}
                   []
                   {}
                   []
                   []
                   {}
                   ""))

(def brokyr-iron-master (Model. "Brôkhyr Iron-Master" 5 5 4 4 7 1
                                (Points. "x3" 95)
                                [weapon/graviton-rifle]
                                [weapon/graviton-hammer]
                                {:core ["Leader"]
                                 :faction ["Eye of the Ancestors"]
                                 :other [{:name "Multispectral Visor"
                                          :description "While this model is leading a unit each time a model in that unit makes a ranged attack, add 1 to its Hit roll."}
                                         {:name "Brôkhyr's Guild"
                                          :description "At the end of your Movement phase, this unit can repair one friendly LEAGUES OF VOTANN VEHICLE or EXO-FRAME model within 3\" of it. That model regains up to D3 lost wounds, or up to 3 lost wounds instead if this unit contains an Ironkyn Assistant. Each model can only be repaired once per turn."}]
                                 :invulnerable-save nil}
                                []
                                {:composition ["1 Brôkhyr Iron-master" "1 Ironkin Assistant*" "3 E-COGs*"]
                                 :composition-notice ["If this unit's Iron-master is ever destroyed, all of this unit's Ironkyn Assistant and all remaining E-COGs are also destroyed."]
                                 :equipment ["graviton rifle" "graviton hammer" "las-beam cutter" "close combat weapon" "Autoch-pattern bolt pistol" "close combat weapon" "plasma torch" "manipulator arms"]}
                                ["Hearthkyn Warriors" "Brôkhyr Thunderkyn"]
                                [e-cog ironkin-assistant]
                                {:all-models ["Infantry" "Brôkhyr Iron-master"]
                                 :model ["Character"]
                                 :faction ["Leagues of Votann"]}
                                "Iron-masters are the most accomplished Brôkhyrs of their Kindred. In battle, they take on the duty of maintaining damaged Kin war engines, often aided by Ironkin and COG repair crews. These verteran Brôkhyrs also bring their most powerful personal creations to war, taking satisfaction in unleashing them upon the foe."))

(def hearthkyn-warriors (Model. "Hearthkyn Warriors" 5 5 4 1 7 2
                                (Points. "x10" 135)
                                [weapon/autoch-pattern-bolt-pistol
                                 weapon/autoch-pattern-bolter
                                 weapon/etacarm-plasma-beamer
                                 weapon/etacarm-plasma-pistol
                                 weapon/hylas-auto-rifle
                                 weapon/hylas-rotary-cannon-warrior
                                 weapon/ion-blaster
                                 weapon/ion-pistol
                                 weapon/l7-missile-launcher-blast
                                 weapon/l7-missile-launcher-focused
                                 weapon/magna-rail-rifle]
                                [weapon/close-combat-weapon
                                 weapon/kin-melee-weapon]
                                {:faction ["Eye of the Ancestors"]
                                 :other [{:name "Luch Has. Need Keeps. Toil Earns"
                                          :description "At the end of your Command phase, if this unit is within range of an object marker you control, that object marker remains under your control, even if you have no models within range of it, until your opponent controls it at the start or end of any turn."}]
                                 :wargear-abilities [{:name "Pan Spectral Scanner"
                                                      :description "Ranged weapons equipped by models in the bearer's unit have the [IGNORES COVER] ability."}
                                                     {:name "Comms Array"
                                                      :description "Each time you target the bearer's unit with a Stratagem, roll one D6: on a 5+, you gain 1CP."}
                                                     {:name "Medipack"
                                                      :description "Models in the bearer's unit have the Feel No Pain 6+ ability."}
                                                     {:name "Weavefield Crest"
                                                      :description "The bearer has a 4+ invulnerable save."}]
                                 :invulnerable-save nil}
                                ["All models in this unit each have their Autoch-pattern bolter replaced with 1 ion blaster"]
                                {:composition ["1 Theyn" "9 Hearthkyn Warriors"]
                                 :equipment ["Autoch-pattern bolt pistol" "Autoch-pattern bolter" "close combat weapon" "weavefield crest"]}
                                []
                                []
                                {:model ["Infantry" "Battleline" "Grenades" "Hearthkyn Warriors"]
                                 :faction ["Leagues of Votann"]}
                                "Well-armoured, well-trained and equipped with an array of powerful weaponry, Hearthkyn Warriors form the backbone of most Oathbands. Led by their Theyns, they lay down hails of firepower, shrugging off the enemy's return volleys before storming in to shatter their wavering foes for good."))

(def einhyr-hearthguard (Model. "Einhyr Hearthguard" 5 5 3 5 7 1
                                (Points. "x5" 165)
                                [weapon/etacarm-plasma-gun
                                 weapon/exo-armour-grenade-launcher
                                 weapon/volkanite-disintegrator-hearthgurad]
                                [weapon/concussion-guantlet
                                 weapon/concussion-hammer
                                 weapon/plasma-blade-guantlet]
                                {:faction ["Eye of the Ancestors"]
                                 :other [{:name "Oathband Bodyguard"
                                          :description "While a CHARACTER is leading this unit, each time an attack targets this unit if the Strength characteristic of that attack is greater than this unit's Toughness characteristic, subtract 1 from the Wound roll."}]
                                 :wargear-abilities [{:name "Teleport Crest"
                                                      :description "While this model is leading a unit, models in that unit have the Deep Strike ability."}
                                                     {:name "Weavefield Crest"
                                                      :description "The bearer has a 4+ invulnerable save."}]
                                 :invulnerable-save nil}
                                ["All models in this unit can each have their EtaCarn plasma gun replaced with 1 volkanite disintegrator." "All models in this unit can each have their concussion guantlet replaced with 1 plasma blade gauntlet." "The Hesyr's concussion gauntlet or plasma blade gauntlet can be replaced with 1 concussion hammer" "The Hesyr's weavefield crest can be replaced with 1 teleport crest."]
                                {:composition ["1 Hesyr" "4-9 Hearthguard"]
                                 :equipment ["EtaCam plasma gun" "exo-armour grenade launcher" "concussion gauntlet" "weavefield crest"]}
                                []
                                []
                                {:model ["Infantry" "Exo-Armour" "Einhyr Hearthguard"]
                                 :faction ["Leagues of Votann"]}
                                "Clad in formidable exo-armour and equipped with a fearsome array of weaponry, Einhyr Hearthguard are a force to be reckoned with. Whether forming bodyguard around their Oathband's heroes or striking deep into the heart of enemy territory, they are unstoppable on the attack and immovable in defence."))

(def cthonian-beserks (Model. "Cthonian Beserks" 5 5 6 2 7 1
                              (Points. "x5" 135)
                              [weapon/mole-grenade-launcher]
                              [weapon/heavy-plasma-axe-strike
                               weapon/heavy-plasma-axe-sweep
                               weapon/concussion-maul
                               weapon/twin-concussion-guantlet]
                              {:core ["Feel No Pain 5+"]
                               :faction ["Eye of the Ancestors"]
                               :other [{:name "Cyberstimms"
                                        :description "Each time a model in this unit is destroyed by a melee attack, if that model has not fought this phase, roll one D6: on a 4+, do not remove it from play. The destroyed model can fight after the attacking model's unit has finished making its attacks, and is then removed from play."}
                                       {:name "Subterranean Explosives"
                                        :description "In your Shooting phase, after this unit has shot, select one enemy unit (excluding MONSTERS and VEHICLES) that was hit by one or more of this unit's mole grenade launchers this phase and roll one D6. On a 4+, until the end of your opponent's next turn, that enemy unit is shaken. While a unit is shaken, subtract 2\" from its Move characteristic and subtract 2 from Advance and Charge rolls made for it"}
                                       {:name "Designer's Note"
                                        :description "While a unit is shaken, place a Mole Grenade token next to that unit as a reminder."}]
                               :invulnerable-save nil}
                              ["All models in this unit can each have their heavy plasma axe replaced with 1 concussion maul." "For every 5 models in this unit, 1 model that is not equipped with a mole grenade launcher can have its heavy plasma axe or concussion maul replaced with 1 twin concussion gauntlet" "For every 5 models in this unit, 1 model that is not equipped with a twin concussion gauntlet can be equipped with 1 mole grenade launcher. If a model is equipped with a mole grenade launcher, add 1 to its Wounds and Attacks characteristics"]
                              {:composition ["5-10 Cthonian Beserks"]
                               :equipment ["heavy plasma axe"]}
                              []
                              []
                              {:model ["Infantry" "Cthonian Beserks"]
                               :faction ["Leagues of Votann"]}
                              "Cthonian Beserks are amongs the most heavily augmented and courageious Kin. Brandishing mining-tools-cum-weapons-of-war and unleashing explosives that tunnel through solid rock, the Beserks storm the enemy's strongpoints and break them open like an asterioid filled with seams of precious ore."))


(def hernkyn-pioneers (Model. "Hernkyn Pioneers" 12 6 4 3 7 2
                              (Points. "x3" 105)
                              [weapon/bolt-revolver
                               weapon/bolt-shotgun
                               weapon/magna-coil-autocannon
                               weapon/hylas-rotary-cannon-pioneers
                               weapon/ion-beamer]
                              [weapon/plasma-knife]
                              {:core ["Scouts 9\""]
                               :faction ["Eye of the Ancestors"]
                               :other [{:name "Outflanking Mag-Riders"
                                        :description "At the end of your opponent's turn, if this unit is within 6\" of any battlefield edge and is not within Engagement Range of any enemy units, you can remove this unit from the battlefield and place it into Strategic Reserves."}]
                               :wargear-abilities [{:name "Comms Array"
                                                    :description "Each time you target the bearer's unit with a Stratagem, roll one D6: on a 5+, you gain 1CP."}
                                                   {:name "Pan Spectral Scanner"
                                                    :description "Ranged weapons equipped by models in the bearer's unit have the [IGNORES COVER] ability."}
                                                   {:name "Rollbar Searchlight"
                                                    :description "Each time a model in the bearer's unit makes an attack that targets a unit that contains one or more models with the Stealth ability, add 1 to the Hit roll."}]
                               :invulnerable-save nil}
                              ["For every 3 models in the unit, 1 model can be equipped with one of the following (if a model is equipped with any of these weapons, add 1 to its Wounds characteristic)" "Up to 3 different models that are not equipped with either a HYLas rotary cannon or an ion beamer can each be equipped with 1 of the following (to a maximum of 1 of each per unit)"]
                              {:composition ["3-6 Hernkyn Pioneers"]
                               :equipment ["bolt revolver" "bolt shotgun" "magna-coil autocannon" "plasma knife"]}
                              []
                              []
                              {:model ["Mounted" "Grenades" "Fly" "Hernkyn Pioneers"]
                               :faction ["Leagues of Votann"]}
                              "Hernkyn Pioneers skim across alien worlds and scout enemey positions, riding on their magna-coil bikes. Fast, resilient, and possessed of formidable firepower, these far ranging bands of warriors often strike at the foe from unexpected quarters, or send back intelligence on enemy movements to their Oathban's Kahl."))

(def sagitaur (Model. "Sagitaur" 12 10 3 9 7 3
                      (Points. "x1" 120)
                      [weapon/hylas-beam-cannon
                       weapon/twin-bolt-cannon
                       weapon/l7-missile-launcher-blast
                       weapon/l7-missile-launcher-focused
                       weapon/matr-autocannon
                       weapon/sagitaur-missile-launcher]
                      [weapon/armoured-wheels-sagitaur]
                      {:core ["Deadly Demise 1" "Scouts 6\""]
                       :faction ["Eye of the Ancestors"]
                       :other [{:name "Blistering Advance"
                                :description "Units can disembark from this TRANSPORT after it has Advanced. Units that do s count as having made a Normal move that phase, and cannot declare a charge in the same turn, but can otherwise act normally in the remainder of the turn."}]
                       :invulnerable-save nil}
                      ["This model's HYLas beam cannon can be replaced with one of the following"]
                      {:composition ["1 Sagitaur"]
                       :equipment ["HYLas beam cannon" "twin bolt cannon" "armoured wheels"]}
                      []
                      []
                      {:model ["Vehicle" "Transport" "Dedicated Transport" "Sagitaur"]
                       :faction ["Leagues of Votann"]}
                      "Designed to survive the worst that hostile worlds and alien predators can throw at it, the Sagitaur is a rugged ATV ideally suited to scouting operations and switft, armoured offensives. Sagitaurs mount a remarkable amount of firepowr for their size, and can even knock out enemey battle tanks."))

(def brokyr-thunderkyn (Model. "Brôkhyr Thunderkyn" 5 6 3 3 6 1
                               (Points. "x3" 95)
                               [weapon/bolt-cannon
                                weapon/graviton-blast-cannon
                                weapon/sp-conversion-beamer]
                               [weapon/close-combat-weapon-thunderkyn]
                               {:faction ["Eye of the Ancestors"]
                                :other [{:name "Oathband Covering Fire"
                                         :description "Each time you target this unit with the Fire Overwatch Stratagem, hits are scored on unmodified Hit rolls of 5+ when resolving that Stratagem"}]
                                :invulnerable-save nil}
                               ["All models in this unit can each have their bolt cannon replaced with 1 graviton blast cannon." "All models in this unit can each have their bolt cannon replaced with 1 SP conversion beamer."]
                               {:composition ["3-6 Brôkhyr Thunderkyn"]
                                :equipment ["bolt cannon" "close combat weapon"]}
                               []
                               []
                               {:model ["Infantry" "Exo-Frame" "Brôkhyr Thunderkyn"]
                                :faction ["Leagues of Votann"]}
                               "When a Brôkhyr hooks into a powerful exo-frame, they become Thunderkyn. Adapted from void-rigs used to maintain Kin spacecraft, these armoured exoskeletons exchange repair tools for massive heavy weapons, which the Brôkhyr use to lay down withering covering fire for the Oathbands and to pick off enemey armour."))

(def hekaton-land-fortress (Model. "Hekaton Land Fortress" 10 12 2 16 7 5
                                   (Points. "x1" 245)
                                   [weapon/cyclic-ion-cannon
                                    weapon/matr-autocannon
                                    weapon/twin-bolt-cannon
                                    weapon/heavy-magna-rail-cannon
                                    weapon/hekaton-warhead
                                    weapon/sp-heavy-conversion-beamer
                                    weapon/twin-ion-beamer]
                                   [weapon/armoured-wheels-hekaton-land-fortress]
                                   {:core ["Deadly Demise D6"]
                                    :faction ["Eye of the Ancestors"]
                                    :other [{:name "Fire Support"
                                             :description "In your shooting phase, after this model has shot, select one enemy unit it scored one or more hits against this phase. Until the end of the phase each time a friendly model that is disembarked from this TRANSPORT this turn make an attack that targets that enemy unit, you can re-roll the Wound roll."}]
                                    :wargear-abilities [{:name "Pan Spectral Scanner"
                                                         :description "Ranged weapons equipped by models in the bearer's unit have the [IGNORES COVER] ability."}]
                                    :invulnerable-save nil
                                    :damaged {:name "1-5 Wounds Remaining"
                                              :description "While this model has 1-5 wounds remaining, each time this model makes an attack, subtract 1 from the Hit roll."}}
                                   ["This model can do one of the following" "This model's cyclic ion cannon can be replaced with one of the following" "This model's pan spectral scanner can be replaced with 1 Hekaton warhead."]
                                   {:composition ["1 Hekaton Land Fortress"]
                                     :equipment ["cyclic ion cannon" "MATR autocannon" "2 twin bolt cannons" "armoured wheels" "pan spectral scanner"]}
                                   []
                                   []
                                   {:model ["Vehicle" "Transport" "Hekaton Land Fortress"]
                                    :faction ["Leagues of Votann"]}
                                   "The iconic battle tank and armoured transport of the Leagues of Votann, the Hekaton Land Fortress is a venerable and well-respected as it is powerful. With a fearsome arsenal of heavy weaponry and tremendously resilient armour - and with the ability to bear bands of Kin solidery into battle - it is a versatile military asset."))
