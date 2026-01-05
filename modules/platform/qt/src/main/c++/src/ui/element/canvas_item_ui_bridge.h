#include "ui/ui_bridge.h"

#ifdef __cplusplus
extern "C" {
#endif

typedef void (*DrawingCallback)(PainterContext* ctx);

typedef void (*InitCanvasControllerFunc)(double x, double y);

UI_API void update_canvas_item(void* canvasItem);

UI_API void set_drawing_callback(
        void* canvasItem,
        DrawingCallback callback
);

UI_API void init_canvas_controller(
        void* canvasItem,
        InitCanvasControllerFunc callback
);

UI_API void ui_painter_save(PainterContext* ctx);

UI_API void ui_painter_restore(PainterContext* ctx);

UI_API void ui_painter_set_fill(
    PainterContext* ctx,
    const char* utf8
);

UI_API void ui_painter_fill_rect(
    PainterContext* ctx,
    double x, double y, double w, double h
);

UI_API void ui_painter_stroke_rect(
    PainterContext* ctx,
    double x, double y, double w, double h
);

UI_API void ui_painter_fill_oval(
    PainterContext* ctx,
    double x, double y, double w, double h
);

UI_API void ui_painter_set_opacity(
    PainterContext* ctx,
    double opacity
);

UI_API void ui_painter_set_stroke(
    PainterContext* ctx,
    const char* utf8
);

UI_API void ui_painter_set_line_width(
    PainterContext* ctx,
    double width
);

#ifdef __cplusplus
}
#endif