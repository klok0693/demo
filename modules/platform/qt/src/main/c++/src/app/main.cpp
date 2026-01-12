#include <QGuiApplication>
#include <QQmlApplicationEngine>
#include <QQmlContext>
#include <QObject>
#include <QVector>
#include <QtGlobal>

#include <jni.h>
#include <iostream>

#include "ui/toolbar/ToolBarController.h"
#include "ui/property/PropertiesPanelController.h"
#include "ui/layer/QtLayersPanelController.h"
#include "ui/element/QtCanvasItem.h"
#include "ui/canvas/QtCanvasController.h"
#include "ui/root/RootView.h"

#include "keyboard/QtKeyboardView.h"

static JavaVM* gJvm = nullptr;

void startJvmAndCallJava() 
{
    JavaVMInitArgs vm_args{};
    JavaVMOption options[4];

    options[0].optionString = const_cast<char*>("-Djava.class.path=qt-1.2-raw.jar");
    options[1].optionString = const_cast<char*>("-Xmx256m");
    options[2].optionString = const_cast<char*>("--enable-native-access=ALL-UNNAMED");
    options[3].optionString = const_cast<char*>("--enable-preview");
    options[4].optionString = const_cast<char*>("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005");

    vm_args.version = JNI_VERSION_10;
    vm_args.nOptions = 5;
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

    gJvm->AttachCurrentThread((void**)&env, nullptr);

    qDebug() << "Jvm initialized";
}

int main(int argc, char *argv[])
{
    QGuiApplication app(argc, argv);
    QQmlApplicationEngine engine;

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
    
    QtKeyboardView keyboardView;
    engine.rootContext()->setContextProperty("keyboardView", &keyboardView);

    qmlRegisterType<QtCanvasItem>("App.Canvas", 1, 0, "QtCanvasUI");

    engine.load(QUrl(QStringLiteral("qrc:/MainView.qml")));
    if (engine.rootObjects().isEmpty()) {
        return -1;
    }
    
    set_engine(&engine);
    startJvmAndCallJava();

    // QQuickStyle::setStyle("Imagine");    
    return app.exec();
}

#include "main.moc"