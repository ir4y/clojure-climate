(ns clojure-climate.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [kibit.check :refer :all]))


(raw-code-result [raw-code]
                 "hello code")

(link-result [link]
                 "hello link")
(defroutes app-routes
  (GET "/" [] (main))
  (POST "/" [raw_code] (raw-code-result raw-code-result))
  (POST "/" [github_link] (link-result github_link))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
