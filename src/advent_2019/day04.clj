(ns advent-2019.day04)

;; The puzzle input
(def input-range [124075 580769])

(defn int->vec
  "Convert an integer into a vector."
  [i]
  (mapv #(- (int %) (int \0))
        (seq (str i))))

(defn differences
  "Return the differences between adjacent pairs."
  [coll]
  (map #(- %2 %1) coll (rest coll)))

(defn test-3?
  "Must have two consecutive digits the same => at least one difference is zero."
  [coll]
  (pos-int? (count (filterv zero? coll))))

(defn test-4?
  "Must be static or monotonically increasing sequence => differences must be zero or positive."
  [coll]
  (every? nat-int? coll))

(defn test-5?
  "Cannot have three consecutive digits => cannot have _only_ two consecutive zero differences."
  [coll]
  (let [v (vals (frequencies coll))]
    (nat-int? (.indexOf v 2))))

(defn test-number?
  [n]
  (let [coll (differences (int->vec n))]
    (and (test-3? coll)
         (test-4? coll))))

(defn test-number-2?
  [n]
  (let [coll (differences (int->vec n))]
    (and (test-3? coll)
         (test-4? coll)
         (test-5? (int->vec n)))))

(defn part1
  [[start end]]
  (count (filter test-number? (range start (inc end)))))

(defn part2
  [[start end]]
  (count (filter test-number-2? (range start (inc end)))))

;; The End
