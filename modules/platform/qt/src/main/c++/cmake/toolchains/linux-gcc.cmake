# Identify target system
set(CMAKE_SYSTEM_NAME Linux)
set(CMAKE_SYSTEM_PROCESSOR x86_64)

# Compilers
set(CMAKE_C_COMPILER gcc)
set(CMAKE_CXX_COMPILER g++)

# Optional: prefer Ninja on Linux
set(CMAKE_GENERATOR "Ninja" CACHE INTERNAL "")

# Optional: Qt location (if not in system paths)
# set(CMAKE_PREFIX_PATH "/opt/Qt/6.6.1/gcc_64")

# Optional: global capabilities
set(CMAKE_POSITION_INDEPENDENT_CODE ON)
