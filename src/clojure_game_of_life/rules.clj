(ns clojure-game-of-life.rules)

(defn generate-world
    "Generates a new game of life world from the given size"
    [size]
    (map #(repeat size %) (repeat size 0)))