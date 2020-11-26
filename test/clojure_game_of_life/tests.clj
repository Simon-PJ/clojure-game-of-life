(ns clojure-game-of-life.tests
    (:use clojure.test)
    (:use clojure-game-of-life.rules))

(deftest world-gen
    (testing "Creates a 1x1 world" 
        (let [world (generate-world 1)]
            (is (= (count world) 1))
            (is (= (count (first world)) 1))))
            
    (testing "Creates a 5x5 world"
        (let [world (generate-world 5)]
            (is (= (count world) 5))
            (is (= (count (first world)) 5)))))