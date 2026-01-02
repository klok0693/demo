#pragma once

#include <QObject>

class ToolBarController : public QObject
{
    Q_OBJECT
    Q_PROPERTY(bool deleteEnabled READ deleteEnabled NOTIFY deleteEnabledChanged)

public:
    explicit ToolBarController(QObject* parent = nullptr);

    bool deleteEnabled() const;

public slots:
    void onInsertRectAction();
    void onInsertCycleAction();
    void onUndoAction();
    void onDeleteAction();

signals:
    void deleteEnabledChanged();

private:
    bool m_deleteEnabled = false;
};
