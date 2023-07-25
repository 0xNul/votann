(ns votann-battle-simulator.weapon-abilities
  (:require [votann-battle-simulator.util :as util]
            [votann.records.weapon])
  (:import [votann.records.weapon Weapon]))

(defn blast
  ([target-size]
   (int (/ target-size 5)))
  ([dice unit-size target-size]
   (let [blast-added (+ dice (* unit-size (blast target-size)))]
     (println "Dice count before " dice " after " blast-added)
     blast-added)))

(defn resolve-anti [stat]
  (let [keyword (re-find #"Anti-(\w+)" stat)
        modifier (Integer/parseInt (re-find #"\d+" stat))]
    {:keyword (second keyword)
     :modifier modifier}))

(defn anti [dice type target-keywords]
  (let [resolved-type (resolve-anti type)]
    (if (contains? (set target-keywords) (:keyword resolved-type))
      (map (fn [roll]
             (if (>= roll (:modifier resolved-type))
               6
               roll)) dice)
      dice)))

(defn devastating-wounds [dice]
  (let [mortal-added (vec (map #(if (= 6 %)
                                  99
                                  %) dice))]
    (println "Hit rolls before " dice " after " mortal-added)
    mortal-added))

(defn twin-linked [dice strength toughness]
  (let [re-rolls (vec (cond (>= strength (* toughness 2))
                            (filter #(< % 2) dice)

                            (> strength toughness)
                            (filter #(< % 3) dice)

                            (= strength toughness)
                            (filter #(< % 4) dice)

                            (< strength toughness)
                            (filter #(< % 5) dice)

                            (<= strength (double (/ toughness 2)))
                            (filter #(< % 6) dice)))
        dice-rolls (vec (filter (complement (set re-rolls)) dice))
        twin-linked-rolls (apply conj dice-rolls (util/roll-d6 (count re-rolls)))]
    (println "Wounds before " dice " after " twin-linked-rolls)
    twin-linked-rolls))

(defn rapid-fire [dice modifier]
  (cond
    (re-find #"D\d" modifier)
    (let [rapid-fire-modifier (util/resolve-stat-count modifier)
          rapid-fire-added (+ dice rapid-fire-modifier)]
      (println "Dice count before " dice " after " rapid-fire-added)
      rapid-fire-added)
    :else
    (let [rapid-fire-added (+ dice (Integer/parseInt (re-find #"\d+" modifier)))]
      (println "Dice count before " dice " after " rapid-fire-added)
      rapid-fire-added)))

(defn sustained-hits [modifier dice]
  (let [critical (count (filter #(= 6 %) dice))]
    (cond
      (re-find #"D\d" modifier)
      (let [crit-added (apply conj dice
                              (vec (repeat (*
                                            critical
                                            (util/resolve-stat-count modifier))
                                           7)))]
        (println "Hit count before " dice " after " crit-added)
        crit-added)

      :else
      (let [crit-added (apply conj dice
                              (vec (repeat (*
                                            critical
                                            (Integer/parseInt (re-find #"\d+" modifier)))
                                           7)))]
        (println "Hit count after " dice " after " crit-added)
        crit-added)
        )))

(defn resolve-attack-abilities [^Weapon weapon unit-size target-size]
  (loop [abilities (:abilities weapon)
         dice (* unit-size (util/resolve-stat-count (:a weapon)))]
    (if (empty? abilities)
      dice
      (let [ability (first abilities)]
        (cond
          (not (nil? (re-find #"Blast" ability)))
          (do
            (println "Applied Blast attack ability for " (:name weapon))
            (recur (rest abilities)
                   (blast dice unit-size target-size)))

          (not (nil? (re-find #"Rapid Fire" ability)))
          (do
            (println "Applied Rapid fire attack ability  for " (:name weapon))
            (recur (rest abilities)
                   (rapid-fire dice ability)))

          :else (recur (rest abilities)
                       dice))))))

(defn resolve-hit-dice-abilities [^Weapon weapon dice]
  (loop [abilities (:abilities weapon)
         dice-rolls dice]
    (if (empty? abilities)
      dice-rolls
      (let [ability (first abilities)]
        (cond
          (not (nil? (re-find #"Sustained Hits" ability)))
          (do
            (println "Applied Sustained Hits hit ability for " (:name weapon))
            (recur (rest abilities)
                   (sustained-hits ability dice-rolls)))

          :else
          (recur (rest abilities)
                 dice-rolls))))))

(defn resolve-hit-modifier-abilities [^Weapon weapon modifier-abilities dice]
  (loop [abilities (apply conj (:abilities weapon) modifier-abilities)
         modifier 0]
    (if (empty? abilities)
      (let [modifier-added (vec (map #(if (or (= 1 %) (= 6 %))
                                        %
                                        (util/dice-cap (+ (util/modifier-cap modifier) %))) dice))]
        (println "Resolve hit Before " dice " after " modifier-added)
        modifier-added)
      (let [ability (first abilities)]
        (cond
          (not (nil? (re-find #"Heavy" ability)))
          (do
            (println "Applied Heavy hit modifier for " (:name weapon))
            (recur (rest abilities)
                   (util/mod+1 modifier)))

          (not (nil? (re-find #"Judgment Token" ability)))
          (do
            (println "Applied Judgment Token hit modifier for " (:name weapon))
            (recur (rest abilities)
                   (util/mod+1 modifier)))
          :else
          (recur (rest abilities)
                 modifier))))))

(defn resolve-wound-dice-abilites [^Weapon weapon dice strength toughness keywords]
  (loop [abilities (:abilities weapon)
         dice-rolls dice
         modifier 0]
    (if (empty? abilities)
      dice-rolls
      (let [ability (first abilities)]
        (cond
          (not (nil? (re-find #"Anti-" ability)))
          (do
            (println "Applied Anti wound ability for " (:name weapon))
            (recur (rest abilities)
                   (anti dice ability keywords)
                   modifier))

          (not (nil? (re-find #"Devastating Wounds" ability)))
          (do
            (println "Applied Devastating Wounds wound ability for " (:name weapon))
            (recur (rest abilities)
                   (devastating-wounds dice)
                   modifier))

          (not (nil? (re-find #"Twin-Linked" ability)))
          (do
            (println "Twin-Linked wound ability for " (:name weapon))
            (recur (rest abilities)
                   (twin-linked dice strength toughness)
                   modifier))

          :else
          (recur (rest abilities)
                 dice-rolls
                 modifier))))))

(defn resolve-wound-modifier-abilites [^Weapon weapon modifier-abilities dice]
  (loop [abilities (apply conj (:abilities weapon) modifier-abilities)
         modifier 0]
    (if (empty? abilities)
      (let [modifier-added (vec (map #(if (or (= 1 %) (= 6 %))
                                        %
                                        (util/dice-cap (+ (util/modifier-cap modifier) %))) dice))]
        (println "Resolve wound Before " dice " after " modifier-added)
        modifier-added)
      (let [ability (first abilities)]
        (cond
          (not (nil? (re-find #"Judgment Token" ability)))
          (do
            (println "Applied Judgment Token wound modifier for " (:name weapon))
            (recur (rest abilities)
                   (util/mod+1 modifier)))

          :else
          (recur (rest abilities)
                 modifier))))))
