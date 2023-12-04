(ns aoc.core
  (:require [aoc.calibrate :as calibrate]
            [aoc.cubes :as cubes]))

(defn run [_]
  (prn "aoc 2023 solutions")
  (prn "------------------")
  (calibrate/run)
  (cubes/verify))

(comment
  (run nil))
