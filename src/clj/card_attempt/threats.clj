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
   (include-css "https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,700,800")
   ])

;-----------------------------------------------------

(defrecord threat [title likelihood impact])
(defrecord likelihood [bullets])
(defrecord impact [bullets])

(def threats[
             (threat. "A cyber criminal, hacktivist or nation state group tries to steal data from the system"
                      (likelihood. (list
                                   "What value might data in the system have to an attacker?"
                                   "Does the data have exchange value? Can it be sold?"
                                   "Could an attacker demand a ransom if they obtained the data?"
                                   "Are there specific reasons an attacker might target the data in your system?"
                                   "Can the data be used to escalate privledge into other more sensitive systems?"))
                      ""),
             (threat. "A cyber criminal or hacktivist group mounts a denial of service attack on the system"
                      (likelihood. (list
                                    "Could an attacker demand a ransom were your system unavailable?"
                                    "Is it in the interest of any group to impact your reputation by taking your system down?"
                                    "Are there knock on effect on third parties or clients which an attack could benefit from?"))
                      ""),
             (threat. "A regulator investigates an infringement of the General Data Protection Regulation"
                      (likelihood. (list
                                    "Do you process the personal data of EU residents?"
                                    "Might an EU resident raise a complaint to a regulator about your organisation?"
                                    "Is it likely that you will be subject to a data breach?"))
                      ""),
             (threat. "An end user or third party tries to commit fraud by providing false information"
                      (likelihood. (list
                                    "Is it possible for someone to gain financially by using your system?"
                                    "Could someone cause harm or mischief to a third party by using your system?"))
                      ""),
             (threat. "A disgruntled employee or ex-exployee tries to embarrass or hurt the organisation"
                      (likelihood. (list
                                    "Are there signs of poor morale? Are managers aware of concerns of this type?"
                                    "If the organisation is large, might we not know if there was a threat?"
                                    "Is it possible that someone could be laid off under a dispute?"
                                    "Are there previous examples of this kind of threat?"))
                      ""),
             (threat. "A new form of ransomware or a wiper worm tries to spread via adjacent networks"
                      (likelihood. (list
                                    "Does the system connect to other networks?"))
                      ""),
             (threat. "A developer or admin makes an error in configuring or securing the system"
                      (likelihood. (list
                                    "Does the system connect to the Internet?"))
                      ""),
             (threat. "A random botnet or scriptkiddy mounts an automated attack on the system"
                      (likelihood. (list
                                    "Does the system connect to the Internet?"))
                      ""),
             (threat. "An internal user tries to access sensitive information out of curiosity"
                      (likelihood. (list
                                    "Does the system have a large number of end users?"
                                    "Does the system store sensitive data that people might find of interest?"))
                      "")])

(defn draw-threat [card]
  [:div.bigcard
   [:div.bigcard-header
      [:div.card-threat-main "THREAT"]
      [:div.card-threat-title (:title card)]
    ]
   [:div.bigcard-column
      [:div.card-threat-subtitle "LIKELIHOOD"]
      [:ul (for [bullet (:bullets (:likelihood card))] [:li bullet]) ]
    ]
   [:div.bigcard-column
      [:div.card-threat-subtitle "IMPACT"]
      [:ul (for [bullet (:bullets (:impact card))] [:li bullet]) ]
    ]
   [:div.card-category "Sensible Conversations about Security"]
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
