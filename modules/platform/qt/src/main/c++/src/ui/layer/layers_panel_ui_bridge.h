#pragma once

#include "ui/ui_bridge_macros.h"

#ifdef __cplusplus
extern "C" {
#endif

typedef void (*SelectShapeCallback)(const char* utf8);

typedef struct {
    int layerKey;
    int shapeCount;
    const int* shapeIds;
} LayerEntry;

typedef struct {
    int layerCount;
    const LayerEntry* layers;
} LayersSnapshot;

UI_API void ui_layers_update(
    void* layersController,
    const LayersSnapshot* snapshot
);

UI_API void ui_layers_panel_cleanup(
    void* layersController
);

UI_API void set_selected_id(
    void* layersController,
    const char* selectedId
);

UI_API void set_select_shape_callback(
    void* layersController,
    SelectShapeCallback callback
);

UI_API void ui_layers_panel_unselect_all(
    void* layersController
);

#ifdef __cplusplus
}
#endif