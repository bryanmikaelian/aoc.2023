(ns aoc.cubes
  (:require [clojure.java.io :as io]))

(def ^:private games
  (with-open [r (io/reader (io/resource "day2.txt"))]
    (let [c (line-seq r)
          d (into [] c)]
      d)))

(defrecord Game [red green blue])

(defn verify []
  (prn "day 2 part 1 answer" 0))
