(ns clojure-climate.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [kibit.check :refer :all]
            [clojure-climate.view :as view]))

(defn main []
  (view/main-page))

(defn link-result [link]
  (let [link-reader (clojure.java.io/reader link)
        kibit-result (kibit.check/check-reader link-reader)
        link-reader (clojure.java.io/reader link)
        source-code (clojure.string/join "\n" (line-seq link-reader))]
  (view/main-page-result (apply str kibit-result) source-code)))

(defroutes app-routes
  (GET "/" [] (main))
  (POST "/" [github_link] (link-result github_link))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))

