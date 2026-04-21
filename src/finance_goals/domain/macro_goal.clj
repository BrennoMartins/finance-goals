(ns finance-goals.domain.macro-goal)

(def goal-names
  ["Save for retirement"
   "Buy a property"
   "Create emergency fund"
   "Invest in education"
   "Travel around the world"])

(def min-goal-value 10000)
(def max-goal-value 999999)
(def max-step 9)

(defn date-to-string [date]
  (.toString date))

(defn make-macro-goal
  [{:keys [name step current-date goal-date goal-value]}]
  {:name name
   :step step
   :current-date current-date
   :goal-date goal-date
   :goal-value goal-value})

