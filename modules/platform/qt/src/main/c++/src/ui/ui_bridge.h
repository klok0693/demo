#pragma once

#include <QQmlApplicationEngine>
#include <QPainter>

#if defined(_WIN32)
  #define UI_API __declspec(dllexport)
#else
  #define UI_API __attribute__((visibility("default")))
#endif

#ifdef __cplusplus
extern "C" {
#endif

void set_engine(QQmlApplicationEngine* engine);

QQmlApplicationEngine* get_engine();

struct PainterContext {
    QPainter* painter;
};

#ifdef __cplusplus
}
#endif
