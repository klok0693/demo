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
            selectionModel: layersTreeController.selectionModel

            //clip: true

            delegate: TreeViewDelegate {
                id: delegate

                implicitHeight: 24
                text: model.display

                indicator: Item {
                    implicitWidth: depth > 0 ? 5 : 0
                    visible: false 
                }

                background: Rectangle {
                    anchors.fill: parent
                    visible: delegate.selected
                    color: delegate.palette.highlight
                    //color: delegate.selected ? delegate.palette.highlight : "transparent"
                }

                //leftMargin: 0
            }

            Connections {
                target: layersTree.model
                function onRowsInserted(parent, first, last) {
                    Qt.callLater(() => {
                        layersTree.expandRecursively(-1, -1)
                    })
                }
            }

            Connections {
                target: layersTreeController.selectionModel
                function onCurrentChanged(current, previous) {
                    layersTreeController.setSelectedId(current.data())
                }
            }

            //Component.onCompleted: layersTree.expandAll()
        }

    }
}