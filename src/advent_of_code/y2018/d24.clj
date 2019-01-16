(ns advent-of-code.y2018.d24)

(def input
  {:immune-system [{:units      17
                    :hp         5390
                    :weak       #{:radiation :bludgeoning}
                    :attack     4507
                    :type       :fire
                    :initiative 2}
                   {:units      989
                    :hp         1274
                    :immune     #{:fire}
                    :weak       #{:bludgeoning :slashing}
                    :attack     25
                    :type       :slashing
                    :initiative 3}]
   :infection     [{:units      801
                    :hp         4706
                    :weak       #{:radiation}
                    :attack     116
                    :type       :bludgeoning
                    :initiative 1}
                   {:units      4485
                    :hp         2961
                    :immune     #{:radiation}
                    :weak       #{:fire :cold}
                    :attack     12
                    :type       :slashing
                    :initiative 4}]})

(defn effective-power
  [group]
  (* (:units group) (:attack group)))

(defn selection-rule
  [g1 g2]
  (let [ep1 (effective-power g1)
        ep2 (effective-power g2)]
    (if (= ep1 ep2)
      (> (:initiative g1) (:initiative g2))
      (> ep1 ep2))))

(defn sort-by-selection-rule
  [armies]
  (sort selection-rule armies))

(defn fill-camp
  [camp armies]
  (map #(assoc % :camp camp) armies))

(defn input->state
  [input]
  (sort-by-selection-rule (mapcat #(fill-camp % (% input)) (keys input))))

(defn enemy
  [army armies]
  (filter (fn [candidate] (not= (:camp candidate) (:camp army))) armies))

(defn predict-damage
  [army enemy]
  (if (contains? (:immune enemy) (:type army))
    0
    (if (contains? (:weak enemy) (:type army))
      (* (effective-power army) 2)
      (effective-power army))))

(defn damage-prediction
  [army enemies]
  (map (fn [enemy] (assoc enemy :predicted-damage (predict-damage army enemy))) enemies))

(defn target-selection
  "Return the list of attacker->defender"
  [army enemies]
  (damage-prediction army enemies))

(target-selection (first (:immune-system input)) (:infection input))

(enemy (first (input->state input)) input)

(map (fn [army] (target-selection army (enemy army input))) (input->state input))

;(defn attacking
;  "Return :immune-system and :infection after fighting."
;  [armies]
;  armies)
;
;(attacking (target-selection input))
