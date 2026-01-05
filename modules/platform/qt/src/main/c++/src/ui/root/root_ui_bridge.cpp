#include "ui/root/root_ui_bridge.h"

#include <QQmlApplicationEngine>
#include <QQmlContext>

#include "ui/root/RootView.h"

#ifdef __cplusplus
extern "C" {
#endif

UI_API void set_cursor(const char* utf8) {
    QObject* obj =
        get_engine()->rootContext()
          ->contextProperty("rootView")
          .value<QObject*>();

    auto* rootView = qobject_cast<RootView*>(obj);    
    const QString cursor = QString::fromUtf8(utf8); 

    QMetaObject::invokeMethod(
        rootView,
        [rootView, cursor]() {
            rootView->setCursor(cursor);
        },
        Qt::QueuedConnection
    );
}

#ifdef __cplusplus
}
#endif