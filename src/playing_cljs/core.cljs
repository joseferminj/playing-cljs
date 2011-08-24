(ns playing-cljs.core
  (:require
   [playing-cljs.dom-helpers :as dom]))      

(defn table-cells-dom [cells]
  (map #(vec(list "td" %)) cells))

(defn table-dom [table]
  ["table"
   ["tbody"
    (map #(vec (list "tr" (table-cells-dom %))) table)]])

(defn table-make-dom [table]
  (dom/build (table-dom table)))

(defn supertable-make-dom [container table]
  (dom/append container
              (table-make-dom (vec (first table)))
              (table-make-dom (rest table))))

(def table  [["Lunes" "Martes" "Miercoles" "Jueves" "Viernes" "Sabado" "Domingo"]
                                   ["x" "x" "x" "x" "x" "x" "x"]
                                   ["x" "x" "x" "x" "x" "x" "x"]])

(defn ^:export main []
  ;; (supertable-make-dom (dom/get-element-by-class "content")
  ;;                      table)
  (dom/append (dom/get-element "content")
              (dom/build [:div "Prueba"])))


