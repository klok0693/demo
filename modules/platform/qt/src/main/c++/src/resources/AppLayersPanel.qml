import QtQuick
import QtQuick.Controls
import QtQuick.Layouts

Pane /* Rectangle */ {
    id: layersRoot
    width: 240
    //color: "blue"

    readonly property int margin: 10

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

    contentItem: ColumnLayout {
        anchors.fill: parent
        anchors.margins: margin * 2
        spacing: 10

         Rectangle {
            color: "white"
            anchors.fill: parent

        TreeView {
            id: layersTree

            anchors.fill: parent
            Layout.fillWidth: true
            Layout.fillHeight: true
            Layout.margins: layersRoot.margin

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
                    visible: delegate.highlighted && depth > 0
                    color: delegate.palette.highlight
                }

                onClicked: {
                    let mi = layersTree.index(row, column)
                    layersTree.selectionModel.setCurrentIndex(mi, layersTree.selectionModel.ClearAndSelect)
                    layersTreeController.onShapeSelect(mi.data())
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
        }
        }
    }
}