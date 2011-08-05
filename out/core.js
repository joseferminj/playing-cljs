goog.provide('playing_cljs.core');
goog.require('cljs.core');
goog.require('goog.dom');
goog.require('goog.object');
goog.require('goog.events.Event');
goog.require('goog.events.EventType');
goog.require('goog.ui.ColorButton');
goog.require('goog.ui.Tab');
goog.require('goog.ui.TabBar');
playing_cljs.core.database_connected_QMARK_ = cljs.core.atom.call(null,false);
playing_cljs.core.messaging_connected_QMARK_ = cljs.core.atom.call(null,false);
playing_cljs.core.database_button = (function (){var G__2532__2533 = (new goog.ui.ColorButton("Database"));

G__2532__2533.setTooltip("Database Connection Status");
G__2532__2533.setValue("red");
return G__2532__2533;
})();
playing_cljs.core.messaging_button = (function (){var G__2534__2535 = (new goog.ui.ColorButton("Messaging"));

G__2534__2535.setTooltip("Messaging Connection Status");
G__2534__2535.setValue("red");
return G__2534__2535;
})();
playing_cljs.core.tabbar = (new goog.ui.TabBar());
playing_cljs.core.events = goog.object.getValues(goog.ui.Component.EventType);
playing_cljs.core.handle_tab_select = (function handle_tab_select(tabbar,e){
var tab__2536 = e.target;
var content__2537 = goog.dom.getElement(cljs.core.str.call(null,tabbar.getId(),"_content"));

return goog.dom.setTextContent(content__2537,tab__2536.getCaption());
});
/**
* Associate a colour with the states
*/
playing_cljs.core.button_color = (function button_color(x){
if(cljs.core.truth_(x))
{return "green";
} else
{return "red";
}
});
/**
* Simulate trivial connect/disconnect functionality for the buttions
*/
playing_cljs.core.toggle_state_BANG_ = (function toggle_state_BANG_(x){
return cljs.core.swap_BANG_.call(null,x,cljs.core.not);
});
playing_cljs.core.handle_button_push = (function handle_button_push(s,e){
return e.target.setValue(playing_cljs.core.button_color.call(null,playing_cljs.core.toggle_state_BANG_.call(null,s)));
});
playing_cljs.core.main = (function main(){
playing_cljs.core.tabbar.decorate(goog.dom.getElement("top"));
playing_cljs.core.database_button.render(goog.dom.getElement("buttons"));
playing_cljs.core.messaging_button.render(goog.dom.getElement("buttons"));
goog.events.listen(playing_cljs.core.tabbar,goog.ui.Component.EventType.SELECT,cljs.core.partial.call(null,playing_cljs.core.handle_tab_select,playing_cljs.core.tabbar));
goog.events.listen(playing_cljs.core.database_button,goog.ui.Component.EventType.ACTION,cljs.core.partial.call(null,playing_cljs.core.handle_button_push,playing_cljs.core.database_connected_QMARK_));
return goog.events.listen(playing_cljs.core.messaging_button,goog.ui.Component.EventType.ACTION,cljs.core.partial.call(null,playing_cljs.core.handle_button_push,playing_cljs.core.messaging_connected_QMARK_));
});
goog.exportSymbol('playing_cljs.core.main', playing_cljs.core.main);
