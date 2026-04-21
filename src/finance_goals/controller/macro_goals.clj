(ns finance-goals.controller.macro-goals
  (:require [finance-goals.model.macro-goal :as model.macro-goal]
            [finance-goals.diplomatic.db.finance-db :as db.finance-db]
            [finance-goals.diplomatic.db.macro-goals :as db.macro-goals]
            [schema.core :as s]))

(s/defn get-all-macro-goals :- model.macro-goal/macro-goal
  []
  (let [db (db.finance-db/db)
        table :macro_goals]
    (db.macro-goals/return-all-macro-goals db table)))