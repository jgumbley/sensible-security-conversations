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
  [ (threat. "a"),
    (threat. "b"),
    (threat. "c")])

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
