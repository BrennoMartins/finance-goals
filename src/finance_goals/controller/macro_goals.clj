(ns finance-goals.controller.macro-goals
  (:require [finance-goals.model.macro-goal :as model.macro-goal]
            [schema.core :as s]))


(s/defn get-all-macro-goals :- model.macro-goal/macro-goal
  []
  { :id 1
   :name "Comprar um carro"
   :step 2
   :current-date-goal "2024-01-01"
   :goal-date "2025-01-01"
   :goal-value 20000.0
   :created-at "2024-06-01T12:00:00Z"
   :updated-at "2024-06-01T12:00:00Z"}
  )