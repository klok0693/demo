#include "ui/layer/layers_panel_ui_bridge.h"

#include <QQmlApplicationEngine>
#include <QQmlContext>

#include "ui/layer/QtLayersPanelController.h"
#include "ui/ui_bridge.h"

#ifdef __cplusplus
extern "C" {
#endif

UI_API QtLayersPanelController* ui_layers_controller_get() 
{
    QObject* obj =
        get_engine()->rootContext()
          ->contextProperty("layersTreeController")
          .value<QObject*>();

    return qobject_cast<QtLayersPanelController*>(obj);
}

UI_API void ui_layers_update(
    void* layersController,
    const LayersSnapshot* snapshot) 
{
    auto* ctrl = static_cast<QtLayersPanelController*>(layersController);
    ctrl->layersUpdate(snapshot);
}

UI_API void ui_layers_panel_cleanup(
    void* layersController) 
{
    auto* ctrl = static_cast<QtLayersPanelController*>(layersController);
    ctrl->cleanUp();
}

UI_API void set_selected_ids(
    void* layersController,
    const char* const* ids, 
    jsize_t count) 
{
    auto* ctrl = static_cast<QtLayersPanelController*>(layersController);
    ctrl->setSelectedIds(ids, count);
}

UI_API void set_select_shape_callback(
    void* layersController,
    SelectShapeCallback callback) 
{
    auto* ctrl = static_cast<QtLayersPanelController*>(layersController);
    ctrl->setOnShapeSelectCallback(callback);
}

UI_API void ui_layers_panel_unselect_all(
    void* layersController) 
{
    auto* ctrl = static_cast<QtLayersPanelController*>(layersController);
    ctrl->unSelectAll();
}

#ifdef __cplusplus
}
#endif