(ns votann-battle-simulator.util)

(defn roll-d6 [n]
  (loop [i 0
         rolls ()]
    (if (>= i n)
      (vec rolls)
      (recur (+ i 1) (conj rolls (+ 1 (rand-int 6)))))))

(defn roll-d3 [n]
  (mapv #(int (Math/ceil (double (/ % 2)))) (roll-d6 n)))

(defn d+ [n rolls]
  (vec (filter #(>= % n) rolls)))

(defn resolve-stat-count [stat]
  (if (int? stat)
    stat
    (let [add-num (re-find #"\+\d+" stat)
          num (if (nil? add-num)
                0
                (Integer/parseInt add-num))]
      (cond
        (re-find #"D6" stat)
        (+ num (first (roll-d6 1)))

        (re-find #"D3" stat)
        (+ num (first (roll-d3 1)))))))

(defn modifier-cap [modifier]
  (cond
    (< modifier 0) -1
    (> modifier 0) 1
    :else 0))

(defn dice-cap [dice]
  (cond
    (< dice 1) 1
    (> dice 6) 6
    :else dice))

(defn mod+1 [modifier]
  (+ modifier 1))

(defn mod-1 [modifier]
  (- modifier 1))
