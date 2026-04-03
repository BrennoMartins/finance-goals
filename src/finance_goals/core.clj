(ns finance-goals.core
  (:gen-class)
  (:require [finance-goals.adapters.inbound.http.routes :as http-routes]
            [finance-goals.adapters.outbound.random-macro-goal-generator :as random-macro-goal-generator]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.json :refer [wrap-json-response]]))

(defn build-app [generator]
  (-> (http-routes/build-routes generator)
      (wrap-json-response)))

(def app
  (build-app (random-macro-goal-generator/new-generator)))

(defn -main
  "Starts the API server"
  [& _args]
  (let [port 3000]
    (println (str "Server started at http://localhost:" port))
    (run-jetty app {:port port :join? true})))
