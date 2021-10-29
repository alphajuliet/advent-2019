(ns advent-2019.day05
  (:require [advent-2019.util :as util]
            [clojure.spec.alpha :as s]
            [clojure.string :as str]
            [spec-dict :refer [dict]]))

(s/def ::memory (s/coll-of int?))
(s/def ::state (dict {:mem ::memory :ip int? :input int? :output (s/coll-of int?)}))
(s/def ::instr (dict {:op int? :m1 int? :m2 int? :m3 int?}))

(def inputf "data/day05-input.txt")
(def test0f "data/day05-test0.txt")

(defn read-data
  "Read in the data as a vector of numbers"
  ;; read-data :: String -> IO [Int]
  [f]
  (as-> f <>
    (util/import-data <>)
    (first <>)
    (str/split <> #",")
    (mapv #(Integer/parseInt %) <>)))

(defn fetch-data
  "Return data depending on the mode."
  ;; fetch-data :: [Int] -> Int -> Int -> Int
  [mem param mode]
  (case mode
    0 (nth mem param) ; position
    1 param))         ; immediate

(defn decode-instr
  "Decode the command into opcode and modes"
  ;; decode-instr :: Int -> Instr
  [instr]
  (let [opcode (mod instr 100)
        m1 (mod (int (/ instr 100)) 10)
        m2 (mod (int (/ instr 1000)) 10)
        m3 (mod (int (/ instr 10000)) 10)]
    {:op opcode :modes [m1 m2 m3]}))

(defn apply-fn
  "Execute function f on the data at IP+1"
  ;; apply-fn :: State -> [Int] -> (Int -> Int -> Int) -> State
  [{:keys [mem ip]} {:keys [modes]} f]
  (let [[_ _ z :as params] (subvec mem (inc ip) (+ ip 4))
        [a b _] (map #(fetch-data mem %1 %2) params modes)]
    {:mem (assoc mem z (f a b))
     :ip (+ ip 4)}))

(defn store-input
  "Take a value from the input and write it to a location."
  ;; store-input :: State -> State
  [{:keys [mem ip input] :as state} _]
  (let [z (nth mem (inc ip))]
    (-> state
        (assoc-in [:mem z] input)
        (update :ip #(+ % 2)))))

(defn write-value
  "Write a value"
  [{:keys [mem ip] :as state} {:keys [modes]}]
  (let [p (nth mem (inc ip))
        z (fetch-data mem p (first modes))]
    (-> state
        (update :output #(conj % z))
        (update :ip #(+ % 2)))))

(defn exec-instr
  "In the data, execute the next instruction pointed at by the IP, and return updated state."
  ;; execute :: State -> State
  [{:keys [mem ip] :as state}]
  (let [{:keys [:op] :as instr} (decode-instr (nth mem ip))]
    ;; (println instr)
    (case op
      1 (apply-fn state instr +)
      2 (apply-fn state instr *)
      3 (store-input state instr)
      4 (write-value state instr)
      99 state)))

(defn run-code
  "Main execution loop"
  [memory]
  (let [init-ip 0
        max-instr 200]
    (reduce (fn [{:keys [mem ip] :as state} _]
              (if (= 99 (nth mem ip))
                (reduced state)
                ;;else
                (exec-instr state)))
            {:mem memory :ip init-ip :output [] :input 1}
            (range max-instr))))

(defn part1
  [f]
  (-> f
      read-data
      run-code
      :output))

;; The End
