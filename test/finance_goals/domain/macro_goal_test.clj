(ns finance-goals.domain.macro-goal-test
  (:require [clojure.test :refer :all]
            [finance-goals.domain.macro-goal :as macro-goal]))

(deftest date-to-string-serializes-local-date
  (testing "It converts a LocalDate to ISO-8601 string format"
    (is (= "2026-04-03"
           (macro-goal/date-to-string (java.time.LocalDate/of 2026 4 3))))))

(deftest make-macro-goal-builds-domain-map
  (testing "It returns a macro goal map with the expected fields"
    (let [goal (macro-goal/make-macro-goal {:name "Save for retirement"
                                            :step 4
                                            :current-date-goal "2026-04-03"
                                            :goal-date "2028-04-03"
                                            :goal-value 250000})]
      (is (= {:name "Save for retirement"
              :step 4
              :current-date-goal "2026-04-03"
              :goal-date "2028-04-03"
              :goal-value 250000}
             goal)))))

