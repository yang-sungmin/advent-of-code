(ns advent_of_code.y2018.d1
  (:require [clojure.java.io :as io]))

(def input
  (line-seq (io/reader (io/resource "y2018/d1.input"))))

; p1
(apply + (map read-string input))

; p2
(reduce
  (fn [result freq]
    (let [curr (+ (last result) freq)]
      (if (some #(= curr %) result)
        (reduced curr)
        (conj result curr))))
  [0]
  (cycle (map read-string input)))
