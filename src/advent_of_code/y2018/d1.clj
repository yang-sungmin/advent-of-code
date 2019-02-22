(ns advent_of_code.y2018.d1
  (:require [clojure.java.io :as io]))

(def input
  (line-seq (io/reader (io/resource "y2018/d1.input"))))

; p1
(apply + (map read-string input))

; p2
(time
  (reduce
    (fn [result freq]
      (let [curr (+ (or (last result) 0) freq)]
        (if (some #(= curr %) result)
          (reduced curr)
          (conj result curr))))
    []
    (cycle (map read-string input))))

; "Elapsed time: 432965.000471 msecs"

; from namenu util.cljc
; https://github.com/namenu/advent-of-code/blob/master/src/util.cljc
(defn first-duplicate
  ([xs]
   (first-duplicate identity xs))
  ([key-fn xs]
   (let [result (reduce (fn [seen x]
                          (let [k (key-fn x)]
                            (if (seen k)
                              (reduced x)
                              (conj seen k))))
                        #{} xs)]
     (if (set? result)
       nil
       result))))

(time
  (first-duplicate (reductions + (cycle (map read-string input)))))

; "Elapsed time: 174.588303 msecs"
