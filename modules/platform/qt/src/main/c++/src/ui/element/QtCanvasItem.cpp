#include "QtCanvasItem.h"

QtCanvasItem::QtCanvasItem(QQuickItem* parent)
    : QQuickPaintedItem(parent)
{
    setAntialiasing(true);
}

void QtCanvasItem::paint(QPainter* painter)
{
    painter->fillRect(boundingRect(), QColor("#2a6cff")); // stub: blue background
}
