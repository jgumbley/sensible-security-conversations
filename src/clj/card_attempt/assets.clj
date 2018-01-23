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
                    "Pet food claims" (list
                                "In order to process pet food refund claims")
                    (list
                     "Personally identifiable"
                     "Financial"
                     "Not commercially sensitive"
                     "Regulated under PCI"))
            (asset. "DATA AT REST" "REASON FOR STORING" "VALUE OR SENSITIVITY"
                    "Log data" (list
                         "In order to troubleshoot issues in the system")
                        (list
                         "Not personally sensitive"
                         "Not personally identifiable"
                         "Not financial"
                         "Not commercially sensitive"
                         "Not regulated"))
            (asset. "SERVICE" "PURPOSE OF SERVICE" " "
                    "Continous Delivery pipeline" (list
                                              "In order that developers can be changes to the system")
                    "")
            (asset. "SERVICE" "PURPOSE OF SERVICE" " "
                    "Pet food claim service" (list
                                "In order that people can make claims for defective pet food")
                    "")
            (asset. "PEOPLE" "REASON FOR USING SYSTEM" "HOW DO THEY CONNECT"
                    "Developers and Devops"
                    (list
                     "In order to make changes to the system directed by product owner")
                    (list
                     "Via a VPN into the build system from laptops"
                     ))
            (asset. "PEOPLE" "REASON FOR USING SYSTEM" "HOW DO THEY CONNECT"
                    "Pet food refund claimants"
                    (list
                     "In order to claim a refund for a defective pouch of catfood")
                    (list
                    "Via public internet"
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
