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

(def kin-enhancements
  [{:name "A Long List"
    :points (Points. "x1" 15)}
   {:name "Apprasing Glare"
    :points (Points. "x1" 20)}
   {:name "Grim Demeanour"
    :points (Points. "x1" 20)}
   {:name "Wayfarers Grace"
    :points (Points. "x1" 20)}])
