#include "ui/element/canvas_item_ui_bridge.h"

#include <QQmlContext>

#include "ui/element/QtCanvasItem.h"

#ifdef __cplusplus
extern "C" {
#endif

UI_API QtCanvasItem* ui_canvas_get() {
    const auto roots = get_engine()->rootObjects();
    QObject* obj = roots.first()->findChild<QObject*>("canvasItem");
    
    return qobject_cast<QtCanvasItem*>(obj);
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

UI_API void ui_painter_save(PainterContext* ctx) {
    ctx->painter->save();
}

UI_API void ui_painter_restore(PainterContext* ctx) {
    ctx->painter->restore();
}

UI_API void ui_painter_set_fill(
    PainterContext* ctx,
    const char* utf8
) {
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
    PainterContext* ctx,
    double x, double y, double w, double h
) {
    ctx->painter->fillRect(QRectF(x, y, w, h), ctx->painter->brush());
}

UI_API void ui_painter_stroke_rect(
    PainterContext* ctx,
    double x, double y, double w, double h
) {
    ctx->painter->drawRect(QRectF(x, y, w, h));
}

UI_API void ui_painter_fill_oval(
    PainterContext* ctx,
    double x, double y, double w, double h
) {
    ctx->painter->drawEllipse(QRectF(x, y, w, h));
}

UI_API void ui_painter_set_opacity(
    PainterContext* ctx,
    double opacity
) {
    ctx->painter->setOpacity(opacity);
}

UI_API void ui_painter_set_stroke(
    PainterContext* ctx,
    const char* utf8
) {
    QColor color(QString::fromUtf8(utf8));

    QPen pen = ctx->painter->pen();
    pen.setColor(color);
    ctx->painter->setPen(pen);
}

UI_API void ui_painter_set_line_width(
    PainterContext* ctx,
    double width
) {
    QPen pen = ctx->painter->pen();
    pen.setWidthF(width);
    ctx->painter->setPen(pen);
}

#ifdef __cplusplus
}
#endif