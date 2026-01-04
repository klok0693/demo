#pragma once

#include <QQmlApplicationEngine>

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

#ifdef __cplusplus
}
#endif
