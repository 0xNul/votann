(ns votann.codex
  (:require [votann.models.leagues-of-votann :as lov]
            [votann.records.points])
  (:import [votann.records.points Points]))

(def kin-models
  [lov/uthar-the-destined
   lov/khal
   lov/einhyr-champion
   lov/grimnyr
   lov/brokyr-iron-master
   lov/hearthkyn-warriors
   lov/einhyr-hearthguard
   lov/cthonian-beserks
   lov/hernkyn-pioneers
   lov/sagitaur
   lov/brokyr-thunderkyn
   lov/hekaton-land-fortress])

;; {:name "Uthar The Destined"
;;     :units [(Unit. "x1" 115)]}
;;    {:name "Kahl"
;;     :units [(Unit. "x1" 90)]}
;;    {:name "Einhyr Champion"
;;     :units [(Unit. "x1" 75)]}
;;    {:name "Grimnyr"
;;     :units [(Unit. "x3" 75)]}
;;    {:name "Brokhyr Iron-Master"
;;     :units [(Unit. "x3" 95)]}
;;    {:name "Hearthkyn Warriors"
;;     :units [(Unit. "x10" 135)]}
;;    {:name "Einhyr Hearthguard"
;;     :units [(Unit. "x5" 165)]}
;;    {:name "Cthonian Beserks"
;;     :units [(Unit. "x5" 135)]}
;;    {:name "Hernkyn Pioneers"
;;     :units [(Unit. "x3" 105)]}
;;    {:name "Sagitaur"
;;     :units [(Unit. "x1" 120)]}
;;    {:name "Brokhyr Thunderkyn"
;;     :units [(Unit. "x3" 95)]}
;;    {:name "Hekaton Land Fortress"
;;     :units [(Unit. "x1" 245)]}

(def kin-enhancements
  [{:name "A Long List"
    :units [(Points. "x1" 15)]}
   {:name "Apprasing Glare"
    :units [(Points. "x1" 20)]}
   {:name "Grim Demeanour"
    :units [(Points. "x1" 20)]}
   {:name "Wayfarers Grace"
    :units [(Points. "x1" 20)]}])
