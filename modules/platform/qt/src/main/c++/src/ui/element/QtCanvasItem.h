#pragma once

#include <QQuickPaintedItem>
#include <QPainter>

#include "ui/element/canvas_item_ui_bridge.h"

using initFunc = void (*)(double, double);

class QtCanvasItem : public QQuickPaintedItem {
    Q_OBJECT

public:
    explicit QtCanvasItem(QQuickItem* parent = nullptr);

    void paint(QPainter* painter) override;

    void setDrawingCallback(void* callback);

    void initController(initFunc func) const;

private:
    DrawingCallback m_drawingCallback = nullptr;
};