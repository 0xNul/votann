(ns votann.codex-view
  (:require [votann.util :as util]
            [clojure.string :as string]
            [hiccup2.core :as h]
            [hiccup.page :as page]))

(def stat-head
  [:tr
   [:th "M"]
   [:th "T"]
   [:th "SV"]
   [:th "W"]
   [:th "LD"]
   [:th "OC"]])

(def stat-head-empty
  [:tr
   [:th]
   [:th]
   [:th]
   [:th]
   [:th]
   [:th]])

(defn stat-body [m t sv w ld oc]
  [:tr
   [:td (str m "\"")]
   [:td t]
   [:td (str sv "+")]
   [:td w]
   [:td (str ld "+")]
   [:td oc]])

(defn stat-table [model]
   [:table
    stat-head
    (stat-body (:m model) (:t model) (:sv model) (:w model) (:ld model) (:oc model))])

(defn stat-table-bottom [model]
   [:table
    stat-head-empty
    (stat-body (:m model) (:t model) (:sv model) (:w model) (:ld model) (:oc model))])

(defn stat-model [name]
  [:div {:class "stat-name"} [:h3 name]])

(defn stat-model-bottom [name]
  [:div {:class "stat-name-bottom"} [:h3 name]])

(defn stats [model]
  [:stats
   (if (empty? (:bodyguards model))
     [:div {:class "stat-container"}
      (stat-table model)]
     (h/html
      [:div {:class "stat-container"}
       (stat-table model)
       (stat-model (:name model))]
      (map-indexed (fn [index model]
                     (if (= index 0)
                       [:div {:class "stat-container"}
                        (stat-table model)
                        (stat-model (:name model))]
                       [:div {:class "stat-container"}
                        (stat-table-bottom model)
                        (stat-model-bottom (:name model))])) (:bodyguards model))))])

(defn stats-two [model]
  [:stats-two
   [:div {:class "stat-container"}
    (stat-table model)
    (stat-model (:name model))]
   [:div {:class "stat-container"}
    (stat-table-bottom (first (:bodyguards model)))
    (stat-model-bottom (:name (first (:bodyguards model))))]])

(def ranged-head
  [:tr
   [:th]
   [:th {:class "text"} "Ranged Weapons"]
   [:th "Range"]
   [:th "A"]
   [:th "BS"]
   [:th "S"]
   [:th "AP"]
   [:th "D"]])

(def melee-head
  [:tr
   [:th]
   [:th {:class "text"} "Melee Weapons"]
   [:th "Range"]
   [:th "A"]
   [:th "BS"]
   [:th "S"]
   [:th "AP"]
   [:th "D"]])

(defn weapon-body [name abilities range a bs s ap d]
  [:tr
   [:td {:class "icon"}]
   [:td {:class "text"}
    name
    (if-not (empty? abilities)
      (h/html
       [:br]
       [:b (str "[" (string/join ", " abilities) "]")]))]
   [:td range]
   [:td a]
   [:td (str bs "+")]
   [:td s]
   [:td ap]
   [:td d]])

(defn weapons [ranged melee]
  [:div {:class "weapons"}
   (if-not (empty? ranged)
     [:table
      ranged-head
      (for [weapon ranged]
        (weapon-body (:name weapon)
                     (:abilities weapon)
                     (str (:range weapon) "\"")
                     (:a weapon)
                     (:bs weapon)
                     (:s weapon)
                     (if (= 0 (:ap weapon))
                       0
                       (str "-" (:ap weapon)))
                     (:d weapon)))])

   (if-not (empty? melee)
     [:table
      melee-head
      (for [weapon melee]
        (weapon-body (:name weapon)
                     (:abilities weapon)
                     "Melee"
                     (:a weapon)
                     (:bs weapon)
                     (:s weapon)
                     (if (= 0 (:ap weapon))
                       0
                       (str "-" (:ap weapon)))
                     (:d weapon)))])])

(defn column-1 [ranged-weapons melee-weapons]
  [:div {:id "column-1"}
   (weapons ranged-weapons melee-weapons)])

(defn abilities-head [header]
  [:tr
   [:th {:class "text"} header]])

(defn abilities-body [abilities]
  (h/html
   (if-not (empty? (:core abilities))
     [:tr
      [:td
       [:p "CORE: "
        [:b (string/join ", " (:core abilities))]]]])

   (if-not (empty? (:faction abilities))
     [:tr
      [:td
       [:p "FACTION: "
        [:b (string/join ", " (:faction abilities))]]]])

   (if-not (empty? (:other abilities))
     [:tr
      [:td
       (for [other (:other abilities)]
         [:p [:b (str (:name other) ": ")]
          (:description other)
          ])]])))

(defn abilities [abilities]
  [:table
   (abilities-head "Abilities")
   (abilities-body abilities)])

(defn wargear-abilities-body [abilities]
  [:tr
   [:td
    (for [ability abilities]
      [:p [:b (str (:name ability) ": ")]
       (:description ability)])]])

(defn wargear-abilities [abilities]
  (if-not (empty? abilities)
    [:table
     (abilities-head "Wargear Abilities")
     (wargear-abilities-body abilities)]))

(defn shield [value]
  [:div {:class ["shield-container"]}
   [:div {:class "shield-text"} (str value "+") ]
   [:div {:class "shield"}]])

(defn invulnerable-save [ability]
  (if-not (nil? ability)
    (h/html
     [:table
      (abilities-head "Invulnerable Save")]
     (shield ability))))

(defn damaged-body [ability]
  [:tr
   [:td
    [:p (:description ability)]]])

(defn damaged [ability]
  (if-not (empty? ability)
    (h/html
     [:table
      (abilities-head (str "Damaged: " (:name ability)))
      (damaged-body ability)])))

(defn column-2 [model]
  [:div {:id "column-2"}
   [:div {:class "abilities"}
    (abilities (:abilities model))
    (wargear-abilities (:wargear-abilities (:abilities model)))
    (invulnerable-save (:invulnerable-save (:abilities model)))
    (damaged (:damaged (:abilities model)))
    ]])

(defn keywords-model [keywords]
  [:div {:class "model"}
   [:span (str "KEYWORDS: " (string/join ", " keywords))]])

(defn keywords-faction [keywords]
  [:div {:class "faction"}
   [:div "FACTION KEYWORDS:"]
   [:span (string/join ", " keywords)]])

(defn keywords [keywords]
  [:div {:class "keywords"}
   (keywords-model (:model keywords))
   (keywords-faction (:faction keywords))])

(def star
  [:div {:class "star"} ""])

(defn top [model]
  [:top
   [:h1 (:name model)]
   (if (not= (count (:bodyguards model)) 1)
     (stats model)
     (stats-two model))])

(defn content [model]
  [:content
   (column-1 (-> (map (fn [model]
                        (:ranged-weapons model)) (:bodyguards model))
                 (concat (:ranged-weapons model))
                 (flatten)
                 (->>
                  (distinct)
                  (sort-by :name)))
             (-> (map (fn [model]
                        (:melee-weapons model)) (:bodyguards model))
                 (concat (:melee-weapons model))
                 (flatten)
                 (->>
                  (distinct)
                  (sort-by :name))))
   (column-2 model)])

(defn bottom [model]
  [:bottom
   (keywords (:keywords model))
   star])

(def head
  [:head
   [:link {:rel "stylesheet" :href (util/get-resource-path "css/style.css")}]])

(defn body [model]
  [:body
   (top model)
   (content model)
   (bottom model)])

(defn codex-page [model]
  (page/html5
   (h/html
    head
    (body model))))
