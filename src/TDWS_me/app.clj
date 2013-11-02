(ns TDWS-me.app
  (:require [ring.adapter.jetty :as jetty]
            [ring.util.response :as response]
            [net.cgrand.enlive-html :as en])
  (:use (compojure handler
                   [core :only (GET POST defroutes)])
        clojure.pprint)
  (:gen-class))

(defonce counter (atom 100000))
(defonce urls (atom {}))

(defn shorten
  [url]
  (let [id (swap! counter inc)
        id (Long/toString id 36)]
    (swap! urls assoc id url)
    id))

(en/deftemplate homepage
  (en/html-resource "homepage.html")
  [request])

(en/deftemplate shortlink
  (en/html-resource "shortlink.html")
  [id]
  [:#shortlink] (en/content (format "%s/%s" "http://tdws.me" id)))
  
(defn redirect
  [id]
  (response/redirect (@urls id)))

(defroutes app*
  (GET "/" request (homepage request))
  (POST "/shorten" request
        (let [id (shorten (-> request :params :long-url))]
          (response/redirect (format "/shortlink/%s" id))))
  (GET "/shortlink/:id" [id] (shortlink id))
  (GET "/:id" [id] (redirect id)))

(def app (compojure.handler/site app*))

(defn -main [& args]
  (jetty/run-jetty #'app {:port 8081 :join? false}))
