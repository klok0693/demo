#pragma once

#include "ui/ui_bridge.h"

#ifdef __cplusplus
extern "C" {
#endif

typedef void (*UpdatePropertyCallback)(const char* utf8);

UI_API void set_properties_panel_enabled(
    void* propertiesPanel,
    bool isEnabled
);

UI_API void ui_set_x(
    void* propertiesPanel,
    const char* utf8
);

UI_API void ui_set_y(
    void* propertiesPanel,
    const char* utf8
);

UI_API void ui_set_width(
    void* propertiesPanel,
    const char* utf8
);

UI_API void ui_set_height(
    void* propertiesPanel,
    const char* utf8
);

UI_API void ui_set_layer(
    void* propertiesPanel,
    const char* utf8
);

UI_API void clear_and_disable(
    void* propertiesPanel,
    const char* utf8
);

UI_API void set_update_x_callback(
    void* propertiesPanel,
    UpdatePropertyCallback callback
);

UI_API void set_update_y_callback(
    void* propertiesPanel,
    UpdatePropertyCallback callback
);

UI_API void set_update_width_callback(
    void* propertiesPanel,
    UpdatePropertyCallback callback
);

UI_API void set_update_height_callback(
    void* propertiesPanel,
    UpdatePropertyCallback callback
);

UI_API void set_update_layer_callback(
    void* propertiesPanel,
    UpdatePropertyCallback callback
);

#ifdef __cplusplus
}
#endif