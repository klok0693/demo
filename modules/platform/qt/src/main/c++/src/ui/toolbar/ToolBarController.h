#pragma once

#include <QObject>

#include "../ui_bridge.h"

class ToolBarController : public QObject
{
    Q_OBJECT
    Q_PROPERTY(bool deleteEnabled READ deleteEnabled NOTIFY deleteEnabledChanged)

public:
    explicit ToolBarController(QObject* parent = nullptr);

    bool deleteEnabled() const;

    void setInsertRectCallback(void* cb, void* userData);
    void setInsertCycleCallback(void* cb, void* userData);
    void setUndoCallback(void* cb, void* userData);
    void setDeleteCallback(void* cb, void* userData);

public slots:
    void onInsertRectAction();
    void onInsertCycleAction();
    void onUndoAction();
    void onDeleteAction();

signals:
    void deleteEnabledChanged();

private:
    bool m_deleteEnabled = false;
    
    ToolbarCallback insertRectCallback = nullptr;
    ToolbarCallback insertCycleCallback = nullptr;
    ToolbarCallback undoCallback = nullptr;
    ToolbarCallback deleteCallback = nullptr;

    void* insertRectUserData = nullptr;
};
