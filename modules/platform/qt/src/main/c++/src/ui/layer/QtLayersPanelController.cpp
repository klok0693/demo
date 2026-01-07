#include "ui/layer/QtLayersPanelController.h"
/* #include "ModelState.h"
#include "UIState.h"
#include "ShapeSelector.h"
#include "Shape.h" */

#include <iostream>

#include <QStandardItem>
#include <QString>


QtLayersPanelController::QtLayersPanelController(
    QObject* parent
) : QObject(parent),
    m_selectionModel(&m_model) 
{
    update();
}

QAbstractItemModel* QtLayersPanelController::model() {
    return &m_model;
}

QModelIndex QtLayersPanelController::rootIndex() const {
    return QModelIndex(); // invisible root
}

QItemSelectionModel* QtLayersPanelController::selectionModel() 
{ 
    return &m_selectionModel; 
}

void QtLayersPanelController::layersUpdate(
    const LayersSnapshot* snapshot, 
    const char* selectedId) 
{
    qDebug() << "c++ update ";

    QStandardItem* selectedItem = nullptr;

    for (int i = 0; i < snapshot->layerCount; i++) {
        const LayerEntry& layer = snapshot->layers[i];
        QStandardItem* layerItem = new QStandardItem(QString::number(layer.layerKey));
        
        for (int j = 0; j < layer.shapeCount; j++) {
            int id = layer.shapeIds[j];

            auto* shapeItem = new QStandardItem(QString::number(id));
            layerItem->appendRow(shapeItem);

            if (selectedId && std::stoi(selectedId) == id) {
                selectedItem = shapeItem;
            }
        }

        m_model.appendRow(layerItem);
    }

    if (selectedItem && (selectedItem->index().isValid())) {
        m_selectionModel.select(selectedItem->index(), QItemSelectionModel::ClearAndSelect | QItemSelectionModel::Rows);
    }

    qDebug() << "c++ update ends";
}

void QtLayersPanelController::update() {
/*     m_model.clear();
    m_model.setHorizontalHeaderLabels({ "Layers" });

    m_rootItem = m_model.invisibleRootItem();

    auto layers = groupShapesByPriority();

    for (auto it = layers.begin(); it != layers.end(); ++it) {
        auto* layerItem = new QStandardItem(QString::fromStdString(it.key()));
        //layerItem->setSelectable(false); // optional
        m_rootItem->appendRow(layerItem);

        for (const Shape& shape : it.value()) {
            auto* shapeItem = new QStandardItem(QString::number(shape.id()));
            shapeItem->setData(shape.id(), Qt::UserRole);
            layerItem->appendRow(shapeItem);

            if (uiState.isSelected(shape.id())) {
                // selection is handled via view or selectionModel
            }
        }
    } */

/*     std::cout << "update\n";

    m_model.clear();
    m_model.setHorizontalHeaderLabels({""});

    auto* root = m_model.invisibleRootItem();

    auto* layer0 = new QStandardItem("Layer 0");
    layer0->appendRow(new QStandardItem("1"));
    layer0->appendRow(new QStandardItem("2"));

    auto* layer1 = new QStandardItem("Layer 1");
    layer1->appendRow(new QStandardItem("3"));

    root->appendRow(layer0);
    root->appendRow(layer1); */

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
    qDebug() << "unselect all";
}

/* void QtLayersPanelController::onItemActivated(const QModelIndex& index) {
} */

void QtLayersPanelController::cleanUp() {
    qDebug() << "c++ leanup";
    m_model.clear();
    m_model.setHorizontalHeaderLabels({""});
    qDebug() << "c++ cleanup ends";
}

void QtLayersPanelController::setSelectShapeCallback(SelectShapeCallback callback
) {
    m_selectShapeCallback = callback;
}

void QtLayersPanelController::setSelectedId(const QString& value) 
{
    qDebug() << "c++ set selected id " << value;
    QByteArray utf8 = value.toUtf8();
    m_selectShapeCallback(utf8.constData());
    qDebug() << "c++ set selected id ends";
}
