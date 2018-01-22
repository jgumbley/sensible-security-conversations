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
                     (focus. "Software component"
                             (list
                              (vulnerability. "Running version of infrastructure dependency with known vulnerability")
                              (vulnerability. "Running version of application dependency with known vulnerability")
                              (vulnerability. "Running version of dependency with 'zero day' vulnerability")
                              ))
                     (focus. "Component accepting input"
                             (list
                              (vulnerability. "Fails to prevent Cross Site Scripting (XSS)")
                              (vulnerability. "File upload feature fails to block malware")
                              (vulnerability. "Fails to prevent SQL injection")
                              (vulnerability. "Fails to prevent XML injection")
                              (vulnerability. "Fails to prevent LDAP injection")
                              (vulnerability. "Fails to prevent shell injection")
                              (vulnerability. "Fails to prevent code injection via json")
                              (vulnerability. "Fails to prevent a stack overflow")
                              (vulnerability. "Framework support for mass binding can be exploited")
                              (vulnerability. "User forms can be completed in a scripted manner")
                              (vulnerability. "URL paths can be manipulated to access system files")
                              (vulnerability. "URL paths can be manipulated to access unauthorised resources")
                              (vulnerability. "Developers have disabled framework security protections")
                              (vulnerability. "layer 7 denial of service")
                              (vulnerability. "open redirect")
                              ))
                     (focus. "Got root access"
                             (list
                              (vulnerability. "Possible to spawn a malicious process")
                              (vulnerability. "Possible to steal plaintext secret configuration at rest")
                              (vulnerability. "Possible to steal plain text credentials at rest")
                              (vulnerability. "Possible to steal plaintext data from disc")
                              (vulnerability. "Possible to mount attack on other system components")
                              ))
                     (focus. "Control of access to data or services"
                             (list
                              (vulnerability. "Lack of access control, i.e. any form of authentication")
                              (vulnerability. "Use of shared accounts and credentials")
                              (vulnerability. "Failure to prevent users creating weak credentials")
                              (vulnerability. "Failure to check authorisation to more sensitive resources")
                              (vulnerability. "Weakness in offline process to reset credentials")
                              (vulnerability. "Vulnerable to credentials harvested from other breaches")
                              (vulnerability. "Failure to allow use of password manager and strong credentials")
                              (vulnerability. "Session hijack failure in implementation")
                              (vulnerability. "shared computer read over shoulder or pull from cache")
                              (vulnerability. "authorisation decision not logged")
                              (vulnerability. "cause an exception and gain information")
                              (vulnerability. "hand rolled by team session management or access control")
                              (vulnerability. "no checks to ensure the user is whom they claim to be")
                              ))
                     (focus. "Developers or Devops Users"
                             (list
                              (vulnerability. "possible to commit a backdoor, easter egg or logic flaw to source control")
                              (vulnerability. "possible to bribe folks to take malicous action")
                              (vulnerability. "Failure to revoke access rapidly when someone leaves")
                              (vulnerability. "Laptop is stolen or lost")
                              (vulnerability. "Errors in configuration of cloud, tls or access control")
                              (vulnerability. "click jacking")
                              (vulnerability. "phishing")
                              ))
                     (focus. "Single page app"
                             (list
                              (vulnerability. "Implementation of browser based access control")
                              (vulnerability. "Implementation of browser based business logic")
                             ))
                     (focus. "Network transport of data"
                             (list
                              (vulnerability. "Cleartext transport of data over Wifi")
                              (vulnerability. "Cleartext transport of data across Internet")
                              (vulnerability. "Cleartext transport of data between system components")
                              (vulnerability. "Configuration of TLS is vulnerable to a 'downgrade' attack")
                              ))
                     (focus. "Underlying infrastructure"
                             (list
                              (vulnerability. "Cloud vendor side channel attack")
                              (vulnerability. "Network flood denial of service")
                              (vulnerability. "Secrets are in source control")
                              (vulnerability. "Leaks an API secret into source")
                              (vulnerability. "Admin default password")
                              (vulnerability. "Sensitive information written into logs")
                              ))
                     ])


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
