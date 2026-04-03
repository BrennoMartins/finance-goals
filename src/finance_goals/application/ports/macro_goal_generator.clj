(ns finance-goals.application.ports.macro-goal-generator)

(defprotocol MacroGoalGenerator
  (generate-macro-goals [this total]))

