(ns card-attempt.prod
  (:require [card-attempt.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
