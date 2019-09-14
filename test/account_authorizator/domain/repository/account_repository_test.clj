(ns account-authorizator.domain.repository.account_repository_test
    (:require 
            [clojure.test :refer [is, deftest, use-fixtures]]
            [account-authorizator.helper.account_helper :refer [get-expected-account]]
            [account-authorizator.domain.repository.account_repository :refer [is-empty, save, get-accounts, clear]]))

(defn clear-database [f]
    (clear)
    (f))

(defn get-expected-created-accounts []
    [(get-expected-account)])

(deftest empty-must-return-true-when-there-is-no-account
  (is (= true (is-empty))))

(deftest empty-must-return-false-when-after-an-insertion
  (save "xpto")  
  (is (= false
         (is-empty))))

(deftest get-accounts-must-return-created-accounts
  (save (get-expected-account))
  (is (= (get-expected-created-accounts)
         (get-accounts))))

(use-fixtures :each clear-database)