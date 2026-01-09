#pragma once

#include "ui/ui_bridge_macros.h"

#ifdef __cplusplus
extern "C" {
#endif

typedef struct {
    double x;
    double y;
} Point;

typedef void (*DrawingCallback)(void* ctx);

typedef void (*InitCanvasControllerFunc)(double x, double y);

UI_API void get_cursor_position(void* canvasController, Point* out_point);

UI_API void update_canvas_item(void* canvasItem);

UI_API void set_drawing_callback(
        void* canvasItem,
        DrawingCallback callback
);

UI_API void init_canvas_controller(
        void* canvasItem,
        InitCanvasControllerFunc callback
);

UI_API void ui_painter_save(void* ctx);

UI_API void ui_painter_restore(void* ctx);

UI_API void ui_painter_set_fill(
    void* ctx,
    const char* utf8
);

UI_API void ui_painter_fill_rect(
    void* ctx,
    double x, double y, double w, double h
);

UI_API void ui_painter_stroke_rect(
    void* ctx,
    double x, double y, double w, double h
);

UI_API void ui_painter_fill_oval(
    void* ctx,
    double x, double y, double w, double h
);

UI_API void ui_painter_set_opacity(
    void* ctx,
    double opacity
);

UI_API void ui_painter_set_stroke(
    void* ctx,
    const char* utf8
);

UI_API void ui_painter_set_line_width(
    void* ctx,
    double width
);

#ifdef __cplusplus
}
#endif