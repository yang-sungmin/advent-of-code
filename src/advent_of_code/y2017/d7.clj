(ns advent_of_code.y2017.d7
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input
  (line-seq (io/reader (io/resource "y2017/d7.input"))))

(defn ->name
  [str]
  (first (str/split str #"\s\(")))

(defn ->weight
  [str]
  (Integer. (str/replace (last (str/split str #"\s\(")) #"\)" "")))

(defn ->tower
  ([program]
   {:name (->name program)
    :weight (->weight program)})
  ([program holdings]
   {:name (->name program)
    :weight (->weight program)
    :holdings (str/split holdings #",\s+")}))

(defn parse
  [line]
  (apply ->tower (str/split line #" -> ")))

( ->> input
  (map parse)
  (filter :holdings))
