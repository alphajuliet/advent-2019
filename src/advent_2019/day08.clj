(ns advent-2019.day08
  (:require [advent-2019.util :as util]
            [clojure.string :as str]
            [clojure.core.matrix :as m]))

;;----------------
(def inputf "data/day08-input.txt")
(def input-dims [100 6 25])
(def testf "data/day08-test.txt")
(def test-dims [2 2 3])
(def test2f "data/day08-test2.txt")
(def test2-dims [4 2 2])

;;----------------
(defn read-image
  "Read in the image data."
  [f]
  (as-> f <>
    (util/import-data <>)
    (first <>)
    (str/split <> #"")
    (map #(Integer/parseInt %) <>)))

(defn split-layers
  "Split the vector into a 3D tensor of layers."
  [lst dims]
  (-> lst
      m/matrix
      (m/reshape dims)))

(defn count-eq
  "Count the a's in the tensor m."
  [t a]
  (as-> t <>
    (m/eq <> a)
    (m/eseq <>)
    (filter pos? <>)
    (count <>)))

(defn min-zeros
  "Find the slice with the minimum number of zeros"
  [t]
  (let [m (->> t
               (map #(count-eq % 0))
               (util/argmin identity)
               (nth t))]
    (* (count-eq m 1)
       (count-eq m 2))))

(defn merge-pixels
  "Merge the "
  [p1 p2]
  (if (= p1 2) p2 p1))

(defn merge-layers
  [t1 t2]
  (m/emap merge-pixels t1 t2))

(defn part1
  [f dims]
  (as-> f <>
    (read-image <>)
    (split-layers <> dims)
    (min-zeros <>)))

(defn part2
  [f dims]
  (as-> f <>
    (read-image <>)
    (split-layers <> dims)
    (reduce merge-layers <>)))

;; The End
