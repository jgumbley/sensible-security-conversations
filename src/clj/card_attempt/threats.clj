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
             (threat. "A cyber criminal, hacktivist or nation state group try to steal data from the system"
                      (likelihood. (list
                                    "What value might data in the system have to an attacker?"
                                    "Does the data have exchange value? Can it be sold?"
                                    "Could an attacker demand a ransom if they obtained the data?"
                                    "Are there specific reasons an attacker might target the data in your system?"
                                    "Can the data be used to escalate privledge into other systems?"))
                      (impact.     (list
                                    "How might data loss impact the organisation's reputation?"
                                    "Might there be a breakdown in trust with any parent organisation or customers?"
                                    "Might this trigger an investigation or penalties by a regulator?"
                                    "Might there be a financial cost in terms of revenue? Ransoms?"
                                    "What impact if data allows escalation of privledge to more sensitive systems?"))),
             (threat. "A cyber criminal or hacktivist group mount a denial of service attack on the system"
                      (likelihood. (list
                                    "Could an attacker demand a ransom were your system unavailable?"
                                    "Is it in the interest of any group to impact your reputation by taking your system down?"
                                    "Are there knock on effect on third parties or clients which an attack could benefit from?"))
                      (impact.     (list
                                    "What is the impact on revenue or operations if the system is down?"
                                    "How long could the system be down until it really hurt? 5 mins? 1 hour? 1 week?"
                                    ))),
             (threat. "A regulator investigates an infringement of the General Data Protection Regulation"
                      (likelihood. (list
                                    "Do you process the personal data of EU residents?"
                                    "Might an EU resident raise a complaint to a regulator about your organisation?"
                                    "Is it likely that you will be subject to a data breach?"))
                      (impact.     (list
                                     "Fines for failing to report a data breach can be up to 2% yearly revenue"
                                     "Fines for non compliance with GDPR can be up to 4% of global yearly revenue"
                                     "Penalties in most cases will be public record, leading to a reputational impact"))),
             (threat. "An end user or third party tries to commit fraud by providing false information"
                      (likelihood. (list
                                    "Are there previous examples of this kind of threat for this or similar systems?"
                                    "Could someone to gain financially by making an application or other action using your system?"
                                    "Could someone cause harm or mischief to a third party by using your system?"))
                      (impact.     (list
                                    "What is the impact of a single fraudulent transaction?"
                                    "How does this change if there are means of replaying or multiplying the fraud?"))),
             (threat. "A disgruntled employee or ex-exployee tries to embarrass or hurt the organisation"
                      (likelihood. (list
                                    "Are there signs of poor morale? Are managers aware of concerns of this type?"
                                    "Is it possible that someone could be laid off under a dispute?"
                                    "If something like this is detected, there will be legal implications. Would someone take the risk?"
                                    "Are there previous examples of this kind of threat?"))
                      (impact.     (list
                                    "What impact could someone with a normal level of access to the system make?"
                                    "What impact could someone with high levels of technical access to system make?"))),
             (threat. "A new form of ransomware or a wiper worm tries to spread from adjacent networks"
                      (likelihood. (list
                                    "Does the system connect to other networks?"))
                      (impact.     (list
                                    "What would be the impact if you had to rebuild your environment from scratch?"
                                    "Would there be dataloss if the environment was destroyed?"
                                    "How long would the system be unavailable for in the event of a rebuild?"))),
             (threat. "A developer or admin makes an error in configuring or securing the system"
                      (likelihood. (list
                                    "Human error can never be ruled out, although you can use automation and review to reduce issues."))
                      (impact.     (list
                                    "What might be the impact of misconfiguring access control?"
                                    "What might be the impact of a misconfiguration of transport encryption?"))),
             (threat. "A random botnet or scriptkiddy mounts an automated attack on the system"
                      (likelihood. (list
                                    "Should be considered likely if the system connects to the Internet"))
                      (impact.     (list
                                    "What is the business impact of spam comments, applications or signups?"
                                    "What is the effect of a system component being taken offline?"
                                    "What is the impact of a user account being brute-forced and access sold?"
                                    "What is the rebuild / forensic cost if a machine is compromised?"))),
             (threat. "An internal user tries to access sensitive information out of curiosity"
                      (likelihood. (list
                                    "Does the system have a large number of end users?"
                                    "Does the system store sensitive data that people might find of interest?"))
                      (impact.     (list
                                    "What is the administrative cost of a data breach?"
                                    "What is the reputational impact if personal nformation leaks to press?"
                                    "What is the privacy impact on the individual?"
                                    "Could there be regulatory impact?"))),
             (threat. " "
                      (likelihood. (list
                                    " "))
                      (impact.     (list
                                    " "))),
             (threat. " "
                      (likelihood. (list
                                    " "))
                      (impact.     (list
                                    " "))),
             (threat. " "
                      (likelihood. (list
                                    " "))
                      (impact.     (list
                                    " "))),
                      ])

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
