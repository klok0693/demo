#include "ui/toolbar/ToolBarController.h"

#include <QGuiApplication>

void ToolBarController::setDeleteBtnDisabled(bool isDisabled) 
{
    m_deleteEnabled = !isDisabled;
    emit deleteEnabledChanged();
}

void ToolBarController::setinsertRectBtnSelected(bool isSelected) 
{
    m_insertRectBtnSelected = isSelected;
    emit insertRectBtnSelectedChanged();
}

void ToolBarController::setinsertCycleBtnSelected(bool isSelected) 
{
    m_insertCycleBtnSelected = isSelected;
    emit insertCycleBtnSelectedChanged();
}

void ToolBarController::setInsertRectCallback(void* cb, void* userData) 
{
    m_insertRectCallback = reinterpret_cast<ToolbarCallback>(cb);
    m_insertRectUserData = userData;
}

void ToolBarController::setInsertCycleCallback(void* cb, void* userData) 
{
    m_insertCycleCallback = reinterpret_cast<ToolbarCallback>(cb);
    m_insertRectUserData = userData;
}

void ToolBarController::setUndoCallback(void* cb, void* userData) 
{
    m_undoCallback = reinterpret_cast<ToolbarCallback>(cb);
    m_insertRectUserData = userData;
}

void ToolBarController::setDeleteCallback(void* cb, void* userData) 
{
    m_deleteCallback = reinterpret_cast<ToolbarCallback>(cb);
    m_insertRectUserData = userData;
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
    return m_deleteEnabled;
}

void ToolBarController::onInsertRectAction()
{
    m_insertRectCallback(m_insertRectUserData);
}

void ToolBarController::onInsertCycleAction()
{
    m_insertCycleCallback(m_insertRectUserData);
}

void ToolBarController::onUndoAction()
{
    m_undoCallback(m_insertRectUserData);
}

void ToolBarController::onDeleteAction()
{
    m_deleteCallback(m_insertRectUserData);
}
