import QtQuick
import QtQuick.Controls
import QtQuick.Layouts

Rectangle {
    id: propertyRoot
    width: 240
    //anchors.right: parent.right;
    //anchors.top: parent.top;
    color: "green"

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
                enabled: propertiesPanelController.enabled
                text: propertiesPanelController.x
                onEditingFinished: propertiesPanelController.updateX(text)
            }
            Label {
                text: "y:"
                Layout.alignment: Qt.AlignRight
            }
            TextField {
                enabled: propertiesPanelController.enabled
                text: propertiesPanelController.y
                onEditingFinished: propertiesPanelController.updateY(text)
            }

            // Row 1 — Width / Height
            Label {
                text: "width:"
                Layout.alignment: Qt.AlignRight
            }
            TextField {
                enabled: propertiesPanelController.enabled
                text: propertiesPanelController.width
                onEditingFinished: propertiesPanelController.updateWidth(text)
            }
            Label {
                text: "height:"
                Layout.alignment: Qt.AlignRight
            }
            TextField {
                enabled: propertiesPanelController.enabled
                text: propertiesPanelController.height
                onEditingFinished: propertiesPanelController.updateHeight(text)
            }

            // Row 2 — Layer
            Label {
                text: "layer:"
                Layout.alignment: Qt.AlignRight
            }
            TextField {
                enabled: propertiesPanelController.enabled
                text: propertiesPanelController.layer
                onEditingFinished: propertiesPanelController.updateLayer(text)
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
