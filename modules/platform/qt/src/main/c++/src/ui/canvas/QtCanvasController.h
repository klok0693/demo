#pragma once

#include <QObject>
#include <QPointF>

class QtCanvasController : public QObject {
    Q_OBJECT

public:
    explicit QtCanvasController(QObject* parent = nullptr);

/* public slots:
    void handleMousePressed(QPointF pos);
    void handleMouseDragged(QPointF pos);
    void handleMouseReleased(QPointF pos); */
};
