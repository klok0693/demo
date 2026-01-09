#include "ui/element/canvas_item_ui_bridge.h"

#include <QQmlContext>

#include "ui/element/QtCanvasItem.h"
#include "ui/ui_bridge.h"

#ifdef __cplusplus
extern "C" {
#endif

UI_API QtCanvasItem* ui_canvas_get() {
    const auto roots = get_engine()->rootObjects();
    QObject* obj = roots.first()->findChild<QObject*>("canvasItem");
    
    return qobject_cast<QtCanvasItem*>(obj);
}

UI_API void get_cursor_position(void* canvasController, Point* out_point) 
{
    auto* ctrl = static_cast<QtCanvasItem*>(canvasController);
    ctrl->getCursorPositionOnCanvas(out_point);
}

UI_API void update_canvas_item(void* canvasItem) {
    QMetaObject::invokeMethod(
        static_cast<QtCanvasItem*>(canvasItem),
        "update",
        Qt::QueuedConnection
    );
}

UI_API void set_drawing_callback(
        void* canvasItem,
        DrawingCallback callback
) {
    auto* ctrl = static_cast<QtCanvasItem*>(canvasItem);
    ctrl->setDrawingCallback((void*)callback);
}

UI_API void init_canvas_controller(
        void* canvasItem,
        InitCanvasControllerFunc callback
) {
    auto* ctrl = static_cast<QtCanvasItem*>(canvasItem);
    ctrl->initController(callback);
}

UI_API void ui_painter_save(void* ctxPtr) 
{
    auto* ctx = static_cast<PainterContext*>(ctxPtr);
    ctx->painter->save();
}

UI_API void ui_painter_restore(void* ctxPtr) {
    auto* ctx = static_cast<PainterContext*>(ctxPtr);
    ctx->painter->restore();
}

UI_API void ui_painter_set_fill(
    void* ctxPtr,
    const char* utf8
) {
    auto* ctx = static_cast<PainterContext*>(ctxPtr);
    const QString colorStr = QString::fromUtf8(utf8);
    QColor color(colorStr);

    if (!color.isValid()) {
        qDebug() << "color not valid " << colorStr;
        color = Qt::transparent;
    }

    ctx->painter->setBrush(color);
    ctx->painter->setPen(Qt::NoPen);
}

UI_API void ui_painter_fill_rect(
    void* ctxPtr,
    double x, double y, double w, double h
) {
    auto* ctx = static_cast<PainterContext*>(ctxPtr);
    ctx->painter->fillRect(QRectF(x, y, w, h), ctx->painter->brush());
}

UI_API void ui_painter_stroke_rect(
    void* ctxPtr,
    double x, double y, double w, double h
) {
    auto* ctx = static_cast<PainterContext*>(ctxPtr);
    ctx->painter->drawRect(QRectF(x, y, w, h));
}

UI_API void ui_painter_fill_oval(
    void* ctxPtr,
    double x, double y, double w, double h
) {
    auto* ctx = static_cast<PainterContext*>(ctxPtr);
    ctx->painter->drawEllipse(QRectF(x, y, w, h));
}

UI_API void ui_painter_set_opacity(
    void* ctxPtr,
    double opacity
) {
    auto* ctx = static_cast<PainterContext*>(ctxPtr);
    ctx->painter->setOpacity(opacity);
}

UI_API void ui_painter_set_stroke(
    void* ctxPtr,
    const char* utf8
) {
    auto* ctx = static_cast<PainterContext*>(ctxPtr);
    QColor color(QString::fromUtf8(utf8));

    QPen pen = ctx->painter->pen();
    pen.setColor(color);
    ctx->painter->setPen(pen);
}

UI_API void ui_painter_set_line_width(
    void* ctxPtr,
    double width
) {
    auto* ctx = static_cast<PainterContext*>(ctxPtr);
    QPen pen = ctx->painter->pen();
    pen.setWidthF(width);
    ctx->painter->setPen(pen);
}

#ifdef __cplusplus
}
#endif