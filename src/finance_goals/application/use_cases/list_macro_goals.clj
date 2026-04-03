(ns finance-goals.application.use-cases.list-macro-goals
  (:require [finance-goals.application.ports.macro-goal-generator :as macro-goal-generator]))

(def default-goals-count 5)

(defn list-macro-goals [generator]
  (vec (macro-goal-generator/generate-macro-goals generator default-goals-count)))

