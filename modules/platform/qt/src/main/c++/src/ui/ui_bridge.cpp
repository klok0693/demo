#include "ui_bridge.h"
#include "root/RootView.h"
#include "toolbar/ToolBarController.h"

#include <QGuiApplication>
#include <QQmlApplicationEngine>
#include <QQmlContext>

static QQmlApplicationEngine* engine = nullptr;

void setEngine(QQmlApplicationEngine* eng) {
    engine = eng;
}

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

// --------- ROOT -----------

UI_API void setCursor(const char* utf8) {
    QObject* obj =
        engine->rootContext()
          ->contextProperty("rootView")
          .value<QObject*>();

    auto* rootView = qobject_cast<RootView*>(obj);    
     const QString cursor = QString::fromUtf8(utf8); 
    //rootView->setCursor(QString::fromUtf8(utf8)); 

    QMetaObject::invokeMethod(
        rootView,
        [rootView, cursor]() {
            rootView->setCursor(cursor);
        },
        Qt::QueuedConnection
    );
}

// --------- TOOLBAR -----------

UI_API ToolBarController* ui_toolbar_get() {
    QObject* obj =
        engine->rootContext()
          ->contextProperty("toolBarController")
          .value<QObject*>();

    auto* toolbar = qobject_cast<ToolBarController*>(obj);

    return toolbar;
}

UI_API void setToolBarInsertRectBtnSelected(void* toolbar, bool isSelected) {
    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setinsertRectBtnSelected(isSelected);
}

UI_API void setToolBarInsertCycleBtnSelected(void* toolbar, bool isSelected) {
    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setinsertCycleBtnSelected(isSelected);
}

UI_API void setToolBarDeleteBtnDisabled(void* toolbar, bool disabled) {
    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setDeleteBtnDisabled(disabled);
}

UI_API void setToolBarInsertRectCallback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData) {

    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setInsertRectCallback((void*)cb, userData);
}

UI_API void setToolBarInsertCycleCallback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData) {

    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setInsertCycleCallback((void*)cb, userData);
}

UI_API void setToolBarUndoCallback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData) {

    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setUndoCallback((void*)cb, userData);
}

UI_API void setToolBarDeleteCallback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData) {

    auto* ctrl = static_cast<ToolBarController*>(toolbar);
    ctrl->setDeleteCallback((void*)cb, userData);
}


}