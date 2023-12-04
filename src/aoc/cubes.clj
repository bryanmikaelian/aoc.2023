(ns aoc.cubes
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(def ^:private games
  (with-open [r (io/reader (io/resource "day2.txt"))]
    (let [c (line-seq r)
          d (into [] c)]
      d)))

(def ^:private id-re #"(?<=Game\s)\d+(?=:)")
(def ^:private rounds-re #"(?<=:\s)[a-z0-9\s,;]+")
(def ^:private red-score-re #"\d+(?=\sred)")
(def ^:private blue-score-re #"\d+(?=\sblue)")
(def ^:private green-score-re #"\d+(?=\sgreen)")

(def ^:private initial-game {:id nil :rounds []})
(def ^:private initial-score {:red 0 :blue 0 :green 0})
(def ^:private max-red 12)
(def ^:private max-green 13)
(def ^:private max-blue 14)

(defn- tally-round [round]
  (let [red (or (re-find red-score-re round) "0")
        blue (or (re-find blue-score-re round) "0")
        green (or (re-find green-score-re round) "0")
        scores (-> initial-score
                   (assoc :red (Integer/parseInt red))
                   (assoc :blue (Integer/parseInt blue))
                   (assoc :green (Integer/parseInt green)))]
    scores))

(defn- parse-round [game]
  (string/split (re-find rounds-re game) #"; "))

(defn- make-game [d]
  (let [id (re-find id-re d)
        round-data (parse-round d)
        rounds (into [] (map tally-round round-data))]
    (assoc initial-game :id (Integer/parseInt id) :rounds rounds)))

(defn- valid-round? [round]
  (let [{red :red
         green :green
         blue :blue} round]
    (and (<= red max-red)
         (<= green max-green)
         (<= blue max-blue))))

(defn- valid-game? [game]
  (let [{:keys [rounds]} game]
    (every? valid-round? rounds)))

(def ^:private valid-games (->> games
                                (map make-game)
                                (filter valid-game?)))

(defn verify []
  (prn "day 2 part 1 answer" (reduce + (map :id valid-games)))
  (prn "day 2 part 2 answer" 0))
