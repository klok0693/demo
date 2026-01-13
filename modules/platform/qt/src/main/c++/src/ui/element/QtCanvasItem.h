#pragma once

#include <QQuickPaintedItem>
#include <QPainter>

#include "ui/element/canvas_item_ui_bridge.h"
#include "ui/ui_export.h"

using initFunc = void (*)(double, double);

struct PainterContext {
    QPainter* painter;
};

class UI_EXPORT QtCanvasItem : public QQuickPaintedItem {
    Q_OBJECT

public:
    explicit QtCanvasItem(QQuickItem* parent = nullptr);

    void paint(QPainter* painter) override;

    void setDrawingCallback(void* callback);

    void initController(initFunc func) const;

    void getCursorPositionOnCanvas(Point* out_point);

private:
    DrawingCallback m_drawingCallback = nullptr;
};