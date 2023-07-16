(ns votann.tab_widget
  (:require [votann.list-widget :refer [list-view-widget]]
            [votann.util :refer [get-models unit-file-name]]
            [votann.unit-widget :refer [unit-view-widget]]
            [votann.enhancements-widget :refer [enhancements-view-widget]]
            [votann.codex-view :refer [codex-page]]
            [cljfx.api :as fx]
            [cljfx.ext.web-view :as fx.ext.web-view]))

(defn list-view-tab [data]
  [{:fx/type :tab
    :text "List"
    :closable false
    :content (list-view-widget data)}])

(def unit-view-tab
  (vec (for [unit get-models]
         {:fx/type :tab
          :text unit
          :closable false
          :content (unit-view-widget (unit-file-name unit))})))

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
              :tabs (vec (apply merge unit-view-tab enhancements-view-tab))}}])

(def battle-simulator-view-tab
  [{:fx/type :tab
    :text "Battle Simulator"
    :closable false
    :content {:fx/type fx.ext.web-view/with-engine-props
              :props {:content codex-page}
              :desc {:fx/type :web-view}}}])

(defn tab-widget [data]
  (vec (apply concat [(list-view-tab data) battle-simulator-view-tab])))
