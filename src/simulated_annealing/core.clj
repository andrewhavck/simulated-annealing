(ns simulated-annealing.core
	(:import [java.lang.Math])
	(:gen-class :main true))

(declare abs-diff)
(declare gen-city)
(declare pretty-decimal)

(defprotocol Point
	(distance-to [this city])
	(to-string [this]))

(defprotocol PointCollection
	(total-distance [this]))

(defrecord City [x y]
	Point
	(distance-to [this city] 
		(let [xdist (abs-diff (:x this) (:x city))
			ydist (abs-diff (:y this) (:y city))]
			(Math/sqrt (+ (* xdist xdist) (* ydist ydist)))))
	(to-string [this] (str "(" (pretty-decimal (:x this)) ", " (pretty-decimal (:y this)) ")")))
	
(defrecord Tour [cities]
	PointCollection
	(total-distance [this]
		(let [cities (:cities this)
			final (distance-to (first cities) (last cities))]
			(loop [curr cities distances '()]
				(if (empty? (rest curr))
					(reduce + (conj distances final))
					(recur (rest curr) (conj distances (distance-to (first curr) (second curr))))
					)))))

(defn random-dec [limit] (* (Math/random) limit))

(defn random-int [limit] (int (random-dec limit)))

(defn abs-diff [p1 p2] (Math/abs (- p1 p2)))

(defn gen-city [] (City. (random-dec 200) (random-dec 200)))

(defn pretty-decimal [num] (format "%.2f" (float num)))

(defn swap [coll pos1 pos2]
	(let [item1 (nth coll pos1)
		item2 (nth coll pos2)]
		 (assoc (assoc coll pos1 item2) pos2 item1)))

(defn acceptance [e new-e temp]
	(cond (< new-e e) 1.0
		  :else (Math/exp (/ (- e new-e) temp))))

(defn random-tour [number-of-cities] (Tour. (vec (take number-of-cities (repeatedly #(gen-city))))))

(defn -main [& args]
	(let [tour-size 20
		initial-tour (random-tour tour-size)
		cooling 0.003
		optimal-tour
		(loop  [best-tour initial-tour
			temp 10000]
			(let [pos1 (random-int tour-size)
				pos2 (random-int tour-size)
				new-temp (* temp (- 1 cooling))
				new-tour (Tour. (swap (:cities best-tour) pos1 pos2))]

				(if (> 1 temp)
					best-tour

			  		(if (> (acceptance (total-distance best-tour) (total-distance new-tour) temp) (Math/random))
			  		  (recur new-tour new-temp)
			  		  (recur best-tour new-temp)))))]

		  (println (str "Initial tour distance: " (pretty-decimal (total-distance initial-tour))))
		  (println (str "Tour: " (apply str (map to-string (:cities initial-tour)))))
		  (println)

		  (println (str "Optimal tour distance: " (pretty-decimal (total-distance optimal-tour))))
		  (println (str "Tour: " (apply str (map to-string (:cities optimal-tour)))))
	))
