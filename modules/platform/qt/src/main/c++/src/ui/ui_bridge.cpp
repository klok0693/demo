#include "ui/ui_bridge.h"

#include <QQmlApplicationEngine>

extern "C" {

static QQmlApplicationEngine* engine = nullptr;

void set_engine(QQmlApplicationEngine* eng) {
    engine = eng;
}

QQmlApplicationEngine* get_engine() {
    return engine;
}

}