(ns aoc.2022.day-1
  (:require [clojure.string :as str]))


(defn total-elf-workload
  [elf-workload]
  (reduce + 0 elf-workload))

(defn total-elf-workload-for-all-elfs
  [elf-workload-for-all-elfs]
  (map total-elf-workload elf-workload-for-all-elfs))


(defn max-n-elf-workload
  [total-elf-workload-for-all-elfs n]
  (->> (sort total-elf-workload-for-all-elfs)
       (reverse)
       (take n)
       (reduce + 0)))

(defn clean-data
  [data]
  (->> (str/split data #"\n\n")
       (map (fn [elf-workload] (str/split elf-workload #"\n")))
       (map (fn [elf-workload] (map (fn [partial-elf-workload] (Integer/parseInt partial-elf-workload)) elf-workload)))))

(defn slurp-file-to-list
  []
  (slurp "src/aoc/2022/day_1.txt"))

(defn part1
  []
  (-> (slurp-file-to-list)
      (clean-data)
      (total-elf-workload-for-all-elfs)
      (max-n-elf-workload 1)))

(defn part2
  []
  (-> (slurp-file-to-list)
      (clean-data)
      (total-elf-workload-for-all-elfs)
      (max-n-elf-workload 3)))


(comment
  (part1)
  (part2))