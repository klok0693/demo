#pragma once

#include <QObject>
#include <QPointF>

#include "../ui_bridge.h"

using MousePressedCallback = void (*)(double, double);

class QtCanvasController : public QObject {
    Q_OBJECT

    struct DragSource {
        QPointF pressPos;
        bool dragging = false;
    } dragSource;

public:
    explicit QtCanvasController(QObject* parent = nullptr);

    void setOnMousePressedCallback(MousePressedCallback callback);
    void setOnDragDetectedCallback(MousePressedCallback callback);
    void setOnMouseDraggedCallback(MousePressedCallback callback);
    void setOnMouseReleasedCallback(MousePressedCallback callback);

public slots:
    void handleMousePressed(QPointF pos);
    void handleMouseDragDetected(QPointF pos);
    void handleMouseDragged(QPointF pos);
    void handleMouseReleased(QPointF pos);

private:
    MouseEventCallback m_onMousePressedCallback = nullptr;
    MouseEventCallback m_onDragDetectedCallback = nullptr;
    MouseEventCallback m_onMouseDraggedCallback = nullptr;
    MouseEventCallback m_onMouseReleasedCallback = nullptr;
};
