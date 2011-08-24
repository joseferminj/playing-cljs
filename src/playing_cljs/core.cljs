(ns playing-cljs.core
  (:require
   [playing-cljs.dom-helpers :as dom]
   [pinot.html :as ph]))      

(defn table-cell-dom [cell]
  [:td cell])

(defn table-row-dom [row]
  [:tr
   (map table-cell-dom row)])

(defn table-dom [table]
  [:table
   [:tbody
    (map table-row-dom table)
    ]])

(defn table-make-dom [table]
  (ph/html (table-dom table)))

;; (defn supertable-make-dom [container table]
;;   (ph/append-to container
;;                 [(table-make-dom (conj [] (first table)))
;;                 (table-make-dom (rest table))]))


(def table  [["Lunes" "Martes" "Miercoles" "Jueves" "Viernes" "Sabado" "Domingo"]
                                   ["x" "x" "x" "x" "x" "x" "x"]
                                   ["x" "x" "x" "x" "x" "x" "x"]])


(defn ^:export main []
  ;; (supertable-make-dom (dom/get-element-by-class "content")
  ;;                      table)
  
  (ph/append-to (dom/get-element "content")
                (table-make-dom2 table))

  ;; (supertable-make-dom (dom/get-element "content")
  ;;                      table)
  )


