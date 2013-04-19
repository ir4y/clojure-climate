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
    (let [source (slurp link)
          kibit-result (->> (java.io.StringReader. source)
                             kibit.check/check-reader)
          result-page  (view/main-page-result kibit-result source)]
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

