#include "ui/ui_bridge.h"

#ifdef __cplusplus
extern "C" {
#endif

typedef void (*MouseEventCallback)(double x, double y);

UI_API void set_mouse_pressed_callback(
        void* canvasController,
        MouseEventCallback callback
);

UI_API void set_drag_detected_callback(
        void* canvasController,
        MouseEventCallback callback
);

UI_API void set_mouse_dragged_callback(
        void* canvasController,
        MouseEventCallback callback
);

UI_API void set_mouse_released_callback(
        void* canvasController,
        MouseEventCallback callback
);

#ifdef __cplusplus
}
#endif