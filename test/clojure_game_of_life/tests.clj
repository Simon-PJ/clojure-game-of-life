(ns clojure-game-of-life.tests
    (:use clojure.test)
    (:use clojure-game-of-life.rules))

(def dead-world [[0 0 0] [0 0 0] [0 0 0]])

(deftest world-gen
    (testing "Creates a 1x1 world" 
        (let [world (generate-world 1)]
            (is (= (count world) 1))
            (is (= (count (first world)) 1))))
            
    (testing "Creates a 5x5 world"
        (let [world (generate-world 5)]
            (is (= (count world) 5))
            (is (= (count (first world)) 5)))))

(deftest rule-one
    "Any live cell with fewer than two live neighbours dies, as if by underpopulation"
    
    (testing "No neighbours"
        (let [world [[0 0 0] [0 1 0] [0 0 0]]]
            (is (= (next-iteration world) dead-world))))
            
    (testing "One neighbour"
        (let [world [[0 0 0] [0 1 1] [0 0 0]]]
            (is (= (next-iteration world) dead-world))))
            
    (testing "Corner cell"
        (let [world [[1 0 0] [0 0 0] [0 0 0]]]
            (is (= (next-iteration world) dead-world)))))

(deftest rule-two
    "Any live cell with two or three live neighbours lives on to the next generation"
    
    (testing "Two neighbours"
        (let [world [[0 0 0] [1 1 1] [0 0 0]]]
            (is (= (next-iteration world) [[0 0 0] [0 1 0] [0 0 0]])))))