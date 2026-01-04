#include "QtCanvasItem.h"

QtCanvasItem::QtCanvasItem(QQuickItem* parent)
    : QQuickPaintedItem(parent)
{
    setAntialiasing(true);
}

void QtCanvasItem::setDrawingCallback(void* callback) {
    drawingCallback = reinterpret_cast<DrawingCallback>(callback);;
}

void QtCanvasItem::initController(initFunc func) {
    func(width(), height());
}

void QtCanvasItem::paint(QPainter* painter)
{
    PainterContext ctx{ painter };

    drawingCallback(&ctx);
}
