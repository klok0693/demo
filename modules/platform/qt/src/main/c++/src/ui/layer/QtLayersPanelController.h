#pragma once

#include <QObject>
#include <QItemSelectionModel>
#include <QStandardItemModel>
#include <QModelIndex>

#include "ui/layer/layers_panel_ui_bridge.h"
#include "ui/ui_export.h"

class UI_EXPORT QtLayersPanelController : public QObject {
    Q_OBJECT

    Q_PROPERTY(QAbstractItemModel* model READ model CONSTANT)
    Q_PROPERTY(QModelIndex rootIndex READ rootIndex CONSTANT)
    Q_PROPERTY(QItemSelectionModel* selectionModel READ selectionModel CONSTANT)

public:
    explicit QtLayersPanelController(
        QObject* parent = nullptr
    );

    QAbstractItemModel* model();
    QModelIndex rootIndex() const;
    QItemSelectionModel* selectionModel();

    void layersUpdate(const LayersSnapshot* snapshot);
    void cleanUp();
    void unSelectAll();

    void setSelectedIds(const char* const* ids, jsize_t count);
    void setOnShapeSelectCallback(SelectShapeCallback callback);
    
public slots:
    void onShapeSelect(const QString& value);

    void update();    

private:    
    QStandardItemModel m_model;
    QStandardItem* m_rootItem = nullptr;
    QItemSelectionModel m_selectionModel;

    SelectShapeCallback m_selectShapeCallback;

    void setSelectedId(const char* selectedId);
};
