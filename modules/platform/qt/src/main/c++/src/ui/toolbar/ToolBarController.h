#pragma once

#include <QObject>

#include "../ui_bridge.h"

class ToolBarController : public QObject
{
    Q_OBJECT
    Q_PROPERTY(bool deleteEnabled READ deleteEnabled NOTIFY deleteEnabledChanged)
    Q_PROPERTY(bool insertRectBtnSelected READ insertRectBtnSelected NOTIFY insertRectBtnSelectedChanged)
    Q_PROPERTY(bool insertCycleBtnSelected READ insertCycleBtnSelected NOTIFY insertCycleBtnSelectedChanged)

public:
    explicit ToolBarController(QObject* parent = nullptr);

    bool deleteEnabled() const;
    bool insertRectBtnSelected() const;
    bool insertCycleBtnSelected() const;

    void setInsertRectCallback(void* cb, void* userData);
    void setInsertCycleCallback(void* cb, void* userData);
    void setUndoCallback(void* cb, void* userData);
    void setDeleteCallback(void* cb, void* userData);

    void setDeleteBtnDisabled(bool isDisabled);
    void setinsertRectBtnSelected(bool isSelected);
    void setinsertCycleBtnSelected(bool isSelected);

public slots:
    void onInsertRectAction();
    void onInsertCycleAction();
    void onUndoAction();
    void onDeleteAction();

signals:
    void deleteEnabledChanged();
    void insertRectBtnSelectedChanged();
    void insertCycleBtnSelectedChanged();

private:
    bool m_insertRectBtnSelected = false;
    bool m_insertCycleBtnSelected = false;
    bool m_deleteEnabled = false;
    
    ToolbarCallback insertRectCallback = nullptr;
    ToolbarCallback insertCycleCallback = nullptr;
    ToolbarCallback undoCallback = nullptr;
    ToolbarCallback deleteCallback = nullptr;

    void* insertRectUserData = nullptr;
};
