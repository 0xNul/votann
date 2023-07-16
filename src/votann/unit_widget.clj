(ns votann.unit-widget
  (:require [votann.codex-view :refer [codex-page]]
            [cljfx.api :as fx]
            [cljfx.ext.web-view :as fx.ext.web-view]))

(defn unit-view-widget [model]
  {:fx/type fx.ext.web-view/with-engine-props
   :props {:content (codex-page model)}
   :desc {:fx/type :web-view}})
