#include "ui/ui_bridge.h"

#include <QQmlApplicationEngine>

extern "C" {

static QQmlApplicationEngine* engine = nullptr;

UI_EXPORT void set_engine(QQmlApplicationEngine* eng)
{
    engine = eng;
}

UI_EXPORT QQmlApplicationEngine* get_engine()
{
    return engine;
}

}