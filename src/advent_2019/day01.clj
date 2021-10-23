(ns advent-2019.day01
  (:require [advent-2019.util :as util]))

(def inputf "data/day01-input.txt")

(defn fuel
  "Convert mass to fuel."
  [mass]
  (max 0 (- (int (Math/floor (/ mass 3))) 2)))

(defn all-fuel
  "Recursively add fuel until it converges"
  [mass]
  (let [f (fuel mass)]
    (if (= f mass)
      mass
      (+ f (all-fuel f)))))

(defn part1
  [f]
  (->> f
       util/import-data
       (map #(Integer/parseInt %))
       (map fuel)
       util/sum))

(defn part2
  [f]
  (->> f
       util/import-data
       (map #(Integer/parseInt %))
       (map all-fuel)
       util/sum))

;; The End
