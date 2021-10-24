(ns advent-2019.day03
  (:require [advent-2019.util :as util]
            [clojure.string :as str]))

(def inputf "data/day03-input.txt")
(def testf-1 "data/day03-test1.txt")
(def testf-2 "data/day03-test2.txt")

(defn read-data
  [f]
  (->> f
      util/import-data
      (map #(str/split % #","))))

(defn move
  "Given a point [x y] and a movement, return the endpoint"
  ;; move :: Point -> Movement -> Point
  [[x y] m]
  (let [dir (subs m 0 1)
        dist (Integer/parseInt (subs m 1))]
    (case dir
      "U" [x (+ y dist)]
      "R" [(+ x dist) y]
      "D" [x (- y dist)]
      "L" [(- x dist) y]
      [x y])))

(defn waypoints
  "Convert a path into a set of [x y] waypoints"
  [path]
  (reduce (fn [pts path]
            (conj pts (move (last pts) path)))
          [[0 0]]
          path))

(defn segments
  "Convert a set of waypoints into line segments"
  [pts]
  (partition 2 1 pts))

(defn sort-pair
  "Quick pair sort"
  [[x y]]
  (if (> x y) [y x] [x y]))

(defn overlap
  "Determine the overlap of two ranges"
  [r1 r2]
  (let [[a1 a2] (sort-pair r1)
        [b1 b2] (sort-pair r2)]
    (- (min a2 b2) (max a1 b1))))

(defn intersect
  "Find the point of intersection between two line segments in the plane.
  If one doesn't exist then return false."
  [[[x1 y1] [x2 y2]] [[x3 y3] [x4 y4]]]
  (let [ovx (overlap [x1 x2] [x3 x4])
        ovy (overlap [y1 y2] [y3 y4])]
    (if (and (zero? ovx) (zero? ovy))
      (if (= x1 x2)
        [x1 y3]
        [x3 y1])
      false)))

(defn path-intersections
  "Return the intersections between the two paths"
  [[p1 p2]]
  (for [s1 (-> p1 waypoints segments)
        s2 (-> p2 waypoints segments)]
    (intersect s1 s2)))

(defn d-manhattan
  "Manhattan distance"
  [[x y]]
  (+ (Math/abs x) (Math/abs y)))

(defn part1
  [f]
  (->> f
       read-data
       path-intersections
       (filter vector?)
       rest
       (map d-manhattan)
       (apply min)))

;; The End
