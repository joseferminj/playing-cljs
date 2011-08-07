(ns playing-cljs.core
  (:require
   [playing-cljs.dom-helpers :as dom]
   [goog.object :as goog-object]
   [goog.events :as events]
   [goog.events.EventType :as event-type]
   [goog.ui.Toolbar :as gtoolbar]
   [goog.ui.ToolbarButton :as gtbutton]
   [goog.debug :as debug]
   [goog.debug.FancyWindow :as fancy-window]
   [goog.debug.Logger :as glogger]))      

;; -------------------------= State =-----------------------
(def database-connected? (atom false))
(def messaging-connected? (atom false))

(def log (glogger/getLogger "core.cljs"))

(defn switch-panel [target]
  (.info log (str "Execution swich panel with " target))
  (.. dom/get-element "taskList" getStyle (setDisplay "none"))
  (.. dom/get-element "completedTaskList" getStyle (setDisplay "none"))
  (.. dom/get-element "deletedTaskList" getStyle (setDisplay "none"))
  (.. dom/get-element "settings" getStyle (setDisplay "none"))
  (.. dom/get-element target getStyle (setDisplay "block")))

(defn attach-toolbar-button [toolbar label tooltip target]
  (let [button (doto (goog.ui.ToolbarButton. label)
                 (.setTooltip tooltip))]
    (.addChild toolbar button true)
    (events/listen (. button (getContentElement)) event-type/CLICK (partial switch-panel target))))

(defn attach-toolbar [container]
  (let [toolbar (goog.ui.Toolbar.)]
    (attach-toolbar-button toolbar "Matches" "List currently open matches", "taskList")
    (attach-toolbar-button toolbar "Completed" "List completd matches" "completedTaskList")
    (attach-toolbar-button toolbar "Trash" "List deleted matches" "deletedTaskList")
    (attach-toolbar-button toolbar "Settins" "Settings" "settings")
    (.render toolbar container)))
;; ---------------------= MAIN =------------------------
;; Main entry function
(defn ^:export main []
  (doto (goog.debug.FancyWindow. "main")
    (.setEnabled true)
    (.init))

  (.info log "Starting execution")
  ;; Populate a DOM via decoration.
  (attach-toolbar (dom/get-element "menu"))
  )


