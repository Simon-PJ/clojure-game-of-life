(ns clojure-game-of-life.rules)

(defn generate-world
    "Generates a new game of life world from the given size"
    [size]
    (partition size (take (* size size) (repeatedly #(rand-int 2)))))

(defn get-cell-value
    [x y world]
    (if (and (>= x 0) (>= y 0) (< x (count (first world))) (< y (count world)))
        (nth (nth world y) x)))

(defn upper-left
    [x y world]
    (get-cell-value (dec x) (dec y) world))

(defn upper
    [x y world]
    (get-cell-value x (dec y) world))

(defn upper-right
    [x y world]
    (get-cell-value (inc x) (dec y) world))

(defn left
    [x y world]
    (get-cell-value (dec x) y world))

(defn right
    [x y world]
    (get-cell-value (inc x) y world))

(defn lower-left
    [x y world]
    (get-cell-value (dec x) (inc y) world))

(defn lower
    [x y world]
    (get-cell-value x (inc y) world))

(defn lower-right
    [x y world]
    (get-cell-value (inc x) (inc y) world))

(def neighbours [upper-left upper upper-right left right lower-left lower lower-right])

(defn neighbour-count
    [x y world]
    (count (filter #(= (% x y world) 1) neighbours)))

(defn enforce-rules
    "Any live cell with two or three live neighbours lives on to the next generation"
    [x y world]
    (let [num-neighbours (neighbour-count x y world)]
        (if (<= 2 num-neighbours 3)
            (if (= num-neighbours 3)
                1
                (get-cell-value x y world))
            0)))

(defn next-iteration
    "Calculates the next iteration of game of life from the previous iteration"
    [world]
    (map-indexed 
        (fn [row-index row] 
            (map-indexed 
                (fn [col-index cell] (enforce-rules col-index row-index world)) 
                row))
        world))