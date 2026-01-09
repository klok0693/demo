#pragma once

#include <QQmlApplicationEngine>
#include <QPainter>

#include "ui/ui_bridge_macros.h"

#ifdef __cplusplus
extern "C" {
#endif

void set_engine(QQmlApplicationEngine* engine);

QQmlApplicationEngine* get_engine();

#ifdef __cplusplus
}
#endif
