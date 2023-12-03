(ns aoc.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str])

  (:import java.lang.Character
           java.lang.Integer)

  (:gen-class))

(def ^:private raw-calibrations
  (with-open [r (io/reader (io/resource "day1.txt"))]
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

(defn run []
  (prn "aoc 2023 solutions")
  (prn "------------------")
  (prn "day 1 part 1 answer" (calibration-total))
  (prn "day 1 part 2 answer" (calibration-total)))

(comment
  (run))
