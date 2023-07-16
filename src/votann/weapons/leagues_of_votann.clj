(ns votann.weapons.leagues-of-votann
  (:require [votann.weapon])
  (:import [votann.weapon Weapon]))

(def ancestral-ward-stave (Weapon. "Ancestral ward stave" ["Psychic"] 1 1 3 7 1 "D3"))
(def ancestral-wrath-focused-witchfire (Weapon. "Ancestral Wrath - focused witchfire" ["Hazardous" "Psychic"] 24 6 2 6 2 "D3"))
(def ancestral-wrath-witchfire (Weapon. "Ancestral Wrath - witchfire" ["Psychic"] 24 3 2 6 2 "D3"))
(def armoured-wheels-hekaton-land-fortress (Weapon. "Armoured wheels" [] 1 6 4 8 0 1))
(def armoured-wheels-sagitaur (Weapon. "Armoured wheels" [] 1 3 4 6 0 1))
(def autoch-pattern-bolter (Weapon. "Autoch-pattern bolter" [] 24 2 4 4 0 1))
(def autoch-pattern-bolt-pistol (Weapon. "Autoch-pattern bolter pistol" ["Pistol"] 12 1 3 4 0 1))
(def autoch-pattern-combi-bolter (Weapon. "Autoch-pattern combi-bolter" [] 24 4 2 4 0 1))
(def blade-of-the-ancestors (Weapon. "Blade of the Ancestors" ["Devastating Wounds"] 1 5 2 6 3 2))
(def bolt-cannon (Weapon. "Bolt cannon" ["Sustained Hits 1"] 36 3 4 6 1 2))
(def bolt-revolver (Weapon. "Bolt revolver" ["Pistol"] 9 1 4 5 0 1))
(def bolt-shotgun (Weapon. "Bolt shotgun" ["Assault"] 12 2 4 5 0 1))
(def close-combat-weapon (Weapon. "Close combat weapon" [] 1 1 4 3 0 1))
(def close-combat-weapon-thunderkyn (Weapon. "Close combat weapon" [] 1 2 4 4 0 1))
(def concussion-guantlet (Weapon. "Concussion guantlet" [] 1 2 3 9 2 2))
(def concussion-hammer (Weapon. "Concussion hammer" [] 1 3 4 9 1 3))
(def concussion-maul (Weapon. "Concussion maul" [] 1 3 4 9 2 3))
(def cyclic-ion-cannon (Weapon. "Cyclic ion cannon" ["Blast"] 24 "D6+3" 4 9 2 2))
(def darkstar-axe (Weapon. "Darkstar axe" [] 1 6 2 6 2 2))
(def etacarm-plasma-beamer (Weapon. "EtaCarm plasma beamer" ["Sustained Hits D3"] 18 1 4 8 3 2))
(def etacarm-plasma-gun (Weapon. "EtaCarm plasma gun" [] 24 1 3 8 3 2))
(def etacarm-plasma-pistol (Weapon. "EtaCarm plasma pistol" ["Pistol"] 6 1 4 8 3 2))
(def exo-armour-grenade-launcher (Weapon. "Exo armour grenade launcher" ["Blast"] 18 "D6" 3 4 0 1))
(def forgewrought-plasma-axe (Weapon. "Forgewrought plasma axe" [] 1 4 2 5 2 2))
(def graviton-blast-cannon (Weapon. "Graviton blast cannon" ["Anti-Vehicle 2+" "Blast"] 18 "D6" 4 5 2 2))
(def graviton-hammer (Weapon. "Graviton hammer" ["Anti-Vehicle 2+"] 1 3 4 9 1 3))
(def graviton-rifle (Weapon. "Graviton Rifle" ["Anti-Vehicle 2+"] 18 3 3 5 1 3))
(def heavy-magna-rail-cannon (Weapon. "Heavy magna-rail-cannon" ["Devastating Wounds" "Heavy"] 30 1 4 18 4 "D6+6"))
(def heavy-plasma-axe-strike (Weapon. "Heavy plasma axe - strike" [] 1 3 3 6 2 2))
(def heavy-plasma-axe-sweep (Weapon. "Heavy plasma axe - sweep" [] 1 6 3 4 1 1))
(def hekaton-warhead (Weapon. "Hekaton warhead" ["Blast" "Indirect Fire" "One Shot"] 36 "D6+3" 4 7 2 2))
(def hylas-auto-rifle (Weapon. "HYLas auto rifle" ["Assualt" "Rapid Fire 3"] 24 3 4 6 1 1))
(def hylas-beam-cannon (Weapon. "HYLas beam cannon" ["Sustained Hits 3"] 24 2 4 12 3 "D6"))
(def hylas-rotary-cannon-pioneers (Weapon. "HYLas rotary cannon" ["Sustained Hits 1"] 24 6 4 6 1 1))
(def hylas-rotary-cannon-warrior (Weapon. "HYLas rotary cannon" ["Heavy" "Sustained Hits 1"] 24 6 5 6 1 1))
(def ion-beamer (Weapon. "Ion beamer" ["Sustained Hits D3"] 18 3 4 7 2 1))
(def ion-blaster (Weapon. "Ion blaster" [] 18 1 4 5 2 1))
(def ion-pistol (Weapon. "Ion pistol" ["Pistol"] 12 1 4 5 2 1))
(def kin-melee-weapon (Weapon. "Kin melee weapon" [] 1 2 4 5 2 2))
(def l7-missile-launcher-blast (Weapon. "L7 missile launcher - blast" ["Blast"] 24 "D6" 4 4 0 1))
(def l7-missile-launcher-focused (Weapon. "L7 missile launcher - focused" [] 24 1 4 9 2 "D6"))
(def las-beam-cutter (Weapon. "Las-beam cutter" [] 6 1 4 6 3 1))
(def magna-coil-autocannon (Weapon. "Magna-coil autocannon" [] 24 3 4 7 1 2))
(def magna-rail-rifle (Weapon. "Magna-rail rifle" ["Devastating wounds" "Heavy"] 24 1 4 9 2 "D6"))
(def manipulator-arms (Weapon. "Manipulator arms" [] 1 3 4 3 0 1))
(def mass-guantlet (Weapon. "Mass guantlet" [] 1 3 3 8 2 3))
(def mass-hammer (Weapon. "Mass hammer" [] 1 3 3 12 2 "D6+1"))
(def matr-autocannon (Weapon. "MATR autocannon" [] 24 6 4 7 1 2))
(def matr-cannon (Weapon. "MATR cannon" [] 24 6 4 7 1 2))
(def mole-grenade-launcher (Weapon. "Mole grenade launcher" ["Blast" "Indirect Fire"] 24 "D6" 4 5 1 1))
(def plasma-blade-guantlet (Weapon. "Plasma blade guantlet" [] 1 3 3 6 2 1))
(def plasma-knife (Weapon. "Plasma knife" [] 1 2 4 4 0 1))
(def plasma-torch (Weapon. "Plasma torch" [] 1 1 4 6 3 2))
(def sagitaur-missile-launcher (Weapon. "Sagitaur missile launcher" [] 36 2 4 12 3 3))
(def sp-conversion-beamer (Weapon. "SP conversion beamer" ["Conversion" "Sustained Hits D3"] 24 1 4 7 1 3))
(def sp-heavy-conversion-beamer (Weapon. "SP heavy conversion beamer" ["Conversion" "Sustained Hits D3"] 24 2 4 10 2 4))
(def twin-bolt-cannon (Weapon. "Twin bolt cannon" ["Sustained Hits 1" "Twin-Linked"] 36 3 4 6 1 2))
(def twin-concussion-guantlet (Weapon. "Twin concussion gauntlet" ["Twin-Linked"] 1 4 4 9 1 2))
(def twin-ion-beamer (Weapon. "Twin ion beamer" ["Sustained Hits D3" "Twin-Linked"] 18 3 4 7 2 1))
(def volkanite-disintegrator-khal (Weapon. "Volkanite disintegrator" ["Devastating Wounds"] 18 3 2 5 0 1))
(def volkanite-disintegrator-hearthgurad (Weapon. "Volkanite disintegrator" ["Devastating Wounds"] 18 3 3 5 0 1))
