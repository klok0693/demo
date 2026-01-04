#pragma once

#include <QQuickPaintedItem>
#include <QPainter>

#include "../ui_bridge.h"

using initFunc = void (*)(double, double);

class QtCanvasItem : public QQuickPaintedItem {
    Q_OBJECT

public:
    explicit QtCanvasItem(QQuickItem* parent = nullptr);

    void paint(QPainter* painter) override;

    void setDrawingCallback(void* callback);

    void initController(initFunc func);

private:
    DrawingCallback drawingCallback = nullptr;
};