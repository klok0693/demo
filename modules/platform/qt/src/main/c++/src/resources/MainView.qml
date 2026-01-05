import QtQuick 2.15
import QtQuick.Controls 2.15
import QtQuick.Layouts

ApplicationWindow {
    width: 1200
    height: 720
    visible: true
    title: "Qt Demo"

    header: AppToolBar { }

    RowLayout {
        anchors.fill: parent
        //anchors.topMargin: header.height

        AppLayersPanel {
            Layout.preferredWidth: 240
            Layout.fillHeight: true
        }

        // CENTER (canvas / main area)
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
}
