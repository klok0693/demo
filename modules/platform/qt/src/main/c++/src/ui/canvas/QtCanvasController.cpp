#include "ui/canvas/QtCanvasController.h"

#include <QDebug>

/* typedef struct {
    QPointF pressPos;
    bool dragging = false;
} dragSource; */

QtCanvasController::QtCanvasController(QObject* parent)
    : QObject(parent)
{}

void QtCanvasController::setOnMousePressedCallback(MousePressedCallback callback) {
    m_onMousePressedCallback = reinterpret_cast<MouseEventCallback>(callback);;
}

void QtCanvasController::setOnDragDetectedCallback(MousePressedCallback callback) {
    m_onDragDetectedCallback = reinterpret_cast<MouseEventCallback>(callback);
}

void QtCanvasController::setOnMouseDraggedCallback(MousePressedCallback callback) {
    m_onMouseDraggedCallback = reinterpret_cast<MouseEventCallback>(callback);
}

void QtCanvasController::setOnMouseReleasedCallback(MousePressedCallback callback) {
    m_onMouseReleasedCallback = reinterpret_cast<MouseEventCallback>(callback);
}

void QtCanvasController::handleMousePressed(QPointF pos) {
    //qDebug() << "Mouse pressed at" << pos;

    m_dragSource.m_pressPos = pos;
    m_dragSource.m_dragging = false;

    //qDebug() << "pressed callback " << m_onMousePressedCallback;
    m_onMousePressedCallback(pos.x(), pos.y());
}

void QtCanvasController::handleMouseDragDetected(QPointF pos) {
    //qDebug() << "Drag detected at" << pos;

    m_dragSource.m_dragging = true;

    m_onDragDetectedCallback(pos.x(), pos.y());
}

void QtCanvasController::handleMouseDragged(QPointF pos) {
    //qDebug() << "Mouse dragged at" << pos;

    if (!m_dragSource.m_dragging) {
        handleMouseDragDetected(pos);
    }

    m_onMouseDraggedCallback(pos.x(), pos.y());
}

void QtCanvasController::handleMouseReleased(QPointF pos) {
    //qDebug() << "Mouse released at" << pos;

    m_dragSource.m_dragging = false;

    m_onMouseReleasedCallback(pos.x(), pos.y());
}
