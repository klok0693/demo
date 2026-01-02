#include <QGuiApplication>
#include <QQmlApplicationEngine>
#include <QQmlContext>
#include <QObject>
#include <QVector>
#include <QtGlobal>

#include <iostream>

#include "../ui/toolbar/ToolBarController.h"
#include "../ui/property/PropertiesPanelController.h"
#include "../ui/layer/QtLayersPanelController.h"
#include "../ui/element/QtCanvasItem.h"
#include "../ui/canvas/QtCanvasController.h"

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

int main(int argc, char *argv[])
{
    QGuiApplication app(argc, argv);

    qmlRegisterType<QtCanvasItem>("App.Canvas", 1, 0, "QtCanvasUI");
    
    QQmlApplicationEngine engine;

    UiState uiState;
    engine.rootContext()->setContextProperty("uiState", &uiState);
    
    QtCanvasController canvasController;
    engine.rootContext()->setContextProperty("canvasController", &canvasController);

    //QQmlApplicationEngine engine;

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
    
    // QQuickStyle::setStyle("Imagine");    
    return app.exec();
}

#include "main.moc"