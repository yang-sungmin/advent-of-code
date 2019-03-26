; --- Day 2: Inventory Management System ---
; https://adventofcode.com/2018/day/2
(ns advent_of_code.y2018.d2
  (:require [clojure.java.io :as io]))

(def input (->> "y2018/d2.input" io/resource io/reader line-seq))

(defn n-times? [n]
  (fn [coll]
    (not (empty? (keep #{n} coll)))))

(def two-times? (n-times? 2))
(def three-times? (n-times? 3))

(defn inc-true [acc val]
  (if (true? val) (inc acc) acc))

(defn inc-true-vec [acc val]
  [(inc-true (first acc) (first val))
   (inc-true (second acc) (second val))])

; p1
(->> input
     (map frequencies)
     (map vals)
     (map (juxt two-times? three-times?))
     (reduce inc-true-vec [0 0])
     (reduce *))

; p2
(defn count-n-diff [n str1 str2]
  (reduce
    (fn [acc item]
      (if (not= (first item) (second item))
        (inc acc)
        acc))
    0
    (partition n (interleave str1 str2))))

(defn get-n-diff [n str coll]
  (remove false? (keep #(if (= (count-n-diff n str %) 1) % false) coll)))

(defn find-n-diff [n coll]
  (map #(get-n-diff n % coll) coll))

(defn correct?
  [input]
  (apply str (map first (filter #(= (first %) (second %)) (partition 2 (apply interleave (flatten (find-n-diff 2 input))))))))

(correct? input)
