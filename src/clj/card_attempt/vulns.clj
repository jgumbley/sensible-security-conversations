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
                     (focus. "Opportunist cyber attack on infrastructure"
                             (list
                              (vulnerability. "Running version of infrastructure dependency with known vulnerability")
                              (vulnerability. "Running version of application dependency with known vulnerability")
                              (vulnerability. "Leakage of unnecessary system information which can assist an attacker")
                              (vulnerability. "Developer mode tools or default admin credentials are enabled")
                              (vulnerability. "Unnecessary network services are exposed by underlying infrastructure")
                              (vulnerability. "Fails to filter network (layer 2/3) denial of service from the Internet")
                              (vulnerability. "Possible for another tenant to read deallocated cloud storage")
                              ))
                     (focus. "Determined cyber attack on infrastructure"
                             (list
                              (vulnerability. "Running version of a dependency with 'zero day' vulnerability")
                              (vulnerability. "Possible to escalate privlege from another system")
                              (vulnerability. "Able to escalate privlege via cloud vendor side channel attack")
                              (vulnerability. "Possible to spawn a malicious process")
                              (vulnerability. "Possible for malicious process to read plaintext secret configuration at rest")
                              (vulnerability. "Possible for malicious process to read plaintext credentials at rest")
                              (vulnerability. "Possible for malicious process to read sensitive information from logs")
                              (vulnerability. "Possible to steal plaintext data from disc")
                              (vulnerability. "Possible to mount attack on other system components via network")
                              ))
                     (focus. "Access to data or services"
                             (list
                              (vulnerability. "Lack of access control, i.e. any form of authentication")
                              (vulnerability. "Use of shared accounts and credentials")
                              (vulnerability. "Reliance on a single factor for authentication")
                              (vulnerability. "Failure to configure access, i.e. based on least privlege")
                              (vulnerability. "Lack of identity or entitlement checks in setting up a new account")
                              (vulnerability. "Weakness in offline process to reset credentials")
                              (vulnerability. "Lack of audit log showing user access to sensitive data")
                              (vulnerability. "Lack access control to audit log showing user access to sensitive data")
                              (vulnerability. "Lack of awareness material to communicate audit and abuse policy to user")
                              ))
                     (focus. "Support for GDPR subject access rights"
                             (list
                              (vulnerability. "Lack of means to purge personal data for a data subject in response to request")
                              (vulnerability. "Lack of means to correct personal data for a data subject in response to request")
                              (vulnerability. "Lack of features to package personal data for access request or transfer to a competitor")
                              (vulnerability. "Lack of data retention policy for personal data")
                              (vulnerability. "Personal data is being stored without a justification for processing")
                              ))
                     (focus. "Server-side web implementation"
                             (list
                              (vulnerability. "Fails to prevent stored or reflected Cross Site Scripting (XSS)")
                              (vulnerability. "File upload feature fails to block malware")
                              (vulnerability. "Fails to prevent SQL, XML (XXE) or LDAP injection")
                              (vulnerability. "Fails to prevent shell injection")
                              (vulnerability. "Fails to prevent open redirects")
                              (vulnerability. "Fails to prevent Cross-site request forgery (CSRF)")
                              (vulnerability. "It is possible for attacker to tamper with cookies")
                              (vulnerability. "Framework support for mass binding can be exploited")
                              (vulnerability. "Alternate character encodings can be used to circumvent protections")
                              (vulnerability. "User forms can be completed in a scripted manner")
                              (vulnerability. "Lack of rate limiting allows 'scraping' or 'spidering' of valuable data")
                              (vulnerability. "URL paths can be manipulated to access system files or load remote files")
                              (vulnerability. "URL paths can be manipulated to access unauthorised resources")
                              (vulnerability. "Developers have disabled framework security protections")
                              (vulnerability. "Application is sensitive to application layer denial of service")
                              (vulnerability. "Triggering an exception leaks unnecessary information that can assist attacker")
                              ))
                     (focus. "Access control implementation"
                             (list
                              (vulnerability. "Authentication, authorisation or session management has been coded from scratch")
                              (vulnerability. "Authentication mechanism subject to brute force attack")
                              (vulnerability. "Input can be manipulated to enumerate all users")
                              (vulnerability. "Failure to allow use of password manager and strong credentials")
                              (vulnerability. "Failure to prevent users creating weak credentials")
                              (vulnerability. "An attacker can trivially guess session identifier")
                              (vulnerability. "Lack of access control on resources not intended to be discoverable to user")
                              (vulnerability. "Lack of protection against an attacker hijacking a session")
                              (vulnerability. "It is possible for attacker to tamper with session cookies")
                              (vulnerability. "Failure to check authorisation to more sensitive resources")
                              (vulnerability. "Failure to reauthenticate session when taking destructive actions, such as delete account")
                              (vulnerability. "Failure of session to timeout after a reasonable duration of time")
                              (vulnerability. "Failure to prevent caching of credentials on a shared computer")
                              (vulnerability. "Failure of user interface obscure entry of credentials")
                              (vulnerability. "Can authenticate with credentials harvested from other breaches")
                              ))
                     (focus. "End users"
                             (list
                              (vulnerability. "Lack of control over malware or shared logins on endpoint devices")
                              (vulnerability. "Lack awareness of audit and access policy")
                              ))
                     (focus. "Developers or Devops Users"
                             (list
                              (vulnerability. "Secrets are stored in plain text in source control")
                              (vulnerability. "Lack of full disc encryption on devices containing configuration secrets or data")
                              (vulnerability. "Lack of tooling to prevent pushing configuration secrets to source control")
                              (vulnerability. "Lack of access control based on least privlege in delivery infrastructure, scm or cloud console/API")
                              (vulnerability. "Failure to revoke access to delivery infrastructure rapidly when someone leaves")
                              (vulnerability. "Lack of policy and awareness for troubleshooting with copies of production data")
                              (vulnerability. "Sensitive information is present in log files")
                              (vulnerability. "Lack of peer review prior to deployment of code to production")
                              (vulnerability. "Lack of audit log showing user actions within delivery infrastructure")
                              (vulnerability. "Lack of integrity signatures on artefacts passing through delivery pipeline")
                              (vulnerability. "Lack of tests to verify functionality of security enforcing application code")
                              (vulnerability. "Lack of awareness of threat from phishing to developer devices")
                              (vulnerability. "Lack of protection from malware on developer devices")
                              (vulnerability. "Poor morale or short term staff in production support roles")
                              ))
                     (focus. "Mobile app implementation"
                             (list
                              (vulnerability. "Guessable values such as IMEI number used in authentication")
                              (vulnerability. "Sensitive data stored in unencrypted storage")
                              (vulnerability. "Sensitive data is leaked into logs")
                              (vulnerability. "Sensitive data is stored in predictable locations in memory")
                              ))
                     (focus. "Browser based app implemention"
                             (list
                              (vulnerability. "Fails to prevent DOM based Cross Site Scripting (XSS)")
                              (vulnerability. "Fails to prevent clickjacking")
                              (vulnerability. "Authenticated data is not removed from browser storage when session ends")
                              (vulnerability. "Lack of Client Security Policy (CSP) configration allows loading of untrusted resources")
                              (vulnerability. "Lack of Cross Origin Resource Sharing (CORS) configration allows loading of untrusted resources")
                              (vulnerability. "Dependency on browser-based access control implementation")
                              (vulnerability. "Dependency on browser based business logic")
                              (vulnerability. "Scripts to display advertising contain malicious code")
                              (vulnerability. "Code injection is possible via JSON responses recieved from server")
                              (vulnerability. "Transfers between DOM contexts are subject to code injection")
                             ))
                     (focus. "Network transport of data"
                             (list
                              (vulnerability. "Cleartext transport of credentials or data over Wifi")
                              (vulnerability. "Cleartext transport of credentials or data across Internet")
                              (vulnerability. "Cleartext transport of credentials or data between system components")
                              (vulnerability. "Configuration of TLS is vulnerable to a 'downgrade' attack")
                              (vulnerability. "TLS Cyphers configured are not upto date or do not provide forward secrecy")
                              (vulnerability. "Lack of anti-caching headers to prevent caching of sensitive HTTP requests or responses")
                              (vulnerability. "Lack of measures to prevent domain spoofing, such as Strict Transport Security")
                              ))
                     ])


(defn draw-vuln-summary [focus]
  [:div.bigcard
   [:div.bigcard-header
    [:div.card-threat-h2 "EXPOSURE"]
    [:div.card-threat-title (:title focus)]
    [:div.card-threat-subtitle "FOR ASSET:"]
    [:table
     [:theads  [:th "&nbsp"] [:th.vuln "VULNERABILITY"] [:th "EXPOSURE?"]]
     (for [vuln (:vulnerabilities focus)]
       [:tr [:td "&nbsp;"] [:td (:title vuln)] [:td.tick "&#9744;"]]
       )
     ]
    ]
   [:div.card-category "Sensible Conversations about Security"]
   ]
  )

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


(defn vulns-summary-page []
  (let [vulns vulnerabilities]
    (html5
     (head)
     [:body {:class "body-container"}
      (for [focus vulns] (draw-vuln-summary focus) )
      ]
     )))

(defn vulns-page []
  (let [vulns vulnerabilities]
    (html5
     (head)
     [:body {:class "body-container"}
      (for [focus vulns] (for [vuln (:vulnerabilities focus)] (draw-vuln focus vuln) ))
      ]
     )))
