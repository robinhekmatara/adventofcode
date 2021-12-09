(ns aoc.2021.day-2
  (:require [clojure.string :as str]))

(defn position
  [{horizontal :horizontal depth :depth aim :aim}
   [direction amount]]
  (case direction
    "forward" {:horizontal (+ horizontal amount) :depth (+ depth (* aim amount)) :aim aim}
    "down" {:horizontal horizontal :depth depth  :aim (+ aim amount)}
    "up" {:horizontal horizontal :depth  depth :aim (- aim amount)}))

(defn end-position
  [course]
  (reduce position {:horizontal 0 :depth 0 :aim 0} course))

(def slurp-file-to-list
  (->>
   (str/split (slurp "src/aoc/2021/day-2.txt") #"\n")
   (map (fn [s] (str/split s #" ")))
   (map (fn [[s1 s2]] [s1 (Integer/parseInt s2)]))))

(comment
  slurp-file-to-list
  (end-position slurp-file-to-list)
  )


