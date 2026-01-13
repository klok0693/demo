#pragma once

#include <QQmlApplicationEngine>
#include <QPainter>

#include "ui/ui_bridge_macros.h"
#include "ui/ui_export.h"

#ifdef __cplusplus
extern "C" {
#endif

UI_EXPORT void set_engine(QQmlApplicationEngine* engine);

UI_EXPORT QQmlApplicationEngine* get_engine();

#ifdef __cplusplus
}
#endif
