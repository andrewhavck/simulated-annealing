(ns simulated-annealing.core-test
  (:require [midje.sweet :refer :all])
  (:require [simulated-annealing.core :refer :all])
  (:import [simulated_annealing.core City Tour]))

(defn round [d precision]
  (let [factor (Math/pow 10 precision)]
    (/ (Math/floor (* d factor)) factor)))

(fact "Take absolute value of difference"
	(abs-diff 1 3) => 2)

(fact "Returns a decimal larger than 0 and less than 200"
	(random-dec 200) => (and decimal? (roughly 0 200)))

(fact "Returns an int larger than -1 and less than 20"
	(random-int 20) => (and integer? (roughly 0 20)))

(fact "Creates a city"
	(gen-city) => (every-checker #(instance? City %)))

(fact "Calculates the distance between two cities"
	(let [city1 (City. 140 98)
		city2 (City. 34 87)]
		(round (distance-to city1 city2) 2) => 106.56))

(fact "Prints a cities coordinates"
	(to-string (City. 140 98)) => "(140.00, 98.00)")

(fact "Creates a tour"
	(random-tour 1) => (every-checker #(instance? Tour %)))

(fact "Creates a random tour of specified number of cities"
	(:cities (random-tour 10)) => (n-of #(instance? City %) 10))

(fact "When the new energy is less than the current energy return 1.0"
	(acceptance 2 1 20) => 1.0)

(fact "When the new energy is greater than the current energy return change probability"
	(round (acceptance 4 6 10) 2) => 0.81)

(fact "Swaps two items in a list"
	(swap [1 2 3] 0 2) => [3 2 1])

(fact "Returns the total tour size"
	(let [city1 (City. 140 98)
		city2 (City. 34 87)
		city3 (City. 78 190)
		cities [city1 city2 city3]]
		(round (total-distance (Tour. cities)) 2) => 329.51))

(fact "Formats decimal string"
	(pretty-decimal 20.312) => "20.31")