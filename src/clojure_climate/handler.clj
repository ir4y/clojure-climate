(ns clojure-climate.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [kibit.check :refer [check-expr]]))

(defn form [expr]
  (str 
    "<form method='POST'>
    <input name='expr' value='" expr "'>
    <input type='submit' value='submit'>"
    (check-expr (read-string expr))
    "</form>"))

(defroutes app-routes
  (GET "/" [] (form (str "()")))
  (POST "/" [expr] (form expr))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))

(form "")
