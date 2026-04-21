(ns finance-goals.adapters.outbound.postgres-macro-goal-generator-test
  (:require [clojure.test :refer :all]
            [finance-goals.application.ports.macro-goal-generator :as macro-goal-generator]
            [finance-goals.adapters.outbound.postgres-macro-goal-generator :as postgres-generator]
            [next.jdbc :as jdbc]))

(deftest postgres-generator-maps-query-result-to-domain-shape
  (testing "It maps rows from SQL query to macro goal maps"
    (let [generator (postgres-generator/new-generator ::fake-datasource)]
      (with-redefs [jdbc/execute! (fn [_ _ _]
                                    [{:name "Save for retirement"
                                      :step 4
                                      :current_date_goal "2026-04-18"
                                      :goal_date "2028-04-18"
                                      :goal_value 250000}])]
        (is (= [{:name "Save for retirement"
                 :step 4
                 :current-date "2026-04-18"
                 :goal-date "2028-04-18"
                 :goal-value 250000}]
               (macro-goal-generator/generate-macro-goals generator 1)))))))

(deftest postgres-generator-returns-empty-list-for-non-positive-total
  (testing "It avoids querying for non-positive limits"
    (let [generator (postgres-generator/new-generator ::fake-datasource)
          called? (atom false)]
      (with-redefs [jdbc/execute! (fn [& _]
                                    (reset! called? true)
                                    [])]
        (is (= [] (macro-goal-generator/generate-macro-goals generator 0)))
        (is (false? @called?))))))

