(ns votann.model)

(defrecord Model [name m t sv w ld oc
                  points
                  ranged-weapons
                  melee-weapons
                  abilities
                  wargear-options
                  unit-composition
                  leader
                  keywords
                  quote])
