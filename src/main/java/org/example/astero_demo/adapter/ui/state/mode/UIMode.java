package org.example.astero_demo.adapter.ui.state.mode;

public class UIMode<T extends ModeSwitchable> {
    public static final UIMode<InsertModeSwitchable> INSERT = new UIMode<>();
    public static final UIMode<SingleSelectionModeSwitchable> SINGLE_SELECTION = new UIMode<>();
    public static final UIMode<MultipleSelectionModeSwitchable> MULTIPLE_SELECTION = new UIMode<>();

    private UIMode() {};

    public void visit(final T switchable) {
        switchable.switchMode();
    };
}
