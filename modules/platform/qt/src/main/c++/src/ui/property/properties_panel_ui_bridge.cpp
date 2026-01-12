#include "ui/property/properties_panel_ui_bridge.h"

#include <QQmlApplicationEngine>
#include <QQmlContext>
#include <QDebug>

#include "ui/property/PropertiesPanelController.h"

#ifdef __cplusplus
extern "C" {
#endif

UI_API PropertiesPanelController* ui_properties_panel_get() {
    QObject* obj =
        get_engine()->rootContext()
          ->contextProperty("propertiesPanelController")
          .value<QObject*>();

    return qobject_cast<PropertiesPanelController*>(obj);
}

UI_API void set_properties_panel_enabled(
    void* propertiesPanel,
    bool isEnabled
) {
    auto* ctrl = static_cast<PropertiesPanelController*>(propertiesPanel);
    ctrl->setEnabled(isEnabled);
}

UI_API void ui_set_x(
    void* propertiesPanel,
    const char* utf8
) {
    const QString str = QString::fromUtf8(utf8);
    auto* ctrl = static_cast<PropertiesPanelController*>(propertiesPanel);
    ctrl->setX(str);
}

UI_API void ui_set_y(
    void* propertiesPanel,
    const char* utf8
) {
    const QString str = QString::fromUtf8(utf8);
    auto* ctrl = static_cast<PropertiesPanelController*>(propertiesPanel);
    ctrl->setY(str);
}

UI_API void ui_set_width(
    void* propertiesPanel,
    const char* utf8
) {
    const QString str = QString::fromUtf8(utf8);
    auto* ctrl = static_cast<PropertiesPanelController*>(propertiesPanel);
    ctrl->setWidth(str);
}

UI_API void ui_set_height(
    void* propertiesPanel,
    const char* utf8
) {
    const QString str = QString::fromUtf8(utf8);
    auto* ctrl = static_cast<PropertiesPanelController*>(propertiesPanel);
    ctrl->setHeight(str);
}

UI_API void ui_set_layer(
    void* propertiesPanel,
    const char* utf8
) {
    const QString str = QString::fromUtf8(utf8);
    auto* ctrl = static_cast<PropertiesPanelController*>(propertiesPanel);
    ctrl->setLayer(str);
}

UI_API void clear_and_disable(
    void* propertiesPanel,
    const char* utf8
) {
    const QString str = QString::fromUtf8(utf8);
    auto* ctrl = static_cast<PropertiesPanelController*>(propertiesPanel);
    ctrl->clearAndDisable(str);
}

UI_API void set_update_x_callback(
    void* propertiesPanel,
    UpdatePropertyCallback callback
) {
    auto* ctrl = static_cast<PropertiesPanelController*>(propertiesPanel);
    ctrl->setUpdateXCallback(callback);
}

UI_API void set_update_y_callback(
    void* propertiesPanel,
    UpdatePropertyCallback callback
) {
    auto* ctrl = static_cast<PropertiesPanelController*>(propertiesPanel);
    ctrl->setUpdateYCallback(callback);
}

UI_API void set_update_width_callback(
    void* propertiesPanel,
    UpdatePropertyCallback callback
) {
    auto* ctrl = static_cast<PropertiesPanelController*>(propertiesPanel);
    ctrl->setUpdateWidthCallback(callback);
}

UI_API void set_update_height_callback(
    void* propertiesPanel,
    UpdatePropertyCallback callback
) {
    auto* ctrl = static_cast<PropertiesPanelController*>(propertiesPanel);
    ctrl->setUpdateHeightCallback(callback);
}

UI_API void set_update_layer_callback(
    void* propertiesPanel,
    UpdatePropertyCallback callback
) {
    auto* ctrl = static_cast<PropertiesPanelController*>(propertiesPanel);
    ctrl->setUpdateLayerCallback(callback);
}

UI_API void set_update_color_callback(
    void* propertiesPanel,
    UpdatePropertyCallback callback
) {
    auto* ctrl = static_cast<PropertiesPanelController*>(propertiesPanel);
    ctrl->setUpdateColorCallback(callback);
}

#ifdef __cplusplus
}
#endif