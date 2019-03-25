; --- Day 2: Inventory Management System ---
; https://adventofcode.com/2018/day/2
(ns advent_of_code.y2018.d2
  (:require [clojure.java.io :as io]
             [clojure.data]))

(def input
  (line-seq (io/reader (io/resource "y2018/d2.input"))))

(defn n-times? [n]
  (fn [coll]
    (not (empty? (filter #(= n (second %)) coll)))))

(def two-times? (n-times? 2))
(def three-times? (n-times? 3))

(defn inc-if-true
  [acc val]
  (if (true? val) (inc acc) acc))

(defn inc-vec-if-true
  [acc val]
  [(inc-if-true (first acc) (first val))
   (inc-if-true (second acc) (second val))])

; p1
(->> input
     (map #(frequencies (seq %)))
     (map (juxt two-times? three-times?))
     (reduce inc-vec-if-true [0 0])
     (reduce *))

;
; p2 TODO
;
;(defn diff-str
;  [str1 str2]
;  )
;
;(defn correct?
;  [str1 str2]
;  (= (count (diff-str str1 str2)) 1))
;
;(defn has-correct?
;  [str coll]
;  (filter #(correct? str %) coll))
;
;(defn map-correct?
;  [coll]
;  (map #(has-correct? % coll) coll))
;
;; p2
;(reduce
;  (fn [result item])
;  ""
;  input)
;