#pragma once

#include <QObject>
#include <QPointF>

#include "ui/canvas/canvas_view_ui_bridge.h"

//using MousePressedCallback = void (*)(double, double);

class QtCanvasController : public QObject {
    Q_OBJECT

    struct DragSource {
        QPointF m_pressPos;
        bool m_dragging = false;
    } m_dragSource;

public:
    explicit QtCanvasController(QObject* parent = nullptr);

    void setOnMousePressedCallback(MousePressedCallback callback);
    void setOnDragDetectedCallback(MouseEventCallback callback);
    void setOnMouseDraggedCallback(MouseEventCallback callback);
    void setOnMouseReleasedCallback(MouseEventCallback callback);

public slots:
    void handleMousePressed(QPointF pos, bool isCtrl, bool isShift);
    void handleMouseDragDetected(QPointF pos);
    void handleMouseDragged(QPointF pos);
    void handleMouseReleased(QPointF pos);

private:
    MousePressedCallback m_onMousePressedCallback = nullptr;
    MouseEventCallback m_onDragDetectedCallback = nullptr;
    MouseEventCallback m_onMouseDraggedCallback = nullptr;
    MouseEventCallback m_onMouseReleasedCallback = nullptr;
};
