#pragma once

#include "ui/ui_bridge.h"

#ifdef __cplusplus
extern "C" {
#endif

typedef void (*KeyCallback)(const char* key, bool isCtrl, bool isShift);

UI_API void set_key_callback(
    void* keyboardController,
    KeyCallback callback
);

#ifdef __cplusplus
}
#endif