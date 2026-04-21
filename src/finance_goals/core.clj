(ns finance-goals.core
  (:gen-class)
  (:require [finance-goals.adapters.inbound.http.routes :as http-routes]
            [finance-goals.adapters.outbound.postgres-macro-goal-generator :as postgres-macro-goal-generator]
            [next.jdbc :as jdbc]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.json :refer [wrap-json-response]]))

(def db
  {:dbtype "postgresql"
   :dbname "finance"
   :host "localhost"
   :port 5432
   :user "usuario"
   :password "1234"})

(def datasource
  (jdbc/get-datasource db))

(defn build-app [generator]
  (-> (http-routes/build-routes generator)
      (wrap-json-response)))

(def app
  (build-app (postgres-macro-goal-generator/new-generator datasource)))

(defn -main
  "Starts the API server"
  [& _args]
  (let [port 3000]
    (println (str "Server started at http://localhost:" port))
    (run-jetty app {:port port :join? true})))
