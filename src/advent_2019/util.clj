(ns advent-2019.util
  (:require [clojure.string :as str]
            [clojure.pprint :as pp]))

(defn import-data
  "Import and prepare the data"
  ;; import-data :: IO File -> List String
  [f]
  (->> f
       (slurp)
       (str/split-lines)))

(defn swap [f x y] (f y x))
(defn sum [coll] (reduce + 0 coll))

(defn argmin
  "Return the value x in xs that minimises (f x)."
  [f xs]
  (apply min-key f xs))

;; filter-if :: ∀ a. List Boolean -> List a -> List a
(defn filter-if
  "Filter c2 according to the truth of the corresponding element in c1.
  (filter-if [true false true] [1 2 3]) => [1 3]"
  [c1 c2]
  (remove nil? (map #(if %1 %2) c1 c2)))

(defn delete
  [coll elt]
  (remove #(= % elt) coll))

;; Transpose a list of lists
(def T (partial apply map list))

;; number-of-bits :: Number -> Integer
(defn number-of-bits
  "Number of bits required to store `n`."
  [n]
  (if (zero? n) 0
      (-> (Math/log n)
            (/ (Math/log 2))
            inc
            Math/floor
            int)))

(defn leftpad
  "If S is shorter than LEN, pad it with CH on the left."
  ([s len] (leftpad s len " "))
  ([s len ch]
   (pp/cl-format nil (str "~" len ",'" ch "d") (str s))))

(defn rotate
  "Rotate collection by n to the left. If n is negative rotates to the right."
  [n coll]
  (let [shift (mod n (count coll))]
    (into [] (concat (drop shift coll)
                     (take shift coll)))))

(defn rotate-string
  "Rotate a string."
  [n s]
  (apply str (rotate n s)))

(defn swap-elements
  "Swap elements i and j in coll."
  [coll i j]
  (let [a (nth coll i)
        b (nth coll j)]
    (-> coll
        (assoc i b)
        (assoc j a))))

(defn count-if
  "Utility function"
  [f v]
  (count (filter f v)))

(defn take-until
  "Returns a lazy sequence of successive items from coll until
   (pred item) returns true, including that item. pred must be
   free of side-effects."
  [pred coll]
  (lazy-seq
    (when-let [s (seq coll)]
      (if (pred (first s))
        (cons (first s) nil)
        (cons (first s) (take-until pred (rest s)))))))

(defn mapmap
  "Do a deep map of 2-level structure."
  [f x]
  (map (fn [y] (map f y)) x))

(defn map-kv
  "Map over a map."
  [f coll]
  (reduce-kv (fn [m k v] (assoc m k (f v))) (empty coll) coll))

;; The End
