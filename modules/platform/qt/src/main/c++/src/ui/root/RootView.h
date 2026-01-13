#pragma once

#include <QObject>
#include <QString>

#include "ui/ui_export.h"

class UI_EXPORT RootView : public QObject {
    Q_OBJECT

public:
    explicit RootView(QObject* parent = nullptr);

    void setCursor(const QString& cursor);
};
