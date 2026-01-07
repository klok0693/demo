#pragma once

#if defined(_WIN32)
  #define UI_API __declspec(dllexport)
#else
  #define UI_API __attribute__((visibility("default")))
#endif