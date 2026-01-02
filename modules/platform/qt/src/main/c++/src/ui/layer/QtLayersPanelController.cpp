#include "QtLayersPanelController.h"
/* #include "ModelState.h"
#include "UIState.h"
#include "ShapeSelector.h"
#include "Shape.h" */

#include <QStandardItem>
#include <QString>

#include <iostream>

QtLayersPanelController::QtLayersPanelController(
/*     ModelState* modelState,
    UIState* uiState,
    ShapeSelector* shapeSelector, */
    QObject* parent
) : QObject(parent)
/*     m_modelState(modelState),
    m_uiState(uiState),
    m_shapeSelector(shapeSelector) */
{
    //m_model.setHorizontalHeaderLabels({""});
    update();
}

QAbstractItemModel* QtLayersPanelController::model() {
    return &m_model;
}

QModelIndex QtLayersPanelController::rootIndex() const {
    return QModelIndex(); // invisible root
}

void QtLayersPanelController::update() {
    std::cout << "update\n";

    m_model.clear();
    m_model.setHorizontalHeaderLabels({""});

    auto* root = m_model.invisibleRootItem();

    auto* layer0 = new QStandardItem("Layer 0");
    layer0->appendRow(new QStandardItem("1"));
    layer0->appendRow(new QStandardItem("2"));

    auto* layer1 = new QStandardItem("Layer 1");
    layer1->appendRow(new QStandardItem("3"));

    root->appendRow(layer0);
    root->appendRow(layer1);
/*     cleanUp();

    auto shapes = m_modelState->getShapes(); // assume iterable
    QMap<int, QList<Shape>> layers;

    for (const auto& shape : shapes) {
        layers[shape.getPriority()].append(shape);
    }

    if (layers.isEmpty())
        return;

    m_rootItem = m_model.invisibleRootItem();

    for (auto it = layers.begin(); it != layers.end(); ++it) {
        auto* layerItem = new QStandardItem(QString::number(it.key()));
        layerItem->setSelectable(false);
        m_rootItem->appendRow(layerItem);

        for (const auto& shape : it.value()) {
            auto* shapeItem = new QStandardItem(QString::number(shape.getId()));
            layerItem->appendRow(shapeItem);

            if (m_uiState->hasSelectedId() &&
                m_uiState->isIdSelected(shape.getId())) {
                // selection applied from QML side
            }
        }
    } */
}

void QtLayersPanelController::unSelectAll() {
    // selection cleared via TreeView API if needed
}

void QtLayersPanelController::onItemActivated(const QModelIndex& index) {
/*     if (!index.isValid())
        return;

    const auto id = m_model.data(index).toString();
    m_shapeSelector->selectShape(id.toInt()); */
}

void QtLayersPanelController::cleanUp() {
/*     m_model.clear();
    m_model.setHorizontalHeaderLabels({""}); */
}
