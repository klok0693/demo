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

# ------ UI -------------


add_library(ui SHARED)

target_sources(ui PRIVATE
    src/ui/toolbar/ToolBarController.cpp
    src/ui/toolbar/tool_bar_ui_bridge.cpp
    src/ui/property/PropertiesPanelController.cpp
    src/ui/property/properties_panel_ui_bridge.cpp
    src/ui/layer/QtLayersPanelController.cpp
    src/ui/layer/layers_panel_ui_bridge.cpp
    src/ui/canvas/QtCanvasController.cpp
    src/ui/canvas/canvas_view_ui_bridge.cpp
    src/ui/element/QtCanvasItem.cpp
    src/ui/element/canvas_item_ui_bridge.cpp
    src/ui/ui_bridge.cpp
    src/ui/root/RootView.cpp
    src/ui/root/root_ui_bridge.cpp

    src/realization/thread_manager_ui_bridge.cpp

    src/keyboard/QtKeyboardView.cpp
    src/keyboard/keyboard_view_ui_bridge.cpp
)

target_include_directories(ui PUBLIC 
    src/ui
    ${JNI_INCLUDE_DIRS}
    ${CMAKE_CURRENT_SOURCE_DIR}/src
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

# ------ EXECUTABLE -------------

add_executable(QtDemo
    src/app/main.cpp
    ${NATIVE_RESOURCES_DIR}/resources.qrc
)

target_include_directories(QtDemo
    PRIVATE ${JNI_INCLUDE_DIRS}
)

set_target_properties(QtDemo PROPERTIES
    BUILD_RPATH "$ORIGIN"
)

target_link_libraries(QtDemo
    Qt6::Quick
    Qt6::Gui
    ui
    ${JNI_LIBRARIES}
)