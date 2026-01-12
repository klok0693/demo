#include "ui/property/PropertiesPanelController.h"

#include <QQmlContext>

PropertiesPanelController::PropertiesPanelController(QObject* parent)
    : QObject(parent)
{
}

bool PropertiesPanelController::enabled() const
{
    return m_enabled;
}

void PropertiesPanelController::setEnabled(bool isEnabled) {
    m_enabled = isEnabled;
    emit enabledChanged();
}

QString PropertiesPanelController::x() const
{
    return m_x;
}

void PropertiesPanelController::setX(const QString& str) {
    m_x = str;
    emit valuesChanged();
}

QString PropertiesPanelController::y() const
{
    return m_y;
}

void PropertiesPanelController::setY(const QString& str) {
    m_y = str;
    emit valuesChanged();
}

QString PropertiesPanelController::width() const
{
    return m_width;
}

void PropertiesPanelController::setWidth(const QString& str) {
    m_width = str;
    emit valuesChanged();
}

QString PropertiesPanelController::height() const
{
    return m_height;
}

void PropertiesPanelController::setHeight(const QString& str) {
    m_height = str;
    emit valuesChanged();
}

QString PropertiesPanelController::layer() const
{
    return m_layer;
}

void PropertiesPanelController::setLayer(const QString& str) {
    m_layer = str;
    emit valuesChanged();
}

static constexpr unsigned int str2int(const char* str, int h = 0)
{
    return !str[h] ? 5381 : (str2int(str, h+1) * 33) ^ str[h];
}

void PropertiesPanelController::clearAndDisable(const QString& id) {
    QByteArray ba = id.toLocal8Bit();
    const char *str = ba.data();

    switch (str2int(str))
    {
    case str2int("x"):
        m_x.clear();
        break;

    case str2int("y"):
        m_y.clear();
        break; 
        
    case str2int("width"):
        m_width.clear();
        break;     
    
    case str2int("height"):
        m_height.clear();
        break; 

    case str2int("layer"):
        m_layer.clear();
        break; 

    default:
        break;
    }

    emit valuesChanged();
}

void PropertiesPanelController::setUpdateXCallback(UpdatePropertyCallback callback) {
    m_updateXCallback = callback;
}

void PropertiesPanelController::setUpdateYCallback(UpdatePropertyCallback callback) {
    m_updateYCallback = callback;
}

void PropertiesPanelController::setUpdateWidthCallback(UpdatePropertyCallback callback) {
    m_updateWidthCallback = callback;
}

void PropertiesPanelController::setUpdateHeightCallback(UpdatePropertyCallback callback) {
    m_updateHeightCallback = callback;
}

void PropertiesPanelController::setUpdateLayerCallback(UpdatePropertyCallback callback) {
    m_updateLayerCallback = callback;
}

void PropertiesPanelController::setUpdateColorCallback(UpdatePropertyCallback callback) {
    m_updateColorCallback = callback;
}

void PropertiesPanelController::updateX(const QString& v) 
{
    QByteArray utf8 = v.toUtf8();
    m_updateXCallback(utf8.constData());
}

void PropertiesPanelController::updateY(const QString& v) 
{
    QByteArray utf8 = v.toUtf8();
    m_updateYCallback(utf8.constData());
}

void PropertiesPanelController::updateWidth(const QString& v) 
{
    QByteArray utf8 = v.toUtf8();
    m_updateWidthCallback(utf8.constData());
}

void PropertiesPanelController::updateHeight(const QString& v) 
{
    QByteArray utf8 = v.toUtf8();
    m_updateHeightCallback(utf8.constData());
}

void PropertiesPanelController::updateLayer(const QString& v) 
{
    QByteArray utf8 = v.toUtf8();
    m_updateLayerCallback(utf8.constData());
}

void PropertiesPanelController::updateColor(const QString& v) 
{
    QColor color(v);
    
    // 2. Pack into ARGB integer (matching Java bits)
    // QRgb in Qt is typically 0xAARRGGBB
    unsigned int argb = color.rgba(); 
    
    // 3. Convert integer to string for transfer
    // We use std::to_string to get a decimal representation of the int
    std::string convertedColor = std::to_string(static_cast<int>(argb));

    m_updateColorCallback(convertedColor.c_str());
}