import QtQuick 2.15
import QtQuick.Controls 2.15
import QtQuick.Layouts

ApplicationWindow {
    width: 1200
    height: 720
    visible: true
    title: "Qt Demo"

    header: AppToolBar {
        Keys.forwardTo: [keyCatcher]
    }

    FocusScope {
       anchors.fill: parent
       focus: true // Ensure the scope itself is focusable
    
       Item {
           id: keyCatcher
           anchors.fill: parent
           focus: true 
           
           Keys.onPressed: (event) => {
               keyboardView.onKeyEvent(event.key, event.modifiers)
           }

            RowLayout {
                anchors.fill: parent
                //anchors.topMargin: header.height

                AppLayersPanel {
                    Layout.preferredWidth: 240
                    Layout.fillHeight: true

                    Keys.forwardTo: [keyCatcher]
                }

                // CENTER (canvas / main area)
                QtCanvas {
                    Layout.fillWidth: true
                    Layout.fillHeight: true

                    Keys.forwardTo: [keyCatcher]
                }

                // EAST (PropertyPanel)
                AppPropertiesPanel {
                    Layout.preferredWidth: 240
                    Layout.fillHeight: true

                    Keys.forwardTo: [keyCatcher]
                }
            }
       }
    }
}
