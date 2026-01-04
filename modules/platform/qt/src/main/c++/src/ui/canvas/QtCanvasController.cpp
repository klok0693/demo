#include "QtCanvasController.h"
#include <QDebug>

/* typedef struct {
    QPointF pressPos;
    bool dragging = false;
} dragSource; */

QtCanvasController::QtCanvasController(QObject* parent)
    : QObject(parent)
{}

void QtCanvasController::setOnMousePressedCallback(MousePressedCallback callback) {
    qDebug() << "mouse pressed callback set";
    m_onMousePressedCallback = reinterpret_cast<MouseEventCallback>(callback);;
}

void QtCanvasController::setOnDragDetectedCallback(MousePressedCallback callback) {
    qDebug() << "drag detected callback set";
    m_onDragDetectedCallback = reinterpret_cast<MouseEventCallback>(callback);
}

void QtCanvasController::setOnMouseDraggedCallback(MousePressedCallback callback) {
    qDebug() << "Mouse dragged callback set";
    m_onMouseDraggedCallback = reinterpret_cast<MouseEventCallback>(callback);
}

void QtCanvasController::setOnMouseReleasedCallback(MousePressedCallback callback) {
    qDebug() << "Mouse dragged callback set";
    m_onMouseReleasedCallback = reinterpret_cast<MouseEventCallback>(callback);
}

void QtCanvasController::handleMousePressed(QPointF pos) {
    qDebug() << "Mouse pressed at" << pos;

    dragSource.pressPos = pos;
    dragSource.dragging = false;

    //qDebug() << "pressed callback " << m_onMousePressedCallback;
    m_onMousePressedCallback(pos.x(), pos.y());
}

void QtCanvasController::handleMouseDragDetected(QPointF pos) {
    qDebug() << "Drag detected at" << pos;

    dragSource.dragging = true;

    m_onDragDetectedCallback(pos.x(), pos.y());
}

void QtCanvasController::handleMouseDragged(QPointF pos) {
    qDebug() << "Mouse dragged at" << pos;

    if (!dragSource.dragging) {
        handleMouseDragDetected(pos);
    }

    m_onMouseDraggedCallback(pos.x(), pos.y());
}

void QtCanvasController::handleMouseReleased(QPointF pos) {
    qDebug() << "Mouse released at" << pos;

    dragSource.dragging = false;

    m_onMouseReleasedCallback(pos.x(), pos.y());
}
