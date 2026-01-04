#pragma once

#include <jni.h>

#include <QQmlApplicationEngine>
#include <QPainter>

#if defined(_WIN32)
  #define UI_API __declspec(dllexport)
#else
  #define UI_API __attribute__((visibility("default")))
#endif

void setEngine(QQmlApplicationEngine* engine);

#ifdef __cplusplus
extern "C" {
#endif

typedef void (*StatusFunc)(int);

UI_API void setStatusCallback(StatusFunc callback);
UI_API void emitStatus(int status);

UI_API void setToolState(int toolId, int enabled);

// --------- ROOT -----------

UI_API void setCursor(const char* cursor);

// --------- TOOLBAR -----------

typedef void (*ToolbarCallback)(void* ctx);

UI_API void setToolBarInsertRectCallback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData
);

UI_API void setToolBarInsertCycleCallback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData
);

UI_API void setToolBarUndoCallback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData
);

UI_API void setToolBarDeleteCallback(
        void* toolbar,
        ToolbarCallback cb,
        void* userData
);

UI_API void setToolBarInsertRectBtnSelected(void* toolbar, bool isSelected);

UI_API void setToolBarInsertCycleBtnSelected(void* toolbar, bool isSelected);

UI_API void setToolBarDeleteBtnDisabled(void* toolbar, bool disabled);

// --------- CANVAS -----------

struct PainterContext {
    QPainter* painter;
};

typedef void (*DrawingCallback)(PainterContext* ctx);

typedef void (*MouseEventCallback)(double x, double y);

typedef void (*InitCanvasControllerFunc)(double x, double y);

UI_API void updateCanvasItem(void* canvasItem);

UI_API void setDrawingCallback(
        void* canvasItem,
        DrawingCallback callback
);

UI_API void initCanvasController(
        void* canvasItem,
        InitCanvasControllerFunc callback
);

UI_API void setMousePressedCallback(
        void* canvasController,
        MouseEventCallback callback
);

UI_API void setDragDetectedCallback(
        void* canvasController,
        MouseEventCallback callback
);

UI_API void setMouseDraggedCallback(
        void* canvasController,
        MouseEventCallback callback
);

UI_API void setMouseReleasedCallback(
        void* canvasController,
        MouseEventCallback callback
);

UI_API void ui_painter_set_fill(
    PainterContext* ctx,
    const char* utf8
);

UI_API void ui_painter_fill_rect(
    PainterContext* ctx,
    double x, double y, double w, double h
);

// --------- THREAD -----------

typedef void (*JavaRunnableCallback)(jlong id);

UI_API void ui_run_later(
    JavaRunnableCallback cb,
    jlong id
);

#ifdef __cplusplus
}
#endif
