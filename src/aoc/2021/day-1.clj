(ns aoc.2021.day-1
  (:require [clojure.string :as str]))


(defn sum-x-next-list
  [list x aggregate]
  (if (< (count list) x)
    aggregate
    (sum-x-next-list (rest list) x (conj aggregate (reduce + (take x list))))))


(defn number-of-depth-increases-by-x
  [depth x]
  (let [summed-depths-by-x (sum-x-next-list depth x [])]
    (reduce (fn [increases depth-difference]
              (if (> depth-difference 0)
                (+ increases 1)
                increases))
            0
            (map - (rest summed-depths-by-x) (drop-last summed-depths-by-x)))))

(defn number-of-depth-increases-by-one
  [depth]
  (number-of-depth-increases-by-x depth 1))

(defn number-of-depth-increases-by-three
  [depth]
  (number-of-depth-increases-by-x depth 3))

(defn slurp-file-to-list
  []
  (map (fn [s] (Integer/parseInt s)) (str/split (slurp "src/aoc/2021/day-1.txt") #"\n")))

(comment
  (number-of-depth-increases-by-one (slurp-file-to-list))
  (number-of-depth-increases-by-three (slurp-file-to-list))
  )