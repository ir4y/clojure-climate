(ns clojure-climate.view
  (:require [me.raynes.laser :as l]
            [clojure.java.io :as io]
            [clojure-climate.model :as model]))

(def main-html
  (l/parse
    (slurp (clojure.java.io/resource "public/index.html"))))

(def test-code-result (l/select main-html (l/id= "test_code_result")))
(def result-item (l/select main-html (l/class= "result-item")))
(def result-header (l/select main-html (l/class= "result-header")))

(l/defragment new-result-item result-item [kibit]
  (l/class= "span1") (l/content (str (:line kibit)))
  (l/class= "span6") (l/content (apply str (:expr kibit)))
  (l/class= "span5") (l/content (apply str (:alt kibit))))

(defn get_link_tag [link] (l/node :li :content (l/node :a :attrs {:href link} :content link)))

(defn main-page []
  (let [links (model/get_links)]
    (l/document main-html
              (l/id= "nav_links") (l/content (for [link links]
                                               (get_link_tag link))))))

(defn main-page-result [kibit-result source-code]
  (let [links (model/get_links)]
  (l/document main-html
              (l/id= "test_code_result") (l/content (concat [result-header] (for [kibit kibit-result]
                                                                               (new-result-item kibit))))
              (l/id= "raw_code_id") (l/content source-code)
              (l/id= "nav_links") (l/content (for [link links]
                                               (get_link_tag link))))))
