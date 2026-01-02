#pragma once

#include <QQuickPaintedItem>
#include <QPainter>

class QtCanvasItem : public QQuickPaintedItem {
    Q_OBJECT

public:
    explicit QtCanvasItem(QQuickItem* parent = nullptr);

    void paint(QPainter* painter) override;
};