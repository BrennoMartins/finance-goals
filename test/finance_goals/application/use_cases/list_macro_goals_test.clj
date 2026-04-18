(ns finance-goals.application.use-cases.list-macro-goals-test
  (:require [clojure.test :refer :all]
            [finance-goals.application.ports.macro-goal-generator :refer [MacroGoalGenerator]]
            [finance-goals.application.use-cases.list-macro-goals :as list-macro-goals]))

(defrecord StubMacroGoalGenerator [goals]
  MacroGoalGenerator
  (generate-macro-goals [_ total]
    (take total goals)))

(deftest list-macro-goals-returns-five-goals
  (testing "It returns the default number of macro goals from the generator port"
    (let [goals (for [n (range 1 8)]
                  {:name (str "Goal " n)
                   :step n
                   :current-date-goal "2026-04-03"
                   :goal-date "2027-04-03"
                   :goal-value (* n 1000)})
          generator (->StubMacroGoalGenerator goals)]
      (is (= 5 (count (list-macro-goals/list-macro-goals generator)))))))

