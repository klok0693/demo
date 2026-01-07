#pragma once

#include <QObject>
#include <QItemSelectionModel>
#include <QStandardItemModel>
#include <QModelIndex>

#include "ui/layer/layers_panel_ui_bridge.h"

/* class ModelState;
class UIState;
class ShapeSelector; */

class QtLayersPanelController : public QObject {
    Q_OBJECT

    Q_PROPERTY(QAbstractItemModel* model READ model CONSTANT)
    Q_PROPERTY(QModelIndex rootIndex READ rootIndex CONSTANT)
    Q_PROPERTY(QItemSelectionModel* selectionModel READ selectionModel CONSTANT)

public:
    explicit QtLayersPanelController(
/*         ModelState* modelState,
        UIState* uiState,
        ShapeSelector* shapeSelector, */
        QObject* parent = nullptr
    );

    QAbstractItemModel* model();
    QModelIndex rootIndex() const;
    QItemSelectionModel* selectionModel();

    void layersUpdate(const LayersSnapshot* snapshot, const char* selectedId);
    void cleanUp();
    void unSelectAll();

    void setSelectShapeCallback(SelectShapeCallback callback);
    
public slots:
    void setSelectedId(const QString& value);

    void update();
    //void unSelectAll();
    //void onItemActivated(const QModelIndex& index);
    

/*     ModelState* m_modelState;
    UIState* m_uiState;
    ShapeSelector* m_shapeSelector; */

private:    
    QStandardItemModel m_model;
    QStandardItem* m_rootItem = nullptr;
    QItemSelectionModel m_selectionModel;

    SelectShapeCallback m_selectShapeCallback;
};
