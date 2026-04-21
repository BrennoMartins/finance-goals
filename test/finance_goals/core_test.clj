(ns finance-goals.core-test
  (:require [clojure.test :refer :all]
            [cheshire.core :as json]
            [finance-goals.application.ports.macro-goal-generator :refer [MacroGoalGenerator]]
            [finance-goals.adapters.outbound.random-macro-goal-generator :as random-generator]
            [finance-goals.core :as core]
            [finance-goals.domain.macro-goal :as macro-goal]))

(defrecord StubMacroGoalGenerator [goals]
  MacroGoalGenerator
  (generate-macro-goals [_ total]
    (take total goals)))

(deftest random-generator-builds-valid-goal
  (testing "The outbound random generator creates a valid macro goal"
    (let [goal (random-generator/generate-random-goal)]
      (is (contains? goal :name))
      (is (contains? goal :step))
      (is (contains? goal :current-date))
      (is (contains? goal :goal-date))
      (is (contains? goal :goal-value))
      (is (string? (:name goal)))
      (is (some #{(:name goal)} macro-goal/goal-names))
      (is (integer? (:step goal)))
      (is (<= 0 (:step goal) macro-goal/max-step))
      (is (integer? (:goal-value goal)))
      (is (<= macro-goal/min-goal-value (:goal-value goal) macro-goal/max-goal-value)))))

(deftest build-app-wires-hexagonal-components
  (testing "The composition root assembles an HTTP app from the injected generator"
    (let [goals [{:name "Goal 1"
                  :step 1
                  :current-date "2026-04-03"
                  :goal-date "2027-04-03"
                  :goal-value 10000}
                 {:name "Goal 2"
                  :step 2
                  :current-date "2026-04-03"
                  :goal-date "2027-05-03"
                  :goal-value 20000}
                 {:name "Goal 3"
                  :step 3
                  :current-date "2026-04-03"
                  :goal-date "2027-06-03"
                  :goal-value 30000}
                 {:name "Goal 4"
                  :step 4
                  :current-date "2026-04-03"
                  :goal-date "2027-07-03"
                  :goal-value 40000}
                 {:name "Goal 5"
                  :step 5
                  :current-date "2026-04-03"
                  :goal-date "2027-08-03"
                  :goal-value 50000}]
          app (core/build-app (->StubMacroGoalGenerator goals))
          response (app {:request-method :get :uri "/api/macro-goals"})
          body (json/parse-string (:body response) true)]
      (is (= 200 (:status response)))
      (is (= 5 (:count body)))
      (is (= "Goal 1" (get-in body [:data 0 :name]))))))

