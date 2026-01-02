#include "QtCanvasController.h"
#include <QDebug>

QtCanvasController::QtCanvasController(QObject* parent)
    : QObject(parent)
{}

void QtCanvasController::handleMousePressed(QPointF pos) {
    qDebug() << "Mouse pressed at" << pos;
}

void QtCanvasController::handleMouseDragged(QPointF pos) {
    qDebug() << "Mouse dragged at" << pos;
}

void QtCanvasController::handleMouseReleased(QPointF pos) {
    qDebug() << "Mouse released at" << pos;
}
