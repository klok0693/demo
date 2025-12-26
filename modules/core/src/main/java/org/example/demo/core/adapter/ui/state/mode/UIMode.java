package org.example.demo.core.adapter.ui.state.mode;

/**
 * Some GUI elements can be change there state - enable/disabled,<p>
 * visible or not, etc. To prevent uncontrolled behavior of GUI, state of<p>
 * all modifiable elements is linked with MODEs. Although small changed are<p>
 * possible under one mode, huge changes mean changing the mode. All possible<p>
 * switches between modes are defined, which give control under the GUI state<p>
 * and behavior. Realization of the 'State Machine' pattern. All modes are listed<p>
 * in this class
 * <p>
 * To help every element choose the correct mode without additional variables,<p>
 * 'Visitor' pattern is also used
 * <p>
 * @author Pilip Yurchanka
 * @since v1.1
 */
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

    /**
     * @param switchable - element, that must update it's state according to the current mode
     */
    abstract void visit(T switchable);
}
