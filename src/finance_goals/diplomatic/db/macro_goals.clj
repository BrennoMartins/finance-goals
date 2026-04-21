(ns finance-goals.diplomatic.db.macro-goals
  (:require [clojure.java.jdbc :as jdbc]
            [finance-goals.model.macro-goal :as model.macro-goal]
            [schema.core :as s]))

(s/defn return-all-macro-goals :- model.macro-goal/macro-goal
  "Return all macro goals."
  [db table]
  (jdbc/query db
              [(str "SELECT * FROM " table)]))