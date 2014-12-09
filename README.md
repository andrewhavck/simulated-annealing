# simulated-annealing

A simple demonstration of simulated annealing applied to the traveling salesman problem in Clojure. This code is by no means pretty and was created as a learning experience.

Project heavily based off [this tutorial](http://www.theprojectspot.com/tutorial-post/simulated-annealing-algorithm-for-beginners/6) with examples in Java.

Building and running:

```
lein uberjar

java -jar target/simulated-annealing-0.1.0-SNAPSHOT-standalone.jar
```

The application should generate a random tour and then find an exceptionally better route.

Output will look like:

```
Initial tour distance: 2034.04
Tour: (160.14, 45.91)(135.40, 23.24)(21.57, 196.81)(5.12, 163.18)(77.98, 154.96)(97.95, 167.02)(163.35, 147.14)(98.90, 8.99)(79.45, 183.01)(151.94, 182.66)(190.30, 21.35)(96.17, 117.80)(83.57, 123.76)(128.29, 1.94)(71.10, 179.62)(114.99, 64.74)(5.49, 98.59)(112.18, 48.72)(100.79, 35.90)(186.88, 112.48)

Optimal tour distance: 997.96
Tour: (114.99, 64.74)(112.18, 48.72)(100.79, 35.90)(98.90, 8.99)(5.49, 98.59)(5.12, 163.18)(21.57, 196.81)(71.10, 179.62)(96.17, 117.80)(135.40, 23.24)(128.29, 1.94)(190.30, 21.35)(160.14, 45.91)(186.88, 112.48)(163.35, 147.14)(151.94, 182.66)(97.95, 167.02)(79.45, 183.01)(77.98, 154.96)(83.57, 123.76)
```
