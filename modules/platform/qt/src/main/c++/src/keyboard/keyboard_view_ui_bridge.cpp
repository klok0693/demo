#include "keyboard/keyboard_view_ui_bridge.h"

#include <QQmlApplicationEngine>
#include <QQmlContext>

#include "keyboard/QtKeyboardView.h"

#ifdef __cplusplus
extern "C" {
#endif

UI_API QtKeyboardView* ui_keyboard_view_get() {
    QObject* obj =
        get_engine()->rootContext()
          ->contextProperty("keyboardView")
          .value<QObject*>();

    return qobject_cast<QtKeyboardView*>(obj);
}

UI_API void set_key_callback(
    void* keyboardController,
    KeyCallback callback
) {
    auto* ctrl = static_cast<QtKeyboardView*>(keyboardController);
    ctrl->setKeyCallback(callback);
}

#ifdef __cplusplus
}
#endif