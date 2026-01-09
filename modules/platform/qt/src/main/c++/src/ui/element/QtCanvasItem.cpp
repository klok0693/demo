#include "ui/element/QtCanvasItem.h"

#include <QCursor>
#include <QQuickWindow>

#include "ui/element/canvas_item_ui_bridge.h"

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

void QtCanvasItem::getCursorPositionOnCanvas(Point* out_point) 
{
    QPointF globalPos = QCursor::pos();

    QPointF localPos = mapFromScene(
        window()->mapFromGlobal(globalPos.toPoint())
    );
    QRectF bounds(QPointF(0, 0), size());

    if (bounds.contains(localPos)) {
        qDebug() << "contains " << localPos.x() << " " << localPos.y();

        out_point->x = localPos.x();
        out_point->y = localPos.y();
        // localPos.x(), localPos.y()
    }

    qDebug() << "hello from canvas get point";
}
