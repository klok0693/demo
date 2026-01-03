#include "ToolBarController.h"

#include <QGuiApplication>

void ToolBarController::setInsertRectCallback(
        void* cb,
        void* userData) {
    insertRectCallback = reinterpret_cast<ToolbarCallback>(cb);
    insertRectUserData = userData;
}

void ToolBarController::setInsertCycleCallback(
        void* cb,
        void* userData) {
    insertCycleCallback = reinterpret_cast<ToolbarCallback>(cb);
    insertRectUserData = userData;
}

void ToolBarController::setUndoCallback(
        void* cb,
        void* userData) {
    undoCallback = reinterpret_cast<ToolbarCallback>(cb);
    insertRectUserData = userData;
}

void ToolBarController::setDeleteCallback(
        void* cb,
        void* userData) {
    deleteCallback = reinterpret_cast<ToolbarCallback>(cb);
    insertRectUserData = userData;
}

ToolBarController::ToolBarController(QObject* parent)
    : QObject(parent)
{
}

bool ToolBarController::deleteEnabled() const
{
    return m_deleteEnabled;
}

void ToolBarController::onInsertRectAction()
{
    insertRectCallback(insertRectUserData);
}

void ToolBarController::onInsertCycleAction()
{
    insertCycleCallback(insertRectUserData);
}

void ToolBarController::onUndoAction()
{
    undoCallback(insertRectUserData);
}

void ToolBarController::onDeleteAction()
{
    deleteCallback(insertRectUserData);
}
