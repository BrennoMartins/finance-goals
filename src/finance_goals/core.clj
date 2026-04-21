(ns finance-goals.core
  (:require [finance-goals.diplomatic.http-server :as http-server]))

(defn -main
  "Main entry point for the application"
  []
  (println "Starting Finance Goals API on port 3000...")
  (http-server/-main))
