import QtQuick
import QtQuick.Controls
import QtQuick.Layouts

ToolBar {
    id: toolBarRoot
    height: 48

    RowLayout {
        anchors.fill: parent
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
