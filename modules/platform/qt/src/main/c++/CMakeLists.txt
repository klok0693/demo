cmake_minimum_required(VERSION 3.16)

project(QtQuickNoJS LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)

#set(CMAKE_PREFIX_PATH "~/6.10.0/gcc_64")

find_package(Qt6 REQUIRED COMPONENTS 
    Quick 
    QuickControls2
    Gui)


add_library(ui)

target_sources(ui PRIVATE
    src/ui/toolbar/ToolBarController.cpp
    src/ui/property/PropertiesPanelController.cpp
    src/ui/layer/QtLayersPanelController.cpp
    src/ui/canvas/QtCanvasController.cpp
    src/ui/element/QtCanvasItem.cpp
)

target_include_directories(ui PUBLIC src/ui)

target_link_libraries(ui
    PUBLIC Qt6::Quick
    PUBLIC Qt6::Core
    Qt6::Gui
)


add_executable(QtQuickNoJS
    src/app/main.cpp
    src/resources/resources.qrc
)

target_link_libraries(QtQuickNoJS
    Qt6::Quick
    Qt6::Gui
    ui
)