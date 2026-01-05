#include "ToolBarController.h"

#include <QGuiApplication>

void ToolBarController::setDeleteBtnDisabled(bool isDisabled) {
    qDebug() << "set delete enabled " << !isDisabled;
    m_deleteEnabled = !isDisabled;

    emit deleteEnabledChanged();
}

void ToolBarController::setinsertRectBtnSelected(bool isSelected) {
    m_insertRectBtnSelected = isSelected;

    emit insertRectBtnSelectedChanged();
}

void ToolBarController::setinsertCycleBtnSelected(bool isSelected) {
    m_insertCycleBtnSelected = isSelected;

    emit insertCycleBtnSelectedChanged();
}

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

bool ToolBarController::insertRectBtnSelected() const
{
    return m_insertRectBtnSelected;
}

bool ToolBarController::insertCycleBtnSelected() const
{
    return m_insertCycleBtnSelected;
}

bool ToolBarController::deleteEnabled() const
{
    qDebug() << "get delete enabled " << m_deleteEnabled;
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
