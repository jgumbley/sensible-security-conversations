(ns card-attempt.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found resources]]
            [hiccup.page :refer [include-js include-css html5]]
            [hiccup.util :refer [escape-html]]
            [card-attempt.middleware :refer [wrap-middleware]]
            [config.core :refer [env]]
            [trello.core :refer [make-client]]
            [trello.client :as t]
            [clojure.string :as str]
            ))


(defn head []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name    "viewport"
           :content "width=device-width, initial-scale=1"}]
   (include-css (if (env :dev) "/css/site.css" "/css/site.min.css"))
   (include-css "https://fonts.googleapis.com/css?family=Open+Sans")
   ])

(def client (make-client (env :key) (env :secret)))

(def cards (client t/get "boards/ZiANFBCJ/cards"))

(defn map-trello-card [trello-card]
  {:category       ((first (trello-card :labels)) :name)
   :given-clause   (first (str/split (trello-card :name) #"THEN"))
   :then-clause    (str "THEN " (second (str/split (trello-card :name) #"THEN")))
   :category-color ((first (trello-card :labels)) :color)
   :trello-url     (escape-html (trello-card :shortUrl))
   }
  )

(defn style-to-line [line style]
  [:span {:class style} line]
  )

(defn style-to-word [line word style]
  [:span [:span {:class style} word] (str/replace line word "")]
  )


(defn draw-card [card]
  [:div.card
   [:div.card-given (style-to-word (card :given-clause) "GIVEN" "card-given-title")]
   [:div.card-then (style-to-word (card :then-clause) "THEN" "card-then-title")]
   [:div.card-category (style-to-line (card :category) (card :category-color))]
   ]
  )

(defn trello-page []
  (html5
    (head)
    [:body {:class "body-container"}
     (for [card cards] (draw-card (map-trello-card card)))
     ]
    )
  )

;-----------------------------------------------------

(def mount-target
  [:div#app
   [:h3 "ClojureScript has not been compiled!"]
   [:p "please run "
    [:b "lein figwheel"]
    " in order to start the compiler"]])

(defn loading-page []
  (html5
    (head)
    [:body {:class "body-container"}
     mount-target
     (include-js "/js/app.js")]))


(defroutes routes
           ;(GET "/" [] (loading-page))
           ;(GET "/about" [] (loading-page))
           (GET "/trello" [] (trello-page))

           (resources "/")
           (not-found "Not Found"))

(def app (wrap-middleware #'routes))
