import QtQuick
import QtQuick.Controls
import QtQuick.Layouts

Rectangle {
    id: propertyRoot
    width: 240
    enabled: propertiesPanelController.enabled
    //anchors.right: parent.right;
    //anchors.top: parent.top;
    //color: "green"

    ColumnLayout {
        anchors.fill: parent
        spacing: 10

        GridLayout {
            id: grid
            columns: 4
            rowSpacing: 10
            columnSpacing: 5
            anchors.top: parent.top
            Layout.fillWidth: true

            // Row 0 — X / Y
            Label {
                text: "x:"
                Layout.alignment: Qt.AlignRight
            }
            TextField {
                enabled: propertiesPanelController.x && propertiesPanelController.x.length > 0
                text: propertiesPanelController.x
                onAccepted: propertiesPanelController.updateX(text)
            }
            Label {
                text: "y:"
                Layout.alignment: Qt.AlignRight
            }
            TextField {
                enabled: propertiesPanelController.y && propertiesPanelController.y.length > 0
                text: propertiesPanelController.y
                onAccepted: propertiesPanelController.updateY(text)
            }

            // Row 1 — Width / Height
            Label {
                text: "width:"
                Layout.alignment: Qt.AlignRight
            }
            TextField {
                enabled: propertiesPanelController.width && propertiesPanelController.width.length > 0
                text: propertiesPanelController.width
                onAccepted: propertiesPanelController.updateWidth(text)
            }
            Label {
                text: "height:"
                Layout.alignment: Qt.AlignRight
            }
            TextField {
                enabled: propertiesPanelController.height && propertiesPanelController.height.length > 0
                text: propertiesPanelController.height
                onAccepted: propertiesPanelController.updateHeight(text)
            }

            // Row 2 — Layer
            Label {
                text: "layer:"
                Layout.alignment: Qt.AlignRight
            }
            TextField {
                enabled: propertiesPanelController.propertiesPanelController.layer && propertiesPanelController.layer.length > 0
                text: propertiesPanelController.layer
                onAccepted: propertiesPanelController.updateLayer(text)
            }

            Item { }  // spacer
            Item { }

            // Row 3 — Color
            Label {
                text: "color:"
                Layout.alignment: Qt.AlignRight
            }
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
