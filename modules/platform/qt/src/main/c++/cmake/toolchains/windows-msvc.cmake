# Identify target system
set(CMAKE_SYSTEM_NAME Windows)
set(CMAKE_SYSTEM_PROCESSOR x86_64)

# MSVC compilers are selected implicitly
# Do NOT set CMAKE_CXX_COMPILER explicitly

# Optional: static runtime
set(CMAKE_MSVC_RUNTIME_LIBRARY "MultiThreaded$<$<CONFIG:Debug>:Debug>")

# Optional: Qt location
# set(CMAKE_PREFIX_PATH "C:/Qt/6.6.1/msvc2019_64")

set(CMAKE_POSITION_INDEPENDENT_CODE ON)