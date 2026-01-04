cmake_minimum_required(VERSION 3.16)

project(QtQuickNoJS LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# ---- Qt ----

set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)

#set(CMAKE_PREFIX_PATH "~/6.10.0/gcc_64")

find_package(Qt6 REQUIRED COMPONENTS 
    Quick 
    QuickControls2
    Gui)


# ---- JAVA / JNI ----
find_package(JNI REQUIRED)

message(STATUS "JNI include dirs: ${JNI_INCLUDE_DIRS}")
message(STATUS "JNI libraries: ${JNI_LIBRARIES}")    

#add_executable(qt-jvm
#    src/app/main.cpp
#)

#target_include_directories(qt-jvm PRIVATE
#    ${JNI_INCLUDE_DIRS}
#)

#target_link_libraries(qt-jvm
#    PUBLIC Qt6::Quick
#    PUBLIC Qt6::Core
#    Qt6::Gui
#    ${JNI_LIBRARIES}
#)

# ------ UI -------------


add_library(ui SHARED)

target_sources(ui PRIVATE
    src/ui/toolbar/ToolBarController.cpp
    src/ui/property/PropertiesPanelController.cpp
    src/ui/layer/QtLayersPanelController.cpp
    src/ui/canvas/QtCanvasController.cpp
    src/ui/element/QtCanvasItem.cpp
    src/ui/ui_bridge.cpp
    src/ui/root/RootView.cpp
)

target_include_directories(ui PUBLIC 
    src/ui
    ${JNI_INCLUDE_DIRS}
)

#set_target_properties(ui PROPERTIES
#    CXX_VISIBILITY_PRESET default
#    VISIBILITY_INLINES_HIDDEN OFF
#)

target_link_libraries(ui
    PUBLIC Qt6::Quick
    PUBLIC Qt6::Core
    Qt6::Gui
    ${JNI_LIBRARIES}
)


add_executable(QtDemo
    src/app/main.cpp
    src/resources/resources.qrc
)

target_include_directories(QtDemo
    PRIVATE ${JNI_INCLUDE_DIRS}
)

#target_link_libraries(QtDemo
#    PRIVATE ${JNI_LIBRARIES}
#)

#BUILD_RPATH "${JAVA_HOME}/lib/server"
#BUILD_RPATH "$ENV{JAVA_HOME}/lib/server"
set_target_properties(QtDemo PROPERTIES
    BUILD_RPATH "$ORIGIN"
)

target_link_libraries(QtDemo
    Qt6::Quick
    Qt6::Gui
    ui
    ${JNI_LIBRARIES}
)