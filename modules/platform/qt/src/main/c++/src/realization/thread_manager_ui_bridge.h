#include "ui/ui_bridge.h"

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif

typedef void (*JavaRunnableCallback)(jlong id);

UI_API void ui_run_later(
    JavaRunnableCallback cb,
    jlong id
);

#ifdef __cplusplus
}
#endif