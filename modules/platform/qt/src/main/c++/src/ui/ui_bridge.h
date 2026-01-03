#pragma once

#if defined(_WIN32)
  #define UI_API __declspec(dllexport)
#else
  #define UI_API __attribute__((visibility("default")))
#endif

#ifdef __cplusplus
extern "C" {
#endif

typedef void (*StatusFunc)(int);

UI_API void setStatusCallback(StatusFunc callback);
UI_API void emitStatus(int status);

UI_API void setToolState(int toolId, int enabled);

#ifdef __cplusplus
}
#endif
