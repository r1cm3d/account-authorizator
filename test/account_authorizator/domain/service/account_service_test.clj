(ns account-authorizator.domain.service.account_service_test
  (:require [clojure.test :refer [deftest, is]]
            [account-authorizator.domain.entity.account_entity :refer [->Account]]
            [account-authorizator.helper.account_helper :refer [get-expected-account]]
            [account-authorizator.domain.service.account_service :refer [create, initialize]]))

(defn get-expected-already-initialized-account []
  (->Account true 100 ["account-already-initialized"]))

(deftest create-account-when-there-is-no-previous-account
  (is (= (get-expected-account) (create [] true 100))))

(deftest create-account-when-there-is-previous-account
  (is (= (get-expected-already-initialized-account) 
         (create [(get-expected-account)] true 100))))

(deftest initialize-must-keep-account-state
   (initialize true 100)
   (is (= (get-expected-already-initialized-account)
          (initialize true 100))))