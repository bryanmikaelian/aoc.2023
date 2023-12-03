(ns aoc.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str])

  (:import Character
           Integer)

  (:gen-class))

(def d1-input (io/resource "day1.txt"))


(def raw-calibrations
  (with-open [r (io/reader d1-input)]
    (let [c (line-seq r)
          d (into [] c)]
      d)))

(defn- strip [s]
  (apply str (filter #(Character/isDigit %) s)))

(defn- join-digits [s]
  (let [f (first s)
        l (last s)]
   (Integer/parseInt (str/join [f l]))))

(defn- calibration-total []
  (let [nums (map strip raw-calibrations)
        calibrations (map join-digits nums)]
    (reduce + calibrations)))

(defn -main [_]
  (calibration-total))
