cmake_minimum_required(VERSION 3.16)

project(QtQuickNoJS LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# ---- Qt ----

set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)

find_package(Qt6 REQUIRED COMPONENTS 
    Quick 
    QuickControls2
    Gui)


# ---- JAVA / JNI ----
find_package(JNI REQUIRED)

message(STATUS "JNI include dirs: ${JNI_INCLUDE_DIRS}")
message(STATUS "JNI libraries: ${JNI_LIBRARIES}")    

# ------ UI -------------

# STATIC/SHARED
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

# CXX_VISIBILITY_PRESET default
# VISIBILITY_INLINES_HIDDEN OFF
set_target_properties(ui PROPERTIES
    AUTOMOC ON
    AUTORCC ON
)

target_compile_definitions(ui PRIVATE UI_LIBRARY)

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

if(WIN32)
    set_target_properties(QtDemo PROPERTIES WIN32_EXECUTABLE OFF)

    # Find windeployqt
    get_target_property(_qt_bin_dir Qt6::Core LOCATION)
    get_filename_component(_qt_bin_dir "${_qt_bin_dir}" DIRECTORY)
    find_program(WINDEPLOYQT_EXECUTABLE windeployqt HINTS "${_qt_bin_dir}")

    # Run it after build
    add_custom_command(TARGET QtDemo POST_BUILD
        COMMAND "${WINDEPLOYQT_EXECUTABLE}"
                --debug
                --qmldir "${NATIVE_RESOURCES_DIR}/"
                \"$<TARGET_FILE:QtDemo>\"
        COMMENT "Deploying Qt dependencies..."
    )
endif()
