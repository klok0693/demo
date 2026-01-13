#pragma once

#include <QObject>
#include <QKeyEvent>

#include "keyboard/keyboard_view_ui_bridge.h"
#include "ui/ui_export.h"

class UI_EXPORT QtKeyboardView : public QObject {
    Q_OBJECT

public:
    explicit QtKeyboardView(QObject* parent = nullptr);

    void setKeyCallback(KeyCallback callback);

public slots:

    Q_INVOKABLE void onKeyEvent(int key, int modifiers);

private:
    KeyCallback m_keyCallback = nullptr;
};