#include "ui/root/RootView.h"

#include <QGuiApplication>
#include <QCursor>

RootView::RootView(QObject* parent)
    : QObject(parent)
{
}

void RootView::setCursor(const QString& cursor) {
    if (cursor == "CROSSHAIR") {
        QGuiApplication::setOverrideCursor(QCursor(Qt::CrossCursor));
    } else {
        QGuiApplication::restoreOverrideCursor();
    }
}