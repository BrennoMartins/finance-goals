(ns finance-goals.adapters.inbound.http.routes
  (:require [compojure.core :refer [GET routes]]
            [compojure.route :as route]
            [finance-goals.application.use-cases.list-macro-goals :as list-macro-goals]
            [ring.util.response :refer [not-found response]]))

(defn macro-goals-response [generator]
  (let [goals (list-macro-goals/list-macro-goals generator)]
    (response {:data goals
               :count (count goals)})))

(defn build-routes [generator]
  (routes
    (GET "/api/macro-goals" []
      (macro-goals-response generator))
    (GET "/macro-goals" []
      (macro-goals-response generator))
    (route/not-found
      (not-found {:error "Not Found"}))))

