#pragma once

#include <QObject>
#include <QString>
//#include <QColor>

#include "ui/property/properties_panel_ui_bridge.h"

class PropertiesPanelController : public QObject {
    Q_OBJECT
    
    Q_PROPERTY(bool enabled READ enabled NOTIFY enabledChanged)
    Q_PROPERTY(QString x READ x NOTIFY valuesChanged)
    Q_PROPERTY(QString y READ y NOTIFY valuesChanged)
    Q_PROPERTY(QString width READ width NOTIFY valuesChanged)
    Q_PROPERTY(QString height READ height NOTIFY valuesChanged)
    Q_PROPERTY(QString layer READ layer NOTIFY valuesChanged)

public:
    explicit PropertiesPanelController(QObject* parent = nullptr);

    bool enabled() const;
    void setEnabled(bool isEnabled);

    QString x() const;
    void setX(const QString& v);

    QString y() const;
    void setY(const QString& v);
    
    QString width() const;
    void setWidth(const QString& v);

    QString height() const;
    void setHeight(const QString& v);
    
    QString layer() const;
    void setLayer(const QString& v);

    void clearAndDisable(const QString& id);

    void setUpdateXCallback(UpdatePropertyCallback callback);
    void setUpdateYCallback(UpdatePropertyCallback callback);
    void setUpdateWidthCallback(UpdatePropertyCallback callback);
    void setUpdateHeightCallback(UpdatePropertyCallback callback);
    void setUpdateLayerCallback(UpdatePropertyCallback callback);

public slots:
    void updateX(const QString& v);
    void updateY(const QString& v);
    void updateWidth(const QString& v);
    void updateHeight(const QString& v);
    void updateLayer(const QString& v);
    //void updateColor(const QColor& c);

signals:
    void enabledChanged();
    void valuesChanged();

private:
    bool m_enabled = false;
    QString m_x, m_y, m_width, m_height, m_layer;

    UpdatePropertyCallback m_updateXCallback;
    UpdatePropertyCallback m_updateYCallback;
    UpdatePropertyCallback m_updateWidthCallback;
    UpdatePropertyCallback m_updateHeightCallback;
    UpdatePropertyCallback m_updateLayerCallback;
};
