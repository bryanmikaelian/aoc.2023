(ns aoc.core
  (:require [aoc.calibrate :as calibrate]))

(defn run [_]
  (prn "aoc 2023 solutions")
  (prn "------------------")
  (calibrate/run))

(comment
  (run nil))
