(ns aoc.2022.day-2
  (:require [clojure.string :as str]))

(def rock "rock")
(def paper "paper")
(def scissor "scissor")

(def lose "lose")
(def draw "draw")
(def win "win")


(defn opponent-shape
  [move]
  (case move
    "A" rock
    "B" paper
    "C" scissor))

(defn outcome
  [result]
  (case result
    "X" lose
    "Y" draw
    "Z" win))

(defn scissor-needed?
  [outcome opponent-shape]
  (or
   (and (= outcome lose) (= opponent-shape rock))
   (and (= outcome draw) (= opponent-shape scissor))
   (and (= outcome win) (= opponent-shape paper))))

(defn paper-needed?
  [outcome opponent-shape]
  (or
   (and (= outcome lose) (= opponent-shape scissor))
   (and (= outcome draw) (= opponent-shape paper))
   (and (= outcome win) (= opponent-shape rock))))

(defn my-shape
  [outcome opponent-move]
  (cond
    (scissor-needed? outcome opponent-move) scissor
    (paper-needed? outcome opponent-move) paper
    :else rock))

(defn shape-points
  [shape]
  (cond
    (= shape rock) 1
    (= shape paper) 2
    (= shape scissor) 3))

(defn result-points
  [outcome]
  (cond
    (= outcome draw) 3
    (= outcome win) 6
    :else 0))

(defn result-one-game
  [game]
  (let [outcome (game :outcome)
        my-shape (my-shape outcome (game :opponent-shape))]
    (+ (shape-points my-shape) (result-points outcome))))

(defn result
  [games]
  (reduce (fn [score game] (+ score  (result-one-game game))) 0 games))

(defn clean-data
  [data]
  (->> (str/split data #"\n")
       (map (fn [game-move] (str/split game-move #" ")))
       (map (fn [game-move] {:opponent-shape (opponent-shape (nth game-move 0))
                             :outcome (outcome (nth game-move 1))}))))

(defn slurp-to-file-to-list
  []
  (slurp "src/aoc/2022/day_2.txt"))

(defn main
  []
  (->> (slurp-to-file-to-list)
       (clean-data)
       (result)))

(comment
  (main)
  (clean-data (slurp-to-file-to-list))
  (result [{:opponent-shape "rock" :my-shape "paper"}]))