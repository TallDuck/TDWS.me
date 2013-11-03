(defproject TDWS-me "0.1.0-SNAPSHOT"
  :description "TDWS.me URL Shortener"
  :url "http://tdws.me/"
  :license {:name "Creative Commons Attribution-ShareAlike 3.0 United States License"
            :url "http://creativecommons.org/licenses/by-sa/3.0/us/deed.en_US"}
  :source-paths ["src/clj" "src/cljs"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-1978"]
                 [ring "1.2.1"]
                 [compojure "1.1.6"]
                 [enlive "1.1.4"]]
  :plugins [[lein-cljsbuild "0.3.4"]]
  :cljsbuild {:builds
              [{:source-paths ["src/cljs"]
                :compiler {
                           :output-to "resources/static/js/main.js"
                           :optimizations :whitespace
                           :pretty-print true}}]}
  :main TDWS-me.app)
