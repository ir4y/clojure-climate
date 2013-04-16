(ns clojure-climate.view
  (:require [me.raynes.laser :as l]
            [clojure.java.io :as io]))

(def main-html
  (l/parse
    (slurp (clojure.java.io/resource "public/index.html"))))

(defn main-page []
  (l/document main-html))

(defn main-page-result [kibit-result source-code]
  (l/document main-html
              (l/id= "test_code_result") (l/content kibit-result)
              (l/id= "raw_code_id") (l/content source-code)))
