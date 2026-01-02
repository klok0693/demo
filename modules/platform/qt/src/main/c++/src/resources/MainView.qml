import QtQuick 2.15
import QtQuick.Controls 2.15
import QtQuick.Layouts

ApplicationWindow {
    width: 1200
    height: 720
    visible: true
    title: "Qt Quick (no JS)"

    header: AppToolBar { }

    RowLayout {
        anchors.fill: parent
        //anchors.topMargin: header.height

        AppLayersPanel {
            Layout.preferredWidth: 240
            Layout.fillHeight: true
        }

        // CENTER (canvas / main area)
/*         Item {
            Layout.fillWidth: true
            Layout.fillHeight: true
        } */
        QtCanvas {
            Layout.fillWidth: true
            Layout.fillHeight: true
        }

        // EAST (PropertyPanel)
        AppPropertiesPanel {
            Layout.preferredWidth: 240
            Layout.fillHeight: true
        }
    }

/*     Column {
        anchors.centerIn: parent
        spacing: 10

        Button {
            text: "Show label"
            onClicked: uiState.onButtonClicked()
        }

        Label {
            text: "Hello from Qt Quick!"
            visible: uiState.labelVisible
        }
    } */
}
