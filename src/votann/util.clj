(ns votann.util
  (:require [votann.codex :refer [kin-models kin-enhancements]]
            [cljfx.lifecycle :as fx.lifecycle]
            [cljfx.component :as fx.component]
            [clojure.string :as string]))

(def get-models
  (vec (for [model kin-models]
         (:name model))))

(def get-units
    (->> (concat kin-models kin-enhancements)
       (map #(for [unit (:units %)]
               {:name (str (:name %) " " (:count unit))
                :points (:points unit)}))
       (apply concat)
       vec))

(defn get-resource-path [file]
  (.toExternalForm (clojure.java.io/resource file)))

(comment get-local-path)

(defn unit-file-name [unit]
  (string/lower-case (string/replace unit " " "-")))

(defn remove-by-index [data index]
  (vec (concat (subvec data 0 index)
               (subvec data (inc index)))))

(def ext-recreate-on-key-changed
  "Extension lifecycle that recreates its component when lifecycle's key is changed

  Supported keys:
  - `:key` (required) - a value that determines if returned component should be recreated
  - `:desc` (required) - a component description with additional lifecycle semantics"
  (reify fx.lifecycle/Lifecycle
    (create [_ {:keys [key desc]} opts]
      (with-meta {:key key
                  :child (fx.lifecycle/create fx.lifecycle/dynamic desc opts)}
                 {`fx.component/instance #(-> % :child fx.component/instance)}))
    (advance [this component {:keys [key desc] :as this-desc} opts]
      (if (= (:key component) key)
        (update component :child #(fx.lifecycle/advance fx.lifecycle/dynamic % desc opts))
        (do (fx.lifecycle/delete this component opts)
            (fx.lifecycle/create this this-desc opts))))
    (delete [_ component opts]
      (fx.lifecycle/delete fx.lifecycle/dynamic (:child component) opts))))
