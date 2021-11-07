(ns advent-2019.day07
  (:require [advent-2019.day05 :as prog]
            [clojure.math.combinatorics :as combo]))

;;----------------
(def inputf "data/day07-input.txt")
(def test0f "data/day07-test0.txt")
(def test1f "data/day07-test1.txt")
(def test2f "data/day07-test2.txt")
(def test3f "data/day07-test3.txt")
(def test4f "data/day07-test4.txt")

;;----------------
;; Convenience method for showing a queue.
(defmethod print-method clojure.lang.PersistentQueue
  [q w]
  (print-method '<- w)
  (print-method (seq q) w)
  (print-method '-< w))

;;----------------
(defn run-program
  "Run the given program with the input vector."
  [mem input out]
  {:pre [(vector? input)]}
  (-> mem
      (prog/run-code input out)
      :output
      last))

(defn series-thrust
  "Run a simple amplifier chain with the given phase vector."
  [prog phases]
  (reduce (fn [value phase]
            (run-program prog [value phase] []))
          0
          phases))

;;----------------
(defn make-state
  [mem input]
  {:pre [(vector? input)]}
  {:mem mem :ip 0 :input input :output []})


(defn feedback-thrust
  "Run an amplifier chain with feedback loop to input."
  [prog phases])

;;----------------
(defn part1
  "Find the maximum thrust across all possible phase permutations."
  [f]
  (let [program (prog/read-program f)]
    (apply max (for [ph (combo/permutations [0 1 2 3 4])]
                 (series-thrust program ph)))))

;; The End
