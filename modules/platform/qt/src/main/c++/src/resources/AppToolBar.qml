import QtQuick
import QtQuick.Controls
import QtQuick.Layouts

ToolBar {
    id: toolBarRoot
    //height: 48
    implicitHeight: 48 + (padding * 2) 

    readonly property int margin: 15

    padding: margin

    topInset: margin
    bottomInset: margin
    leftInset: margin
    rightInset:margin

    background: Rectangle {
        color: "transparent"
        border.width: 1
        border.color: "black"
        radius: 3
    }

    contentItem: RowLayout {
        anchors.fill: parent
        anchors.left: parent.left
        anchors.leftMargin: margin * 2;
        anchors.right: parent.right
        anchors.rightMargin: margin * 2;
        
        spacing: 12

        // LEFT GROUP
        RowLayout {
            spacing: 6

            Button {
                text: "Rect"
                checkable: true
                checked: toolBarController.insertRectBtnSelected
                onClicked: toolBarController.onInsertRectAction()
            }

            Button {
                text: "Cycle"
                checkable: true
                checked: toolBarController.insertCycleBtnSelected
                onClicked: toolBarController.onInsertCycleAction()
            }
        }

        // SPACER (HBox.hgrow = ALWAYS)
        Item {
            Layout.fillWidth: true
        }

        // RIGHT GROUP
        RowLayout {
            spacing: 6

            Button {
                text: "Undo"
                onClicked: toolBarController.onUndoAction()
            }

            Button {
                text: "Delete"
                //checkable: true
                //checked: toolBarController.deleteEnabled()
                enabled: toolBarController.deleteEnabled
                onClicked: toolBarController.onDeleteAction()
            }
        }
    }
}
