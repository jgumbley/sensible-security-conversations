(ns card-attempt.threats
  (:require [hiccup.page :refer [include-js include-css html5]]
            [config.core :refer [env]]
            [hiccup.util :refer [escape-html]]))

;-----------------------------------------------------

(defn head []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name    "viewport"
           :content "width=device-width, initial-scale=1"}]
   (include-css (if (env :dev) "/css/site.css" "/css/site.min.css"))
   (include-css "https://fonts.googleapis.com/css?family=Open+Sans")
   ])

;-----------------------------------------------------

(defrecord threat [title])

(def threats
  [
   (threat. "A cyber criminal group, hacktivist or nation state tries to steal data from the system"),
   (threat. "A cyber criminal or hacktivist group mounts a denial of service attack on the system"),
   (threat. "A regulator issues a penalty under the General Data Protection Regulation"),
   (threat. "An end user or third party commits fraud by providing false information"),
   (threat. "A disgruntled or angry employee or ex-exployee abuses access to embarrass or hurt the organisation"),
   (threat. "A random botnet or scriptkiddy mounts a simple automated attack on the system"),
   (threat. "An internal user casually accesses information they are not entited to out of curiosity")])

(defn draw-threat [card]
  [:div.bigcard
   [:div.card-category (:title card)]
   ]
  )


(defn threats-page []
  (let [cards threats]
    (html5
     (head)
     [:body {:class "body-container"}
      (for [card cards] (draw-threat card))
      ]
     ))
  )
