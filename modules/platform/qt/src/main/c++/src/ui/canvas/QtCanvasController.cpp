#include "ui/canvas/QtCanvasController.h"

#include <QDebug>

QtCanvasController::QtCanvasController(QObject* parent)
    : QObject(parent)
{}

void QtCanvasController::setOnMousePressedCallback(MousePressedCallback callback) 
{
    m_onMousePressedCallback = callback;
}

void QtCanvasController::setOnDragDetectedCallback(MouseEventCallback callback) 
{
    m_onDragDetectedCallback = callback;
}

void QtCanvasController::setOnMouseDraggedCallback(MouseEventCallback callback) 
{
    m_onMouseDraggedCallback = callback;
}

void QtCanvasController::setOnMouseReleasedCallback(MouseEventCallback callback) 
{
    m_onMouseReleasedCallback = callback;
}

void QtCanvasController::handleMousePressed(QPointF pos, bool isCtrl, bool isShift) 
{
    m_dragSource.m_pressPos = pos;
    m_dragSource.m_dragging = false;

    m_onMousePressedCallback(pos.x(), pos.y(), isCtrl, isShift);
}

void QtCanvasController::handleMouseDragDetected(QPointF pos) 
{
    m_dragSource.m_dragging = true;
    m_onDragDetectedCallback(pos.x(), pos.y());
}

void QtCanvasController::handleMouseDragged(QPointF pos) 
{
    if (!m_dragSource.m_dragging) {
        handleMouseDragDetected(pos);
    }
    m_onMouseDraggedCallback(pos.x(), pos.y());
}

void QtCanvasController::handleMouseReleased(QPointF pos) 
{
    m_dragSource.m_dragging = false;
    m_onMouseReleasedCallback(pos.x(), pos.y());
}
