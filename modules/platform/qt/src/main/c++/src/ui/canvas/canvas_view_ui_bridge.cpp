#include "ui/canvas/canvas_view_ui_bridge.h"

#include <QQmlApplicationEngine>
#include <QQmlContext>

#include "ui/canvas/QtCanvasController.h"
#include "ui/ui_bridge.h"

#ifdef __cplusplus
extern "C" {
#endif

UI_API QtCanvasController* ui_canvas_controller_get() 
{
    QObject* obj =
        get_engine()->rootContext()
          ->contextProperty("canvasController")
          .value<QObject*>();

    return qobject_cast<QtCanvasController*>(obj);
}

UI_API void set_mouse_pressed_callback(
        void* canvasController,
        MousePressedCallback callback) 
{
    auto* ctrl = static_cast<QtCanvasController*>(canvasController);
    ctrl->setOnMousePressedCallback(callback);
}

UI_API void set_drag_detected_callback(
        void* canvasController,
        MouseEventCallback callback) 
{
    auto* ctrl = static_cast<QtCanvasController*>(canvasController);
    ctrl->setOnDragDetectedCallback(callback);
}

UI_API void set_mouse_dragged_callback(
        void* canvasController,
        MouseEventCallback callback) 
{
    auto* ctrl = static_cast<QtCanvasController*>(canvasController);
    ctrl->setOnMouseDraggedCallback(callback);
}

UI_API void set_mouse_released_callback(
        void* canvasController,
        MouseEventCallback callback) 
{
    auto* ctrl = static_cast<QtCanvasController*>(canvasController);
    ctrl->setOnMouseReleasedCallback(callback);
}

#ifdef __cplusplus
}
#endif