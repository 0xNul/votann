(ns votann.codex)

(defrecord Unit [count points])

(def kin-models
  [{:name "Uthar The Destined"
    :units [(Unit. "x1" 115)]}
   {:name "Kahl"
    :units [(Unit. "x1" 90)]}
   {:name "Einhyr Champion"
    :units [(Unit. "x1" 75)]}
   {:name "Grimnyr"
    :units [(Unit. "x3" 75)]}
   {:name "Brokhyr Iron-Master"
    :units [(Unit. "x3" 95)
            (Unit. "x6" 190)]}
   {:name "Hearthkyn Warriors"
    :units [(Unit. "x10" 135)]}
   {:name "Einhyr Hearthguard"
    :units [(Unit. "x5" 165)
            (Unit. "x10" 330)]}
   {:name "Cthonian Beserks"
    :units [(Unit. "x5" 135)
            (Unit. "x10" 270)]}
   {:name "Hernkyn Pioneers"
    :units [(Unit. "x3" 105)
            (Unit. "x6" 210)]}
   {:name "Sagitaur"
    :units [(Unit. "x1" 120)]}
   {:name "Brokhyr Thunderkyn"
    :units [(Unit. "x3" 95)
            (Unit. "x6" 190)]}
   {:name "Hekaton Land Fortress"
    :units [(Unit. "x1" 245)]}])

(def kin-enhancements
  [{:name "A Long List"
    :units [(Unit. "x1" 15)]}
   {:name "Apprasing Glare"
    :units [(Unit. "x1" 20)]}
   {:name "Grim Demeanour"
    :units [(Unit. "x1" 20)]}
   {:name "Wayfarers Grace"
    :units [(Unit. "x1" 20)]}])
