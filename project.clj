(defproject com.taoensso/tempel "1.0.0-SNAPSHOT"
  :author "Peter Taoussanis <https://www.taoensso.com>"
  :description "Data security framework for Clojure"
  :url "https://github.com/ptaoussanis/tempel"
  :min-lein-version "2.3.3"

  :license
  {:name "Eclipse Public License 1.0"
   :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :global-vars
  {*warn-on-reflection* true
   *assert*             true
   *unchecked-math*     :warn-on-boxed}

  :dependencies
  [[com.taoensso/encore "3.52.0"]]

  :plugins
  [[lein-pprint    "1.3.2"]
   [lein-ancient   "0.7.0"]
   [lein-codox     "0.10.8"]
   [lein-cljsbuild "1.1.8"]]

  :profiles
  {;; :default [:base :system :user :provided :dev]
   :server-jvm {:jvm-opts ^:replace ["-server"]}
   :provided {:dependencies [[org.clojure/clojure "1.11.1"]]}
   :c1.11    {:dependencies [[org.clojure/clojure "1.11.1"]]}
   :c1.10    {:dependencies [[org.clojure/clojure "1.10.1"]]}
   :c1.9     {:dependencies [[org.clojure/clojure "1.9.0"]]}
   :depr     {:jvm-opts ["-Dtaoensso.elide-deprecated=true"]}
   :dev      [:c1.11 :test :server-jvm :depr]
   :test     {:dependencies [[org.clojure/test.check "1.1.1"]
                             [com.taoensso/timbre    "6.1.0"]]}}

  :test-paths ["test" "src"]

  :aliases
  {"start-dev"  ["with-profile" "+dev" "repl" ":headless"]
   "deploy-lib" ["do" #_["build-once"] ["deploy" "clojars"] ["install"]]

   "test-all"
   ["do" ["clean"]
    "with-profile" "+c1.11:+c1.10:+c1.9" "test"]}

  :repositories
  {"sonatype-oss-public"
   "https://oss.sonatype.org/content/groups/public/"})
