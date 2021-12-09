(ns aoc.2021.day-3 
  (:require [clojure.string :as str]))

(defn transpose
  [matrix]
  (apply map vector matrix))

(defn most-common-bit
  [bit]
  (let [x (- (get bit \0) (get bit \1))]
    (cond
    (< x 0) "one"
    (> x 0) "zero"
    :else "equal"
    )))
  
(defn gamma-rate-bit
  [bit]
  (case bit
    "zero" 0
    "one" 1))

(defn epsilon-rate-bit
  [bit]
  (case bit
    "zero" 1 
    "one" 0))

(defn oxygen-rate-bit
  [most-common-bit]
  (case most-common-bit
    "zero" 0
    "one" 1
    "equal" 1))

(defn co2-rate-bit
  [most-common-bit]
  (case most-common-bit
    "zero" 1
    "one" 0
    "equal" 0))

(defn gamma-rate
  [frequencies]
    (Integer/parseInt (apply str (map gamma-rate-bit frequencies)) 2))

(defn epsilon-rate
  [frequencies]
    (Integer/parseInt (apply str (map epsilon-rate-bit frequencies)) 2))

(defn rate
  [list current-bit-to-keep-fn]
  (loop [index 0
         result list]
    (if (empty? (rest result))
      (first result)
      (recur (+ 1 index)
             (let [current-bit-to-keep (current-bit-to-keep-fn (most-common-bit (frequencies (map #(nth %1 index) result))))]
               (filter (fn [current-binary-number] (= current-bit-to-keep (Character/digit (nth current-binary-number index) 10))) result))))))

(defn oxygen-generator-rating
  [list]
  (Integer/parseInt (rate list oxygen-rate-bit) 2))

(defn co2-scrubber-rating
  [list]
  (Integer/parseInt (rate list co2-rate-bit) 2))

(defn power-consumption
  [frequencies]
  (* (gamma-rate frequencies) (epsilon-rate frequencies)))

(defn life-support-rating
  [list]
  (* (oxygen-generator-rating list) (co2-scrubber-rating list)))

(def slurp-file-to-list
  (str/split (slurp "src/aoc/2021/day-3.txt") #"\n"))


(def most-common-bits
  (->>
   slurp-file-to-list
   (transpose)
   (map frequencies)
   (map most-common-bit)))

(comment
  (life-support-rating slurp-file-to-list)
  (power-consumption most-common-bits))