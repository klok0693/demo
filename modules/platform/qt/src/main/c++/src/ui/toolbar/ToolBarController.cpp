#include "ToolBarController.h"

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
    // business logic
}

void ToolBarController::onInsertCycleAction()
{
    // business logic
}

void ToolBarController::onUndoAction()
{
    // business logic
}

void ToolBarController::onDeleteAction()
{
    // business logic
}
