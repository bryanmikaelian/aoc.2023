(ns aoc.calibrate
  (:require [clojure.java.io :as io]
            [clojure.string :as string])

  (:import java.lang.Integer))

(def ^:private strings [["one" 1]
                        ["two" 2]
                        ["three" 3]
                        ["four" 4]
                        ["five" 5]
                        ["six" 6]
                        ["seven" 7]
                        ["eight" 8]
                        ["nine" 9]])

(def ^:private numbers [["1" 1]
                        ["2" 2]
                        ["3" 3]
                        ["4" 4]
                        ["5" 5]
                        ["6" 6]
                        ["7" 7]
                        ["8" 8]
                        ["9" 9]])

(def ^:private mapping (into {} (concat strings numbers)))
(def ^:private num-matcher (re-pattern (str "(?=(\\d|" (string/join "|" (map first strings)) "))")))

(def ^:private raw-calibrations
  (with-open [r (io/reader (io/resource "day1.txt"))]
    (let [c (line-seq r)
          d (into [] c)]
      d)))

(defn- numbers-only [s]
  (re-seq #"\d" s))

(defn- numberlike-values [s]
  (map last (re-seq num-matcher s)))

(defn- join-digits [s]
  (let [f (first s)
        l (last s)]
    (Integer/parseInt (string/join [f l]))))

(defn convert [s]
  (join-digits (map mapping s)))

(def ^:private digits-only (map numbers-only raw-calibrations))
(def ^:private all-num-like-values (map numberlike-values raw-calibrations))

(defn- calibrate [nums]
  (reduce + (map convert nums)))

(defn run []
  (prn "day 1 part 1 answer" (calibrate digits-only))
  (prn "day 1 part 2 answer" (calibrate all-num-like-values)))
