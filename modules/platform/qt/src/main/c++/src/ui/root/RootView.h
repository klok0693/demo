#pragma once

#include <QObject>
#include <QString>

class RootView : public QObject {
    Q_OBJECT

public:
    explicit RootView(QObject* parent = nullptr);

    void setCursor(const QString& cursor) const;
};
