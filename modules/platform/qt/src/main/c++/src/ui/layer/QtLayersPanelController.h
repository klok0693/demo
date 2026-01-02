#pragma once

#include <QObject>
#include <QStandardItemModel>
#include <QModelIndex>

/* class ModelState;
class UIState;
class ShapeSelector; */

class QtLayersPanelController : public QObject {
    Q_OBJECT

    Q_PROPERTY(QAbstractItemModel* model READ model CONSTANT)
    Q_PROPERTY(QModelIndex rootIndex READ rootIndex CONSTANT)

public:
    explicit QtLayersPanelController(
/*         ModelState* modelState,
        UIState* uiState,
        ShapeSelector* shapeSelector, */
        QObject* parent = nullptr
    );

    QAbstractItemModel* model();
    QModelIndex rootIndex() const;

public slots:
    void update();
    void unSelectAll();
    void onItemActivated(const QModelIndex& index);

private:
    void cleanUp();

/*     ModelState* m_modelState;
    UIState* m_uiState;
    ShapeSelector* m_shapeSelector; */

    QStandardItemModel m_model;
    QStandardItem* m_rootItem = nullptr;
};
