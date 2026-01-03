#include "ui_bridge.h"

#include <QGuiApplication>

extern "C" {

typedef void (*StatusFunc)(int);

static StatusFunc javaCallback = nullptr;

UI_API void setStatusCallback(StatusFunc callback) {
    javaCallback = callback;
}

UI_API void emitStatus(int status) {
    if (javaCallback) {
        javaCallback(status);
    }
}

void setToolState(int toolId, int enabled) {
    qDebug() << "Java says: tool" << toolId << "enabled:" << enabled;
}

}