(ns advent-2019.day06
  (:require [advent-2019.util :as util]
            [clojure.string :as str]
            [ubergraph.alg :as alg]
            [ubergraph.core :as uber]))

;;----------------
(def inputf "data/day06-input.txt")
(def testf "data/day06-test.txt")

;;----------------
(defn read-data
  "Read in the orbits"
  [f]
  (->> f
      util/import-data
      (map #(str/split % #"\)"))))

;;----------------
(defn create-graph
  "Create a directed graph from the edges."
  [edges]
  (let [g (uber/digraph)]
    (uber/add-edges* g edges)))

(defn root-node
  "Find the root of the graph"
  [g]
  (first
   (filter #(zero? (uber/in-degree g %)) (uber/nodes g))))

(defn count-all-edges
  "Count all the edges from the root to all the leaf nodes."
  [g]
  (let [root (root-node g)]
    (util/sum
     (map #(:cost (alg/shortest-path g root %)) (uber/nodes g)))))

(defn part1
  [f]
  (-> f
      read-data
      create-graph
      count-all-edges))

;; The End
