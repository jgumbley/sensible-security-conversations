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
                     (focus. "Opportunist cyber attack"
                             (list
                              (vulnerability. "Running version of infrastructure dependency with known vulnerability")
                              (vulnerability. "Running version of application dependency with known vulnerability")
                              (vulnerability. "Leakage of unnecessary system information which can assist an attacker")
                              (vulnerability. "Developer mode tools or default admin credentials are enabled")
                              (vulnerability. "Unnecessary network services are exposed by underlying infrastructure")
                              (vulnerability. "Fails to filter network (layer 2/3) denial of service from the Internet")
                              (vulnerability. "Possible for another tenant to read deallocated cloud storage")
                              ))
                     (focus. "Determined cyber attack"
                             (list
                              (vulnerability. "Possible to bribe or extort someone with deep level of technical access")
                              (vulnerability. "Running version of a dependency with 'zero day' vulnerability")
                              (vulnerability. "Possible to escalate privledge from another system")
                              (vulnerability. "Able to escalate privledge via cloud vendor side channel attack")
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
                              (vulnerability. "Failure to configure access, i.e. based on least privledge")
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
                     (focus. "Implementation of input handling"
                             (list
                              (vulnerability. "Fails to prevent Cross Site Scripting (XSS)")
                              (vulnerability. "Fails to prevent reflected Cross Site Scripting (XSS)")
                              (vulnerability. "File upload feature fails to block malware")
                              (vulnerability. "Fails to prevent SQL injection")
                              (vulnerability. "Fails to prevent XML injection (XML bombs and XXE)")
                              (vulnerability. "Fails to prevent LDAP injection")
                              (vulnerability. "Fails to prevent shell injection")
                              (vulnerability. "Fails to prevent open redirects")
                              (vulnerability. "Fails to prevent code injection via json")
                              (vulnerability. "Fails to prevent a stack overflow")
                              (vulnerability. "Fails to prevent clickjacking")
                              (vulnerability. "It is possible for attacker to tamper with cookies")
                              (vulnerability. "Fails to prevent Cross-site request forgery (CSRF)")
                              (vulnerability. "Framework support for mass binding can be exploited")
                              (vulnerability. "Lack of access control on resources not intended to be discoverable to user")
                              (vulnerability. "Alternate character encodings can be used to circumvent protections")
                              (vulnerability. "User forms can be completed in a scripted manner")
                              (vulnerability. "Lack of rate limiting allows 'scraping' or 'spidering' of valuable data")
                              (vulnerability. "URL paths can be manipulated to access system files")
                              (vulnerability. "URL paths can be manipulated to load and execute remote files")
                              (vulnerability. "URL paths can be manipulated to access unauthorised resources")
                              (vulnerability. "Developers have disabled framework security protections")
                              (vulnerability. "Sensitive to application layer denial of service")
                              (vulnerability. "Triggering an exception leaks unnecessary information that can assist attacker")
                              ))
                     (focus. "Implementation of access control"
                             (list
                              (vulnerability. "Authentication, authorisation or session management has been coded from scratch")
                              (vulnerability. "Authentication mechanism subject to brute force attack")
                              (vulnerability. "Input can be manipulated to enumerate all users")
                              (vulnerability. "Failure to allow use of password manager and strong credentials")
                              (vulnerability. "Failure to prevent users creating weak credentials")
                              (vulnerability. "An attacker can trivially guess session identifier")
                              (vulnerability. "Lack of protection against an attacker hijacking a session")
                              (vulnerability. "It is possible for attacker to tamper with session cookies")
                              (vulnerability. "Failure to check authorisation to more sensitive resources")
                              (vulnerability. "Failure to reauthenticate session when taking destructive actions, such as delete account")
                              (vulnerability. "Failure of session to timeout after a reasonable duration of time")
                              (vulnerability. "Failure to prevent caching of credentials on a shared computer")
                              (vulnerability. "Failure of user interface obscure entry of credentials")
                              (vulnerability. "Can authenticate with credentials harvested from other breaches")
                              ))
                     (focus. "Developers or Devops Users"
                             (list
                              (vulnerability. "Lack of peer review prior to deployment of code to production")
                              (vulnerability. "Lack of audit log showing user actions within delivery infrastructure")
                              (vulnerability. "Lack of integrity signatures on artefacts passing through delivery pipeline")
                              (vulnerability. "Lack of audit log showing user actions within delivery infrastructure")
                              (vulnerability. "Lack of access control based on least privledge in delivery infrastructure and scm")
                              (vulnerability. "Lack of access control based on least privledge to cloud consoles or APIs")
                              (vulnerability. "Lack of full disc encryption on devices containing configuration secrets or data")
                              (vulnerability. "Lack of policy and awareness for production secrets on developer devices")
                              (vulnerability. "Lack of policy and awareness for troubleshooting with copies of production data")
                              (vulnerability. "Lack of tooling to prevent pushing configuration secrets to source control")
                              (vulnerability. "Lack of static and other tooling in delivery pipeline to prevent error")
                              (vulnerability. "Lack of tests to verify functionality of security enforcing application code")
                              (vulnerability. "Failure to revoke access to delivery infrastructure rapidly when someone leaves")
                              (vulnerability. "Secrets are stored in plain text in source control")
                              (vulnerability. "Sensitive information is present in log files")
                              (vulnerability. "Lack of awareness of threat from phishing to developer devices")
                              (vulnerability. "Lack of production from malware on developer devices")
                              (vulnerability. "Poor morale or short term staff in production support roles")
                              ))
                     (focus. "Implementation of Mobile app"
                             (list
                              (vulnerability. "Guessable values such as IMEI number used in authentication")
                              (vulnerability. "Sensitive data stored in unencrypted storage")
                              (vulnerability. "Sensitive data is leaked into logs")
                              (vulnerability. "Sensitive data is stored in predictable locations in memory")
                              ))
                     (focus. "Implementation of Browser based app"
                             (list
                              (vulnerability. "Fails to prevent DOM based Cross Site Scripting (XSS)")
                              (vulnerability. "Scripts to display advertising contain malicious code")
                              (vulnerability. "Code injection is possible via JSON responses recieved from server")
                              (vulnerability. "Lack of Client Security Policy (CSP) configration allows loading of untrusted resources")
                              (vulnerability. "Lack of Cross Origin Resource Sharing (CORS) configration allows loading of untrusted resources")
                              (vulnerability. "Transfers between DOM contexts are subject to code injection")
                              (vulnerability. "Dependency on browser-based access control implementation")
                              (vulnerability. "Dependency on browser based business logic")
                              (vulnerability. "Authenticated data is not removed from browser storage when session ends")
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
