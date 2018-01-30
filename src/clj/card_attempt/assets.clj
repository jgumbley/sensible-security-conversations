(ns card-attempt.assets
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

(defrecord asset [type property1 property2
                  example1 example2 example3])

(def assets[
            (asset. "DATA AT REST" "REASON FOR STORING" "VALUE OR SENSITIVITY"
                    "&nbsp;" (list
                              "In order to")
                    (list
                     "Personally identifiable"
                     "Financial"
                     "Anonymised"
                     "Sensitive Personal Data"
                     "Commercially sensitive"
                     "Regulated under PCI"))
            (asset. "DATA AT REST" "REASON FOR STORING" "VALUE OR SENSITIVITY"
                    "&nbsp;" (list
                              "In order to")
                    (list
                     "Personally identifiable"
                     "Financial"
                     "Anonymised"
                     "Sensitive Personal Data"
                     "Commercially sensitive"
                     "Regulated under PCI"))
            (asset. "DATA AT REST" "REASON FOR STORING" "VALUE OR SENSITIVITY"
                    "&nbsp;" (list
                              "In order to")
                    (list
                     "Personally identifiable"
                     "Financial"
                     "Anonymised"
                     "Sensitive Personal Data"
                     "Commercially sensitive"
                     "Regulated under PCI"))
            (asset. "DATA AT REST" "REASON FOR STORING" "VALUE OR SENSITIVITY"
                    "&nbsp;" (list
                                "In order to")
                    (list
                     "Personally identifiable"
                     "Financial"
                     "Anonymised"
                     "Sensitive Personal Data"
                     "Commercially sensitive"
                     "Regulated under PCI"))
            (asset. "SERVICE" "PURPOSE OF SERVICE" " "
                    "Continous Delivery pipeline" (list
                                              "In order that developers can make changes to the system")
                    "")
            (asset. "SERVICE" "PURPOSE OF SERVICE" " "
                    "&nbsp;" (list
                              "In order that ")
                    "")
            (asset. "SERVICE" "PURPOSE OF SERVICE" " "
                    "&nbsp;" (list
                              "In order that ")
                    "")
            (asset. "SERVICE" "PURPOSE OF SERVICE" " "
                    "&nbsp;" (list
                                "In order that ")
                    "")
            (asset. "PEOPLE" "REASON FOR USING SYSTEM" "HOW DO THEY CONNECT"
                    "Developers and Devops"
                    (list
                     "In order to make changes to the system directed by product owner")
                    (list
                     "Via a VPN into the build system from laptops"
                     ))
            (asset. "PEOPLE" "REASON FOR USING SYSTEM" "HOW DO THEY CONNECT"
                    "&nbsp;"
                    (list
                     "In order to")
                    (list
                     "&nbsp;"
                     ))
            (asset. "PEOPLE" "REASON FOR USING SYSTEM" "HOW DO THEY CONNECT"
                    "&nbsp;"
                    (list
                     "In order to")
                    (list
                     "&nbsp;"
                     ))
            (asset. "PEOPLE" "REASON FOR USING SYSTEM" "HOW DO THEY CONNECT"
                    "&nbsp;"
                    (list
                     "In order to")
                    (list
                    "&nbsp;"
                    ))
            ])


(defn draw-asset [asset]
  [:div.bigcard
   [:div.bigcard-header
      [:div.card-threat-main (:type asset)]
      [:div.card-threat-title (:example1 asset)]
    ]
   [:div.bigcard-column
    [:div.card-threat-subtitle (:property1 asset)]
    [:ul (for [bullet (:example2 asset)] [:li bullet]) ]
    ]
   [:div.bigcard-column
    [:div.card-threat-subtitle (:property2 asset)]
    [:ul (for [bullet (:example3 asset)] [:li bullet]) ]
    ]
   [:div.card-category "Sensible Conversations about Security"]
   ]
  )


(defn assets-page []
  (let [asset-cards assets]
    (html5
     (head)
     [:body {:class "body-container"}
      (for [asset-card asset-cards] (draw-asset asset-card) )
      ]
     )))
