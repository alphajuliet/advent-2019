(ns advent-2019.day07
  (:require [advent-2019.util :as util]
            [advent-2019.day05 :as prog]
            [clojure.math.combinatorics :as combo]))

;;----------------
(def inputf "data/day07-input.txt")
(def test0f "data/day07-test0.txt")
(def test1f "data/day07-test1.txt")
(def test2f "data/day07-test2.txt")

(defn run-program
  "Run the given program with the input vector."
  [mem input]
  {:pre [(vector? input)]}
  (-> mem
      (prog/run-code input)
      :output
      last))

(defn thrust
  "Run an amplifier chain with the given phase vector."
  [prog phases]
  (reduce (fn [value phase]
            (run-program prog [phase value]))
          0
          phases))

(defn part1
  "Find the maximum thrust across all possible phase permutations."
  [f]
  (let [program (prog/read-program f)]
    (apply max (for [ph (combo/permutations [0 1 2 3 4])]
                 (thrust program ph)))))

;; The End
