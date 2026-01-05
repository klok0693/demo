#include "realization/thread_manager_ui_bridge.h"

#include <QGuiApplication>
#include <QQmlApplicationEngine>
#include <QQmlContext>

#ifdef __cplusplus
extern "C" {
#endif

UI_API void ui_run_later(
    JavaRunnableCallback cb,
    jlong id
) {
    QMetaObject::invokeMethod(
    QCoreApplication::instance(),
    [cb, id]() {
        cb(id);
    },
    Qt::QueuedConnection
    );
}

#ifdef __cplusplus
}
#endif