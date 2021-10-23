(ns advent-2019.day02
  (:require [advent-2019.util :as util]
            [clojure.string :as str]))

(def inputf "data/day02-input.txt")

;; Test data
(def test0 [1 0 0 0 99])
(def test1 [2 3 0 3 99])
(def test2 [2 4 4 5 99 0])
(def test3 [1 1 1 4 99 5 6 0 99])

(defn read-data
  "Read in the data as a vector of numbers"
  [f]
  (as-> f <>
    (util/import-data <>)
    (first <>)
    (str/split <> #",")
    (mapv #(Integer/parseInt %) <>)))

(defn instr
  "Execute function f on the data at PC+1"
  [memory ip f]
  (let [[x y z :as params] (subvec memory (inc ip) (+ ip 4))
        [a b _] (map #(nth memory %) params)]
    (assoc memory z (f a b))))

(defn execute-instr
  "In the data, execute the next opcode pointed at by the PC, and return updated data."
  ;; execute :: [Int] -> Int -> [Int]
  [memory ip]
  (let [opcode (nth memory ip)]
    (case opcode
      1 (instr memory ip +)
      2 (instr memory ip *)
      99 memory)))

(defn run-code
  "Main execution loop"
  [memory]
  (let [init-ip 0
        max-instr 100]
    (reduce (fn [[st ip] _]
              (if (= 99 (nth st ip))
                (reduced st)
                ;;else
                [(execute-instr st ip) (+ ip 4)]))
            [memory init-ip]
            (range max-instr))))

(defn execute-program
  "Execute the program with the given initial values."
  [memory noun verb]
  (-> memory
      (assoc 1 noun)
      (assoc 2 verb)
      run-code
      first))

(defn find-target
  [memory n]
  (for [noun (range 0 50)
        verb (range 0 50)
        :let [result (execute-program memory noun verb)]
        :while (<= result n)]
    [noun verb]))

(defn part1
  [f]
  (-> f
      read-data
      (execute-program 12 2)))

(defn part2
  [f]
  (-> f
      read-data
      (find-target 19690720)
      last
      ((fn [[x y]] (+ (* 100 x) y)))))

;; The End
