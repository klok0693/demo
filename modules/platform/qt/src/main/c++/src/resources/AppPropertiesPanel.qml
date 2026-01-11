import QtQuick
import QtQuick.Controls
import QtQuick.Layouts

Pane /* Rectangle */ {
    id: propertyRoot
    width: 240
    enabled: propertiesPanelController.enabled

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

        GridLayout {
            id: grid
            columns: 4

            rowSpacing: 10
            columnSpacing: 5

            anchors.top: parent.top
            Layout.fillWidth: true

            readonly property int textFieldMinWidth: 10
            readonly property int textFieldMaxWidth: 50

            // Row 0 — X / Y
            Label {
                text: "x:"
                horizontalAlignment: Text.AlignRight
                verticalAlignment: Text.AlignVCenter

                Layout.fillWidth: true
                Layout.horizontalStretchFactor: 3
            }
            TextField {
                enabled: propertiesPanelController.x && propertiesPanelController.x.length > 0
                text: propertiesPanelController.x
                onAccepted: propertiesPanelController.updateX(text)

                Layout.fillWidth: true
                Layout.horizontalStretchFactor: 2
                Layout.minimumWidth: grid.textFieldMinWidth
                Layout.maximumWidth: grid.textFieldMaxWidth

                onEditingFinished: {
                    focus = false
                }
            }
            Label {
                text: "y:"
                horizontalAlignment: Text.AlignRight
                verticalAlignment: Text.AlignVCenter

                Layout.fillWidth: true
                Layout.horizontalStretchFactor: 3
            }
            TextField {
                enabled: propertiesPanelController.y && propertiesPanelController.y.length > 0
                text: propertiesPanelController.y
                onAccepted: propertiesPanelController.updateY(text)

                Layout.fillWidth: true
                Layout.horizontalStretchFactor: 2
                Layout.minimumWidth: grid.textFieldMinWidth
                Layout.maximumWidth: grid.textFieldMaxWidth
                
                onEditingFinished: {
                    focus = false
                }
            }

            // Row 1 — Width / Height
            Label {
                text: "width:"
                horizontalAlignment: Text.AlignRight
                verticalAlignment: Text.AlignVCenter

                Layout.fillWidth: true
                Layout.horizontalStretchFactor: 3
            }
            TextField {
                enabled: propertiesPanelController.width && propertiesPanelController.width.length > 0
                text: propertiesPanelController.width
                onAccepted: propertiesPanelController.updateWidth(text)

                Layout.fillWidth: true
                Layout.horizontalStretchFactor: 2
                Layout.minimumWidth: grid.textFieldMinWidth
                Layout.maximumWidth: grid.textFieldMaxWidth
                
                onEditingFinished: {
                    focus = false
                }
            }
            Label {
                text: "height:"
                horizontalAlignment: Text.AlignRight
                verticalAlignment: Text.AlignVCenter

                Layout.fillWidth: true
                Layout.horizontalStretchFactor: 3
            }
            TextField {
                enabled: propertiesPanelController.height && propertiesPanelController.height.length > 0
                text: propertiesPanelController.height
                onAccepted: propertiesPanelController.updateHeight(text)

                Layout.fillWidth: true
                Layout.horizontalStretchFactor: 2
                Layout.minimumWidth: grid.textFieldMinWidth
                Layout.maximumWidth: grid.textFieldMaxWidth
                
                onEditingFinished: {
                    focus = false
                }
            }

            // Row 2 — Layer
            Label {
                text: "layer:"
                horizontalAlignment: Text.AlignRight
                verticalAlignment: Text.AlignVCenter

                Layout.fillWidth: true
                Layout.horizontalStretchFactor: 3
            }
            TextField {
                enabled: propertiesPanelController.layer && propertiesPanelController.layer.length > 0
                text: propertiesPanelController.layer
                onAccepted: propertiesPanelController.updateLayer(text)

                Layout.fillWidth: true
                Layout.horizontalStretchFactor: 2
                Layout.minimumWidth: grid.textFieldMinWidth
                Layout.maximumWidth: grid.textFieldMaxWidth
                
                onEditingFinished: {
                    focus = false
                }
            }

            Item { }  // spacer
            Item { }

            // Row 3 — Color
/*             Label {
                text: "color:"
                Layout.alignment: Qt.AlignRight
                Layout.fillWidth: true
                Layout.horizontalStretchFactor: 3
            } */
/*             ColorDialog {
                id: colorDialog
                onAccepted: propertiesPanelController.updateColor(color)
            }
            Button {
                text: "Pick"
                enabled: propertiesPanelController.enabled
                onClicked: colorDialog.open()
            } */
        }
    }
}
