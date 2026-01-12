#pragma once

#include "ui/ui_bridge_macros.h"

#ifdef __cplusplus
extern "C" {
#endif

typedef void (*MouseEventCallback)(double x, double y);

typedef void (*MousePressedCallback)(
        double x, 
        double y, 
        bool isAdditional, 
        bool isShift
);

UI_API void set_mouse_pressed_callback(
        void* canvasController,
        MousePressedCallback callback
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