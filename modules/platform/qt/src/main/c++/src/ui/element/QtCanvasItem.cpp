#include "QtCanvasItem.h"

QtCanvasItem::QtCanvasItem(QQuickItem* parent)
    : QQuickPaintedItem(parent)
{
    setAntialiasing(true);
}

void QtCanvasItem::setDrawingCallback(void* callback) {
    drawingCallback = reinterpret_cast<DrawingCallback>(callback);;
}

void QtCanvasItem::paint(QPainter* painter)
{
    PainterContext ctx{ painter };

    drawingCallback(&ctx);
    
    //ui_canvas_begin(&ctx);
    //ui_canvas_call_java_draw(&ctx);
    //ui_canvas_end();

    //painter->fillRect(boundingRect(), QColor("#2a6cff")); // stub: blue background
}
