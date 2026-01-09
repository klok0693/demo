#include "keyboard/QtKeyboardView.h"

#include <QKeySequence>
#include <QDebug>

QtKeyboardView::QtKeyboardView(QObject* parent)
    : QObject(parent)
{}

static QString keyToString(const QKeyEvent* event)
{
    QKeySequence seq(event->key() | event->modifiers());
    return seq.toString(QKeySequence::PortableText);
}

static bool shouldForwardKey(const int key)
{
    if (key == Qt::Key_Control ||
        key == Qt::Key_Shift ||
        key == Qt::Key_Alt ||
        key == Qt::Key_Meta)
        return false;

    return true;
}

Q_INVOKABLE void QtKeyboardView::onKeyEvent(int key, int modifiers) 
{
    if (!shouldForwardKey(key)) {
        return;
    }

    Qt::KeyboardModifiers mods = static_cast<Qt::KeyboardModifiers>(modifiers);

    bool isCtrl  = mods.testFlag(Qt::ControlModifier);
    bool isShift = mods.testFlag(Qt::ShiftModifier);
    bool isAlt   = mods.testFlag(Qt::AltModifier);
    bool isMeta  = mods.testFlag(Qt::MetaModifier);

    QKeySequence seq(key /* | modifiers */);
    QString ketStr = seq.toString(QKeySequence::PortableText);
    QByteArray utf8 = ketStr.toUtf8();

    m_keyCallback(utf8.constData(), isCtrl, isShift);
}

void QtKeyboardView::setKeyCallback(KeyCallback callback) {
    m_keyCallback = callback;
}