#pragma once

#include <QObject>
#include <QString>
//#include <QColor>

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

    QString x() const;
    QString y() const;
    QString width() const;
    QString height() const;
    QString layer() const;

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
};
