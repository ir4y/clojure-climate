(ns clojure_climate.view
  (:require [me.raynes.laser :as l]
            [clojure.java.io :as io]))

(def main-html
  (l/parse
    (slurp (clojure.java.io/resource "resources/public/index.html"))))

(def article-row (l/select main-html (l/id= "article-row")))

(def form (l/select main-html (l/id= "test_code_form")))
(def result (l/select main-html (l/id= "test_code_result")))

(println result)
