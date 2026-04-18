(ns finance-goals.adapters.inbound.http.routes-test
  (:require [clojure.test :refer :all]
            [cheshire.core :as json]
            [finance-goals.adapters.inbound.http.routes :as routes]
            [finance-goals.application.ports.macro-goal-generator :refer [MacroGoalGenerator]]
            [ring.middleware.json :refer [wrap-json-response]]))

(defrecord StubMacroGoalGenerator [goals]
  MacroGoalGenerator
  (generate-macro-goals [_ total]
    (take total goals)))

(def sample-goals
  [{:name "Goal 1" :step 1 :current-date-goal "2026-04-03" :goal-date "2027-04-03" :goal-value 10000}
   {:name "Goal 2" :step 2 :current-date-goal "2026-04-03" :goal-date "2027-05-03" :goal-value 20000}
   {:name "Goal 3" :step 3 :current-date-goal "2026-04-03" :goal-date "2027-06-03" :goal-value 30000}
   {:name "Goal 4" :step 4 :current-date-goal "2026-04-03" :goal-date "2027-07-03" :goal-value 40000}
   {:name "Goal 5" :step 5 :current-date-goal "2026-04-03" :goal-date "2027-08-03" :goal-value 50000}])

(def app
  (wrap-json-response
   (routes/build-routes (->StubMacroGoalGenerator sample-goals))))

(deftest api-route-returns-macro-goals
  (testing "GET /api/macro-goals returns the JSON payload with five goals"
    (let [response (app {:request-method :get :uri "/api/macro-goals"})
          body (json/parse-string (:body response) true)]
      (is (= 200 (:status response)))
      (is (= 5 (:count body)))
      (is (= 5 (count (:data body))))
      (is (= 10000 (get-in body [:data 0 :goal-value]))))))

(deftest alternative-route-returns-macro-goals
  (testing "GET /macro-goals returns the same payload shape"
    (let [response (app {:request-method :get :uri "/macro-goals"})
          body (json/parse-string (:body response) true)]
      (is (= 200 (:status response)))
      (is (= 5 (:count body)))
      (is (= "Goal 5" (get-in body [:data 4 :name]))))))

(deftest unknown-route-returns-not-found
  (testing "Unknown routes return a valid 404 response map"
    (let [response (app {:request-method :get :uri "/unknown"})
          body (json/parse-string (:body response) true)]
      (is (= 404 (:status response)))
      (is (= "Not Found" (:error body))))))

