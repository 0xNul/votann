(defproject votann "0.1.0"
  :description "Votann is an informational program for creating your unit lists for Warhammer 40k"
  :url "https://github.com/0xNul/votann"
  :license {:name "GPL-3.0-or-later"
            :url "https://www.gnu.org/licenses/gpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [cljfx "1.7.23"]
                 [org.openjfx/javafx-base "19"]]
  :main ^:skip-aot votann.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dcljfx.skip-javafx-initialization=true"]}})
