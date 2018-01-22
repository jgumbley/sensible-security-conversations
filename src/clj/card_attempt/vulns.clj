(ns card-attempt.vulns
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
   (include-css "https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,700,800")
   ])

;-----------------------------------------------------

(defrecord focus [title vulnerabilities])
(defrecord vulnerability [title])

(def vulnerabilities[
                     (focus. "Components accepting input"
                             (list
                              (vulnerability. "A cyber criminal, hacktivist or nation state group try to steal data from the system")
                              ))])

(defn draw-vuln [focus vuln]
  [:div.bigcard
   [:div.bigcard-header
      [:div.card-threat-main "VULNERABILITY"]
      [:div.card-threat-subtitle "FOCUS"]
      [:div.card-threat-title (:title focus)]
      [:div.card-threat-subtitle "DESCRIPTION"]
      [:div.card-threat-title (:title vuln)]
    ]
   [:div.card-category "Sensible Conversations about Security"]
   ]
  )


(defn vulns-page []
  (let [vulns vulnerabilities]
    (html5
     (head)
     [:body {:class "body-container"}
      (for [focus vulns] (for [vuln (:vulnerabilities focus)] (draw-vuln focus vuln) ))
      ]
     )))
