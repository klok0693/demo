#include "PropertiesPanelController.h"

PropertiesPanelController::PropertiesPanelController(QObject* parent)
    : QObject(parent)
{
}

bool PropertiesPanelController::enabled() const
{
    return m_enabled;
}

QString PropertiesPanelController::x() const
{
    return QString("mock");
}

QString PropertiesPanelController::y() const
{
    return QString("mock");
}

QString PropertiesPanelController::width() const
{
    return QString("mock");
}

QString PropertiesPanelController::height() const
{
    return QString("mock");
}

QString PropertiesPanelController::layer() const
{
    return QString("mock");
}

void PropertiesPanelController::updateX(const QString& v) 
{
    // business logic
}

void PropertiesPanelController::updateY(const QString& v) 
{
    // business logic
}

void PropertiesPanelController::updateWidth(const QString& v) 
{
    // business logic
}

void PropertiesPanelController::updateHeight(const QString& v) 
{
    // business logic
}

void PropertiesPanelController::updateLayer(const QString& v) 
{
    // business logic
}

/* void PropertiesPanelController::updateColor(const QColor& c) 
{
    // business logic
} */

/* void PropertiesPanelController::enabledChanged()
{
    // business logic
}

void PropertiesPanelController::valuesChanged()
{
    // business logic
} */