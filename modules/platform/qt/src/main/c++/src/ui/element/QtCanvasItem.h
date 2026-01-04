#pragma once

#include <QQuickPaintedItem>
#include <QPainter>

#include "../ui_bridge.h"

class QtCanvasItem : public QQuickPaintedItem {
    Q_OBJECT

public:
    explicit QtCanvasItem(QQuickItem* parent = nullptr);

    void paint(QPainter* painter) override;

    void setDrawingCallback(void* callback);

private:
    DrawingCallback drawingCallback = nullptr;
};