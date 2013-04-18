(ns clojure-climate.model
    (:require [taoensso.carmine :as car]))

(def pool         (car/make-conn-pool)) ; See docstring for additional options
(def spec-server1 (car/make-conn-spec))

(defmacro wcar [& body] `(car/with-conn pool spec-server1 ~@body))

(defn get_links []
  (wcar (car/lrange "links" 0 -1)))

(defn push_link [link]
  (when (> (wcar (car/llen "links")) 40)
        (wcar (car/rpop "links")))
  (wcar (car/lpush "links" link)))
