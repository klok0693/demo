import QtQuick
import QtQuick.Controls
import QtQuick.Layouts

Rectangle {
    id: layersRoot
    width: 240
    //color: "blue"

    ColumnLayout {
        anchors.fill: parent

        TreeView {
            id: layersTree
            Layout.fillWidth: true
            Layout.fillHeight: true
            Layout.margins: 15

            model: layersTreeController.model
            //selectionMode: TableView.MultiSelection
            rootIndex: layersTreeController.rootIndex
            clip: true

            delegate: Item {
                implicitHeight: 24
                Row {
                    spacing: 6
                    Text {
                        text: model.display
                    }
                }
            }

            /* onActivated: (index) => layersTreeController.onItemActivated(index) */
        }
    }
}