#include <QGuiApplication>
#include <QQmlApplicationEngine>
#include <QQmlContext>
#include <QObject>
#include <QVector>
#include <QtGlobal>

#include <jni.h>
#include <iostream>

#include "../ui/toolbar/ToolBarController.h"
#include "../ui/property/PropertiesPanelController.h"
#include "../ui/layer/QtLayersPanelController.h"
#include "../ui/element/QtCanvasItem.h"
#include "../ui/canvas/QtCanvasController.h"
#include "../ui/ui_bridge.h"
#include "../ui/root/RootView.h"

/* extern "C" {
    typedef void (*StatusFunc)(int);
    UI_API void setStatusCallback(StatusFunc callback);
} */

/* StatusFunc javaCallback = nullptr;

UI_API void setStatusCallback(StatusFunc callback) {
    javaCallback = callback;
} */

class UiState : public QObject {
    Q_OBJECT
    Q_PROPERTY(bool labelVisible READ labelVisible NOTIFY labelVisibleChanged)

public:
    explicit UiState(QObject *parent = nullptr)
        : QObject(parent) {}

    bool labelVisible() const {
        return m_labelVisible;
    }

public slots:
    void onButtonClicked() {
        if (!m_labelVisible) {
            m_labelVisible = true;
            emit labelVisibleChanged();
        }
    }

signals:
    void labelVisibleChanged();

private:
    bool m_labelVisible = false;
};


static JavaVM* gJvm = nullptr;

void startJvmAndCallJava() {
    JavaVMInitArgs vm_args{};
    JavaVMOption options[4];

    options[0].optionString = const_cast<char*>("-Djava.class.path=qt-1.2-raw.jar");
    options[1].optionString = const_cast<char*>("-Xmx256m");
    options[2].optionString = const_cast<char*>("--enable-native-access=ALL-UNNAMED");
    options[3].optionString = const_cast<char*>("--enable-preview");

    vm_args.version = JNI_VERSION_10;
    vm_args.nOptions = 4;
    vm_args.options = options;
    vm_args.ignoreUnrecognized = JNI_FALSE;

    JNIEnv* env = nullptr;

    jint res = JNI_CreateJavaVM(&gJvm,
                               reinterpret_cast<void**>(&env),
                               &vm_args);

    if (res != JNI_OK) {
        qFatal("Failed to create JVM");
    }

    jclass cls = env->FindClass("org/example/demo/QtMain");
    if (!cls) {
        qFatal("Cannot find QtBridge class");
    }

    jmethodID mid = env->GetStaticMethodID(cls, "init", "()V");
    if (!mid) {
        qFatal("Cannot find init() method");
    }

    env->CallStaticVoidMethod(cls, mid);

    if (env->ExceptionCheck()) {
        env->ExceptionDescribe();
        env->ExceptionClear();
    }

    qDebug() << "Java init() executed";

    //if (javaCallback) {
        //qDebug() << "Send callback";
        //JNIEnv* env = nullptr;
        gJvm->AttachCurrentThread((void**)&env, nullptr);
        emitStatus(200);
    //}
}

int main(int argc, char *argv[])
{
    QGuiApplication app(argc, argv);

    qmlRegisterType<QtCanvasItem>("App.Canvas", 1, 0, "QtCanvasUI");
    
    QQmlApplicationEngine engine;

    UiState uiState;
    engine.rootContext()->setContextProperty("uiState", &uiState);

    RootView rootView;
    engine.rootContext()->setContextProperty("rootView", &rootView);
    
    QtCanvasController canvasController;
    engine.rootContext()->setContextProperty("canvasController", &canvasController);

    ToolBarController toolBarController;
    engine.rootContext()->setContextProperty("toolBarController", &toolBarController);

    PropertiesPanelController propertiesPanelController;
    engine.rootContext()->setContextProperty("propertiesPanelController", &propertiesPanelController);
    
    QtLayersPanelController layersTreeController;
    engine.rootContext()->setContextProperty("layersTreeController", &layersTreeController);

    engine.load(QUrl(QStringLiteral("qrc:/MainView.qml")));
    if (engine.rootObjects().isEmpty()) {
        return -1;
    }
    
    setEngine(&engine);
    startJvmAndCallJava();

    // QQuickStyle::setStyle("Imagine");    
    return app.exec();
}

#include "main.moc"