#pragma once
#include <QtCore/qglobal.h>

#if defined(UI_LIBRARY)
    #define UI_EXPORT Q_DECL_EXPORT
#else
    #define UI_EXPORT Q_DECL_IMPORT
#endif