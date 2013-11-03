(ns TDWS_me.main
  (:require [domina :as dom]))

(defn validate-form []
  (let [long-url (dom/by-id "long-url")]
    (if (> (count (value long-url)) 0)
      true
      (do (set-styles! (dom/by-id "error")
                       {:display "inline"})
        false))))

(defn init []
  ;; verify that js/document exists and that it has a getElementById
  ;; property
  (if (and js/document
           (.-getElementById js/document))
    ;; get loginForm by element id and set its onsubmit property to
    ;; our validate-form function
    (let [login-form (.getElementById js/document "url_in")]
      (set! (.-onsubmit login-form) validate-form))))

(set! (.-onload js/window) init)