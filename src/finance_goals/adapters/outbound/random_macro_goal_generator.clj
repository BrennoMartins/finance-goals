(ns finance-goals.adapters.outbound.random-macro-goal-generator
  (:require [finance-goals.application.ports.macro-goal-generator :refer [MacroGoalGenerator]]
            [finance-goals.domain.macro-goal :as macro-goal]))

(defn random-goal-date []
  (macro-goal/date-to-string
   (-> (java.time.LocalDate/now)
       (.plusMonths (+ 6 (rand-int 36))))))

(defn random-goal-value []
  (+ macro-goal/min-goal-value
     (rand-int (inc (- macro-goal/max-goal-value macro-goal/min-goal-value)))))

(defn generate-random-goal []
  (macro-goal/make-macro-goal
   {:name (rand-nth macro-goal/goal-names)
    :step (rand-int (inc macro-goal/max-step))
    :current-date-goal (macro-goal/date-to-string (java.time.LocalDate/now))
    :goal-date (random-goal-date)
    :goal-value (random-goal-value)}))

(defrecord RandomMacroGoalGenerator []
  MacroGoalGenerator
  (generate-macro-goals [_ total]
    (repeatedly total generate-random-goal)))

(defn new-generator []
  (->RandomMacroGoalGenerator))

