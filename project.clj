(defproject simulated-annealing "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main simulated-annealing.core
  :profiles {:dev { :dependencies [[org.clojure/tools.namespace "0.2.7"]
                                  [org.clojure/java.classpath "0.2.2"]
                                  [midje "1.6.3"]
                                  [bultitude "0.2.6"]]
                     :plugins [[lein-midje "3.1.1"]]}})
