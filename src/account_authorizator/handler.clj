(ns account-authorizator.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :refer [site]]
            [ring.middleware.json :refer [wrap-json-body, wrap-json-response]]
            [compojure.route :as route]
            [account-authorizator.domain.service.account_service :refer [initialize, get-all]]
            [account-authorizator.domain.entity.account_entity :refer [to-string]]))

(defroutes app-routes
  (POST "/accounts" request
      (let [account (get-in request [:body :account])]
        {:status 200
         :body (to-string (initialize (:activeCard account) (:availableLimit account)))}))
  (GET "/accounts" request
        {:status 200
         :body (to-string (get-all))})
  (route/resources "/")
  (route/not-found "Not Found"))


(def app
  (-> (site app-routes)
      (wrap-json-body {:keywords? true})
       wrap-json-response))
