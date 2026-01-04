import QtQuick
import App.Canvas 1.0

Item {
    id: canvasRoot
    focus: true

    QtCanvasUI {
        id: canvas
        objectName: "canvasItem"
        width: 720
        height: 600
        anchors.centerIn: parent

        MouseArea {
            anchors.fill: parent
            onPressed: function(mouse) {
                canvasController.handleMousePressed(Qt.point(mouse.x, mouse.y))
            }
            onPositionChanged: function(mouse) {
                canvasController.handleMouseDragged(Qt.point(mouse.x, mouse.y))
            }
            onReleased: function(mouse) {
                canvasController.handleMouseReleased(Qt.point(mouse.x, mouse.y))
            }
        }
    }
}
