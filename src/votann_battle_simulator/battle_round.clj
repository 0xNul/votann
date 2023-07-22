(ns votann-battle-simulator.battle-round
  (:require [votann-battle-simulator.util :as util]
            [votann-battle-simulator.weapon-abilities :as weapon-abilities]
            [votann.records.model]
            [votann.records.weapon])
  (:import [votann.records.model Model]
           [votann.records.weapon Weapon]))

(defn resolve-hit-roll [rolls skill]
  (vec (filter #(>= % skill) rolls)))

(defn resolve-wound-roll [rolls strength toughness]
  (vec (cond (>= strength (* toughness 2))
             (filter #(>= % 2) rolls)

             (> strength toughness)
             (filter #(>= % 3) rolls)

             (= strength toughness)
             (filter #(>= % 4) rolls)

             (< strength toughness)
             (filter #(>= % 5) rolls)

             (<= strength (double (/ toughness 2)))
             (filter #(>= % 6) rolls))))

(defn resolve-saving-throw-roll [rolls ap save]
  (vec (filter #(< % (+ ap save)) rolls)))

(defn resolve-damage [^Integer unit-size ^Weapon weapon ^Integer target-size ^Model target battle-modifiers]
  (let [rolls (util/roll-d6 (weapon-abilities/resolve-attack-abilities weapon unit-size target-size))
        rolls (weapon-abilities/resolve-hit-dice-abilities weapon rolls)
        rolls (weapon-abilities/resolve-hit-modifier-abilities weapon (:hit battle-modifiers) rolls)
        hits (resolve-hit-roll rolls (:bs weapon))
        wounds (weapon-abilities/resolve-wound-dice-abilites weapon (util/roll-d6 (count hits)) (:s weapon) (:t target))
        mortal-wounds (count (filter #(= 99 %) wounds))
        wounds (vec (filter #(not= 99 %) wounds))
        wounds (weapon-abilities/resolve-wound-modifier-abilites weapon (:wound battle-modifiers) wounds)
        wounds (resolve-wound-roll (util/roll-d6 (count wounds)) (:s weapon) (:t target))
        non-saves (count (resolve-saving-throw-roll (util/roll-d6 (count wounds)) (:ap weapon) (:sv target)))
        damage (* (util/resolve-stat-count (:d weapon)) (+ mortal-wounds non-saves))]
    damage))

(defn command-phase [])

(defn movement-phase [])

(defn combat-phase [phase ^Integer unit-size ^Model model ^Integer target-size ^Model target battle-modifiers]
  (cond
    (= phase :ranged-weapons)
    (println (str "\n----------\nStarting shooting-phase"))
    (= phase :melee-weapons)
    (println (str "\n----------\nStarting fight-phase")))
  (println (str (:name model) " x" unit-size " target " (:name target)
                " W: " (:w target)
                " T: " (:t target)
                " SV: " (:sv target)))
  (for [weapon (phase model)]
    (do
      (println (str "\nUsing weapon: " (:name weapon)))
      (let [damage (resolve-damage unit-size weapon target-size target battle-modifiers)]
        (println (str "Total damage: " damage))
        {:dialog (str (:name model) " x" unit-size " target " (:name target)
                      " W: " (:w target)
                      " T: " (:t target)
                      " SV: " (:sv target))
         :weapon (:name weapon) :damage damage}))))
