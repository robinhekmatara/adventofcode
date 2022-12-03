(ns aoc.2022.day-3
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(def item-priority-map
  {"a" 1 "b" 2 "c" 3 "d" 4 "e" 5 "f" 6 "g" 7 "h" 8 "i" 9 "j" 10
   "k" 11 "l" 12 "m" 13 "n" 14 "o" 15 "p" 16 "q" 17 "r" 18 "s" 19
   "t" 20 "u" 21 "v" 22 "w" 23 "x" 24 "y" 25 "z" 26})

(defn item-priority
  [item]
  (let [priority (get item-priority-map (str/lower-case item))
        is-item-upper-case? (= item (str/upper-case item))]
    (if is-item-upper-case?
      (+ 26 priority)
      priority)))

(defn split-string-by-n
  [str n]
  (->> (str/split str #"")
       (partition n)
       (map str/join)))

(defn strings-common-characters
  [strings]
  (->> (apply set/intersection (map set strings))
       (into [])
       (map str)))

(defn rucksack-mispacked-item
  [rucksack]
  (let [compartment-size (/ (count rucksack) 2)
        compartments (split-string-by-n rucksack compartment-size)]
    (->> (strings-common-characters compartments)
         first
         )))

(defn sum-item-priorities
  [items]
  (->> (map item-priority items)
       (reduce + 0)))

(defn priority-sum-mispacked-items
  [rucksacks]
  (->> (map rucksack-mispacked-item rucksacks)
       sum-item-priorities))

(defn three-elf-groups
  [rucksacks]
  (partition 3 rucksacks))

(defn priority-sum-per-three-elf-group
  [rucksacks]
  (->> (three-elf-groups rucksacks)
       (map strings-common-characters)
       (map first)
       (map item-priority)
       (reduce + 0)))

(defn slurp-to-file-to-list
  []
  (-> (slurp "src/aoc/2022/day_3.txt")
      (str/split #"\n")))

(defn part1
  []
  (->>  (slurp-to-file-to-list)
        priority-sum-mispacked-items))

(defn part2
  []
  (->> (slurp-to-file-to-list)
       (priority-sum-per-three-elf-group)))

(comment
  (slurp-to-file-to-list)
  (item-priority "A"))
