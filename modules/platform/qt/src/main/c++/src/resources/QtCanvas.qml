import QtQuick
import App.Canvas 1.0

Item {
    id: canvasRoot
    focus: true

    QtCanvasUI {
        id: canvas
        width: 720
        height: 600
        anchors.centerIn: parent

        MouseArea {
            anchors.fill: parent
            onPressed: canvasController.handleMousePressed(mouse.position)
            onPositionChanged: canvasController.handleMouseDragged(mouse.position)
            onReleased: canvasController.handleMouseReleased(mouse.position)
        }
    }
}
