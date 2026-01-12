#include "ui/toolbar/tool_bar_ui_bridge.h"

#include <QQmlApplicationEngine>
#include <QQmlContext>

#include "ui/toolbar/ToolBarController.h"

#ifdef __cplusplus
extern "C" {
#endif

UI_API ToolBarController* ui_toolbar_get() 
{
    QObject* obj =
        get_engine()->rootContext()
          ->contextProperty("toolBarController")
          .value<QObject*>();

    return qobject_cast<ToolBarController*>(obj);
}

UI_API void set_toolbar_insert_rect_callback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData) 
{
    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setInsertRectCallback((void*)cb, userData);
}

UI_API void set_toolbar_insert_cycle_callback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData) 
{
    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setInsertCycleCallback((void*)cb, userData);
}

UI_API void set_toolbar_undo_callback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData) 
{
    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setUndoCallback((void*)cb, userData);
}

UI_API void set_toolbar_delete_callback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData) 
{
    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setDeleteCallback((void*)cb, userData);
}

UI_API void set_toolbar_insert_rect_btn_selected(void* toolbar, bool isSelected) 
{
    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setinsertRectBtnSelected(isSelected);
}

UI_API void set_toolbar_insert_cycle_btn_selected(void* toolbar, bool isSelected) 
{
    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setinsertCycleBtnSelected(isSelected);
}

UI_API void set_toolbar_delete_btn_disabled(void* toolbar, bool disabled) 
{
    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setDeleteBtnDisabled(disabled);
}

#ifdef __cplusplus
}
#endif