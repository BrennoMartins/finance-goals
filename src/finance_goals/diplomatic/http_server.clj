(ns finance-goals.diplomatic.http-server
  (:require [compojure.core :refer [GET POST PUT defroutes]]
            [compojure.route :as route]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [api-defaults wrap-defaults]]
            [finance-goals.controller.macro-goals :as controller.macro-goals]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.cors :refer [wrap-cors]]))

(defroutes app-routes
           (GET "/goal/macro" []
                (let [macro-golas (controller.macro-goals/get-all-macro-goals )]
                  {:status 200
                   :body   macro-golas}))

           (route/not-found {:status 404 :body "Route not found"}))

(def app
  (-> app-routes
      (wrap-cors
        :access-control-allow-origin [#"http://localhost:5173"]
        :access-control-allow-methods [:get :post :put :delete :options]
        :access-control-allow-headers ["Content-Type" "Authorization"])
      (wrap-json-body {:keywords? true})
      wrap-json-response
      (wrap-defaults api-defaults)))

(defn -main []
  (jetty/run-jetty app {:port 3000}))