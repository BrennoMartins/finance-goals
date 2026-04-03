(defproject finance-goals "0.1.0-SNAPSHOT"
  :description "A REST API in Clojure for managing financial objectives (macro goals)"
  :url "https://github.com/your-name/finance-goals"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [cheshire "5.12.0"]
                 [ring "1.9.6"]
                 [compojure "1.7.0"]
                 [ring/ring-json "0.5.1"]]
  :main ^:skip-aot finance-goals.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                        :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
