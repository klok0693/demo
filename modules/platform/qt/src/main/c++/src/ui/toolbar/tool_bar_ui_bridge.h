#include "ui/ui_bridge.h"

#ifdef __cplusplus
extern "C" {
#endif

typedef void (*ToolbarCallback)(void* ctx);

UI_API void set_toolbar_insert_rect_callback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData
);

UI_API void set_toolbar_insert_cycle_callback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData
);

UI_API void set_toolbar_undo_callback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData
);

UI_API void set_toolbar_delete_callback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData
);

UI_API void set_toolbar_insert_rect_btn_selected(void* toolbar, bool isSelected);

UI_API void set_toolbar_insert_cycle_btn_selected(void* toolbar, bool isSelected);

UI_API void set_toolbar_delete_btn_disabled(void* toolbar, bool disabled);

#ifdef __cplusplus
}
#endif