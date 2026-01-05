#include "ui/element/QtCanvasItem.h"

QtCanvasItem::QtCanvasItem(QQuickItem* parent)
    : QQuickPaintedItem(parent)
{
    setAntialiasing(true);
}

void QtCanvasItem::setDrawingCallback(void* callback) {
    m_drawingCallback = reinterpret_cast<DrawingCallback>(callback);;
}

void QtCanvasItem::initController(initFunc func) const {
    func(width(), height());
}

void QtCanvasItem::paint(QPainter* painter)
{
    PainterContext ctx{ painter };

    m_drawingCallback(&ctx);
}
