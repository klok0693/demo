#include "ui/layer/QtLayersPanelController.h"

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

QAbstractItemModel* QtLayersPanelController::model() 
{
    return &m_model;
}

QModelIndex QtLayersPanelController::rootIndex() const 
{
    return QModelIndex(); // invisible root
}

QItemSelectionModel* QtLayersPanelController::selectionModel() 
{ 
    return &m_selectionModel; 
}

void QtLayersPanelController::layersUpdate(const LayersSnapshot* snapshot) 
{
    for (int i = 0; i < snapshot->layerCount; i++) {
        const LayerEntry& layer = snapshot->layers[i];
        QStandardItem* layerItem = new QStandardItem(QString::number(layer.layerKey));
        
        for (int j = 0; j < layer.shapeCount; j++) {
            int id = layer.shapeIds[j];

            auto* shapeItem = new QStandardItem(QString::number(id));
            layerItem->appendRow(shapeItem);
        }

        m_model.appendRow(layerItem);
    }
}

void QtLayersPanelController::update() 
{
    //qDebug() << "I hope not";
}

void QtLayersPanelController::unSelectAll() 
{
    m_selectionModel.clearSelection();
    m_selectionModel.setCurrentIndex(QModelIndex(), QItemSelectionModel::NoUpdate);
}

void QtLayersPanelController::cleanUp() 
{
    m_model.clear();
    m_model.setHorizontalHeaderLabels({""});
}

void QtLayersPanelController::setSelectedId(const char* selectedId) {
    const QString text = QString::fromUtf8(selectedId);
    const QList<QStandardItem*> items = m_model.findItems(text, Qt::MatchExactly | Qt::MatchRecursive);
    if (items.isEmpty()) {
        return;
    }

    QStandardItem *item = items.first();
    QModelIndex index = item->index();

    m_selectionModel.select(index, QItemSelectionModel::Select | QItemSelectionModel::Rows);
    m_selectionModel.setCurrentIndex(index, QItemSelectionModel::NoUpdate/* SelectCurrent */);
}

void QtLayersPanelController::setSelectedIds(const char* const* ids, jsize_t count) 
{
    unSelectAll();

    for (size_t i = 0; i < count; ++i) {
        const char* id = ids[i];
        setSelectedId(id);
    }
}

void QtLayersPanelController::setOnShapeSelectCallback(SelectShapeCallback callback) 
{
    m_selectShapeCallback = callback;
}

void QtLayersPanelController::onShapeSelect(const QString& value) 
{
    QByteArray utf8 = value.toUtf8();
    m_selectShapeCallback(utf8.constData());
}
