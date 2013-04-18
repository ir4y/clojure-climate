(ns clojure-climate.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [kibit.check :refer :all]
            [clojure-climate.view :as view]
            [clojure-climate.model :as model]
            [clojure-climate.utils :as utils]))

(defn link-result [link]
  (try
    (let [link-reader  (clojure.java.io/reader link)
          kibit-result (kibit.check/check-reader link-reader)
          link-reader  (clojure.java.io/reader link)
          source-code  (clojure.string/join "\n" (line-seq link-reader))
          result-page  (view/main-page-result kibit-result source-code)]
      (model/push_link link)
      result-page)
 (catch Exception e (view/main-page (pr-str e)))))


(defroutes app-routes
  (GET "/" [] (view/main-page))
  (POST "/" [github_link] (link-result github_link))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))

