import QtQuick
import App.Canvas 1.0

Item {
    id: canvasRoot
    focus: true

    QtCanvasUI {
        id: canvas
        objectName: "canvasItem"
        width: 720
        height: 630
        anchors.centerIn: parent
        Keys.forwardTo: [keyCatcher]
        focus:true

        MouseArea {
            anchors.fill: parent
            onPressed: function(mouse) {
                var isCtrl  = mouse.modifiers & Qt.ControlModifier
                var isShift = mouse.modifiers & Qt.ShiftModifier
                canvasController.handleMousePressed(Qt.point(mouse.x, mouse.y), isCtrl, isShift)
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
