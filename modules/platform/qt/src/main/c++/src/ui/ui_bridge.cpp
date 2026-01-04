#include "ui_bridge.h"
#include "root/RootView.h"
#include "toolbar/ToolBarController.h"
#include "element/QtCanvasItem.h"
#include "canvas/QtCanvasController.h"

#include <QGuiApplication>
#include <QQmlApplicationEngine>
#include <QQmlContext>

static QQmlApplicationEngine* engine = nullptr;

void setEngine(QQmlApplicationEngine* eng) {
    engine = eng;
}

extern "C" {

typedef void (*StatusFunc)(int);

static StatusFunc javaCallback = nullptr;

UI_API void setStatusCallback(StatusFunc callback) {
    javaCallback = callback;
}

UI_API void emitStatus(int status) {
    if (javaCallback) {
        javaCallback(status);
    }
}

void setToolState(int toolId, int enabled) {
    qDebug() << "Java says: tool" << toolId << "enabled:" << enabled;
}

// --------- ROOT -----------

UI_API void setCursor(const char* utf8) {
    QObject* obj =
        engine->rootContext()
          ->contextProperty("rootView")
          .value<QObject*>();

    auto* rootView = qobject_cast<RootView*>(obj);    
     const QString cursor = QString::fromUtf8(utf8); 
    //rootView->setCursor(QString::fromUtf8(utf8)); 

    QMetaObject::invokeMethod(
        rootView,
        [rootView, cursor]() {
            rootView->setCursor(cursor);
        },
        Qt::QueuedConnection
    );
}

// --------- TOOLBAR -----------

UI_API ToolBarController* ui_toolbar_get() {
    QObject* obj =
        engine->rootContext()
          ->contextProperty("toolBarController")
          .value<QObject*>();

    auto* toolbar = qobject_cast<ToolBarController*>(obj);

    return toolbar;
}

UI_API void setToolBarInsertRectBtnSelected(void* toolbar, bool isSelected) {
    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setinsertRectBtnSelected(isSelected);
}

UI_API void setToolBarInsertCycleBtnSelected(void* toolbar, bool isSelected) {
    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setinsertCycleBtnSelected(isSelected);
}

UI_API void setToolBarDeleteBtnDisabled(void* toolbar, bool disabled) {
    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setDeleteBtnDisabled(disabled);
}

UI_API void setToolBarInsertRectCallback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData) {

    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setInsertRectCallback((void*)cb, userData);
}

UI_API void setToolBarInsertCycleCallback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData) {

    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setInsertCycleCallback((void*)cb, userData);
}

UI_API void setToolBarUndoCallback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData) {

    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setUndoCallback((void*)cb, userData);
}

UI_API void setToolBarDeleteCallback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData) {

    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setDeleteCallback((void*)cb, userData);
}

// --------- CANVAS -----------

UI_API QtCanvasItem* ui_canvas_get() {
    const auto roots = engine->rootObjects();
    QObject* obj = roots.first()->findChild<QObject*>("canvasItem");
    
    return qobject_cast<QtCanvasItem*>(obj);
}

UI_API QtCanvasController* ui_canvas_controller_get() {
    QObject* obj =
        engine->rootContext()
          ->contextProperty("canvasController")
          .value<QObject*>();

    auto* toolbar = qobject_cast<QtCanvasController*>(obj);
    return toolbar;
}

UI_API void updateCanvasItem(void* canvasItem) {
    auto* ctrl = static_cast<QtCanvasItem*>(canvasItem);
    QMetaObject::invokeMethod(
        ctrl,
        "update",
        Qt::QueuedConnection
    );
}

UI_API void setDrawingCallback(
        void* canvasItem,
        DrawingCallback callback
) {
    auto* ctrl = static_cast<QtCanvasItem*>(canvasItem);
    ctrl->setDrawingCallback((void*)callback);
}

UI_API void initCanvasController(
        void* canvasItem,
        InitCanvasControllerFunc callback
) {
    auto* ctrl = static_cast<QtCanvasItem*>(canvasItem);
    ctrl->initController(callback);
}

UI_API void setMousePressedCallback(
        void* canvasController,
        MouseEventCallback callback
) {
    qDebug() << "UI_BRIDGE mouse pressed callback";

    auto* ctrl = static_cast<QtCanvasController*>(canvasController);
    ctrl->setOnMousePressedCallback(callback);
}

UI_API void setDragDetectedCallback(
        void* canvasController,
        MouseEventCallback callback
) {
    qDebug() << "UI_BRIDGE drag detected callback";

    auto* ctrl = static_cast<QtCanvasController*>(canvasController);
    ctrl->setOnDragDetectedCallback(callback);
}

UI_API void setMouseDraggedCallback(
        void* canvasController,
        MouseEventCallback callback
) {
    qDebug() << "UI_BRIDGE mouse dragged callback";

    auto* ctrl = static_cast<QtCanvasController*>(canvasController);
    ctrl->setOnMouseDraggedCallback(callback);
}

UI_API void setMouseReleasedCallback(
        void* canvasController,
        MouseEventCallback callback
) {
    qDebug() << "UI_BRIDGE mouse released callback";

    auto* ctrl = static_cast<QtCanvasController*>(canvasController);
    ctrl->setOnMouseReleasedCallback(callback);
}

UI_API void ui_painter_set_fill(
    PainterContext* ctx,
    const char* utf8
) {
    const QString colorStr = QString::fromUtf8(utf8);
    QColor color(colorStr);

    if (!color.isValid()) {
        qDebug() << "color not valid " << colorStr;
        // fallback: transparent to avoid undefined state
        color = Qt::transparent;
    }

    ctx->painter->setBrush(color);
    ctx->painter->setPen(Qt::NoPen);
}

UI_API void ui_painter_fill_rect(
    PainterContext* ctx,
    double x, double y, double w, double h
) {
    ctx->painter->fillRect(QRectF(x, y, w, h), ctx->painter->brush());
}

}