(ns votann.tab_widget
  (:require [votann.list-widget :refer [list-view-widget]]
            [votann.codex :refer [kin-models]]
            [votann.unit-widget :refer [unit-view-widget]]
            [votann.enhancements-widget :refer [enhancements-view-widget]]
            [votann.codex-view :refer [codex-page]]
            [votann.battle-simulator-widget :refer [battle-simulator-widget]]
            [cljfx.api :as fx]
            [cljfx.ext.web-view :as fx.ext.web-view]))

(defn list-view-tab [data]
  [{:fx/type :tab
    :text "List"
    :closable false
    :content (list-view-widget data)}])

(def unit-view-tab
  (vec (for [model kin-models]
         {:fx/type :tab
          :text (:name model)
          :closable false
          :content (unit-view-widget model)})))

(def enhancements-view-tab
  [{:fx/type :tab
    :text "Enhancements"
    :closable false
    :content enhancements-view-widget}])

(def codex-view-tab
  [{:fx/type :tab
    :text "Codex"
    :closable false
    :content {:fx/type :tab-pane
              :tabs unit-view-tab}}])

(defn battle-simulator-view-tab [data]
  [{:fx/type :tab
    :text "Battle Simulator"
    :closable false
    :content (battle-simulator-widget data)}])

(defn tab-widget [data]
  (vec (apply concat [(list-view-tab (:list data)) codex-view-tab (battle-simulator-view-tab (:battle-simulator data))])))
