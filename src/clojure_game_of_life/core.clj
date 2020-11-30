(ns clojure-game-of-life.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [clojure-game-of-life.rules :as g]))

(def world-size 50)
(def cell-size 10)

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :rgb)
  (g/generate-world world-size))

(defn update-state [state]
  (g/next-iteration state))

(defn draw-state [state]
  (q/background 0)
  (q/fill 250 0 0)
  (q/no-stroke)
  (doseq [x (range 0 world-size)]
    (doseq [y (range 0 world-size)]
      (if (= (g/get-cell-value x y state) 1)
        (q/rect (* x cell-size) (* y cell-size) cell-size cell-size)))))

(q/defsketch clojure-game-of-life
  :title "Game of Life"
  :size [500 500]
  :setup setup
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
