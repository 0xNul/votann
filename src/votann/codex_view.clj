(ns votann.codex-view
  (:require [hiccup2.core :as h]
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

(def stat-body
  [:tr
   [:td "5\""]
   [:td "5"]
   [:td "3+"]
   [:td "5"]
   [:td "7+"]
   [:td "1"]])

(def stat-table
   [:table
    stat-head
    stat-body])

(def stat-table-bottom
   [:table
    stat-head-empty
    stat-body])

(defn stat-model [name]
  [:div {:class "stat-name"} [:h3 name]])

(defn stat-model-bottom [name]
  [:div {:class "stat-name-bottom"} [:h3 name]])

(def stats
  [:stats
   [:div {:class "stat-container"}
    stat-table
    (stat-model "Brokhyr Iron-Master")]
   [:div {:class "stat-container"}
    stat-table
    (stat-model "Kahl")]
   [:div {:class "stat-container"}
    stat-table-bottom
    (stat-model-bottom "CORVs")]
   [:div {:class "stat-container"}
    stat-table-bottom
    (stat-model-bottom "XX")]
   ])

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

(def ranged-body
  [:tr
   [:td {:class "icon"}]
   [:td {:class "text"} "Autoch-pattern bolt pistol"]
   [:td "12\""]
   [:td "1"]
   [:td "3+"]
   [:td "4"]
   [:td "0"]
   [:td "1"]])

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

(def melee-body
  [:tr
   [:td {:class "icon"}]
   [:td {:class "text"} "Autoch-pattern bolt pistol"]
   [:td "Melee"]
   [:td "1"]
   [:td "3+"]
   [:td "4"]
   [:td "0"]
   [:td "1"]])

(def weapons
  [:div {:class "weapons"}
   [:table
    ranged-head
    ranged-body]
   [:table
    melee-head
    melee-body]])

(def column-1
  [:div {:id "column-1"}
   weapons])

(def abilities-head
  [:tr
   [:th {:class "text"} "Abilities"]])

(def abilities-body
  [:tr
   [:td "CORE: Leader"]])

(def abilities
  [:div {:class "abilities"}
   [:table
    abilities-head
    abilities-body]])

(def column-2
  [:div {:id "column-2"}
   abilities])

(def keywords-model
  [:div {:class "model"}
   [:span "Keywords - All Models: Infanctry, Brokhyr Iron-Master | Brokhyr Iron-Master Model: Character"]])

(def keywords-faction
  [:div {:class "faction"}
   [:div "Faction Keywords:"]
   [:span "Leagues of Votann"]])

(def keywords
  [:div {:class "keywords"}
   keywords-model
   keywords-faction])

(def star
  [:div {:class "star"} ""])

(def top
  [:top
   [:h1 "Uthar the Destined"]
   stats])

(def content
  [:content
   column-1
   column-2])

(def bottom
  [:bottom
   keywords
   star])

(def head
  [:head
   [:link {:rel "stylesheet" :href (votann.util/get-resource-path "css/style.css")}]])

(def body
  [:body
   top
   content
   bottom])

(def codex-page
  (page/html5
   (h/html
    head
    body)))
