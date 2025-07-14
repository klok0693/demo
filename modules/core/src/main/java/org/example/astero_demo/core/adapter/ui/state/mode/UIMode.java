package org.example.astero_demo.core.adapter.ui.state.mode;

public abstract class UIMode<T extends ModeSwitchable> {
    public static final UIMode<InsertModeSwitchable> INSERT = new UIMode<>() {
        @Override
        void visit(final InsertModeSwitchable switchable) {
            switchable.switchToInsertMode();
        }
    };
    public static final UIMode<SingleSelectionModeSwitchable> SINGLE_SELECTION = new UIMode<>() {
        @Override
        void visit(final SingleSelectionModeSwitchable switchable) {
            switchable.switchToSingleSelectionMode();
        }
    };
    public static final UIMode<MultipleSelectionModeSwitchable> MULTIPLE_SELECTION = new UIMode<>() {
        @Override
        void visit(final MultipleSelectionModeSwitchable switchable) {
            switchable.switchToMultipleSelectionMode();
        }
    };

    private UIMode() {};

    abstract void visit(T switchable);
}
