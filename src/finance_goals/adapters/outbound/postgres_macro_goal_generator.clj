(ns finance-goals.adapters.outbound.postgres-macro-goal-generator
  (:require [finance-goals.application.ports.macro-goal-generator :refer [MacroGoalGenerator]]
            [finance-goals.domain.macro-goal :as macro-goal]
            [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs]))

(defrecord PostgresMacroGoalGenerator [datasource]
  MacroGoalGenerator
  (generate-macro-goals [_ total]
    (if (<= total 0)
      []
      (let [rows (jdbc/execute! datasource
                               ["SELECT name, step, current_date_goal, goal_date, goal_value
                                 FROM macro_goals
                                 ORDER BY id
                                 LIMIT ?" total]
                               {:builder-fn rs/as-unqualified-lower-maps})]
        (mapv (fn [{:keys [name step current_date_goal goal_date goal_value]}]
                (macro-goal/make-macro-goal
                 {:name name
                  :step step
                  :current-date current_date_goal
                  :goal-date goal_date
                  :goal-value goal_value}))
              rows)))))

(defn new-generator [datasource]
  (->PostgresMacroGoalGenerator datasource))

