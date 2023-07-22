(ns votann.util
  (:require [votann.codex :refer [kin-models kin-bodyguards]]
            [cljfx.lifecycle :as fx.lifecycle]
            [cljfx.component :as fx.component]
            [clojure.math :as math]
            [clojure.string :as string]))

(defn get-model [name]
  (->>
   (filter #(= (:name %) name) (apply conj kin-models kin-bodyguards))
   first))

(defn total [collection]
  (apply + collection))

(defn mean [collection]
  (double (/ (total collection) (count collection))))

(defn median [collection]
  (let [c (count collection)
        m (- (int (math/ceil (double (/ c 2)))) 1)
        collection (vec (sort collection))]
    (if (= 0 (mod c 2))
      (get collection
           (int (math/ceil (double (/ (+ m (+ m 1)) 2)))))
      (get collection m))))

(defn get-resource-path [file]
  (.toExternalForm (clojure.java.io/resource file)))

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
