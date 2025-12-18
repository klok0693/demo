package org.example.astero_demo.swing.initialization.di;

import com.google.inject.*;
import org.example.astero_demo.core.context.state.ModelState;
import org.example.astero_demo.core.adapter.ui.layerspanel.LayersAdapter;
import org.example.astero_demo.core.adapter.ui.state.UIState;
import org.example.astero_demo.core.port.ui.RootView;
import org.example.astero_demo.core.port.ui.ToolBarPanelView;
import org.example.astero_demo.swing.port.ui.SwingRootUI;
import org.example.astero_demo.swing.port.ui.element.SwingCanvas;
import org.example.astero_demo.swing.port.ui.element.SwingLayersTree;
import org.example.astero_demo.swing.port.ui.toolbar.SwingToolBarUI;
import org.example.astero_demo.swing.port.ui.toolbar.ToolBarUI;

/**
 * DI config for UI elements
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
class SwingUIElementModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ToolBarUI.class).to(SwingToolBarUI.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public SwingRootUI provideRootUI(
            final SwingToolBarUI toolBarUI,
            final RootView rootView) {
        return new SwingRootUI(toolBarUI, rootView);
    }

    @Inject
    @Provides
    @Singleton
    public SwingToolBarUI provideToolBarUI(final ToolBarPanelView rootView) {
        return new SwingToolBarUI(rootView);
    }



/*    @Inject
    @Provides
    @Singleton
    public LayersTreeBuilder provideLayersTreeBuilder(final SwingLayersTree layersTree) {
        return new LayersTreeBuilder(layersTree);
    }*/

    @Inject
    @Provides
    @Singleton
    public SwingLayersTree provideLayersTree(
            final ModelState modelState,
            final UIState uiState,
            final LayersAdapter shapeSelector) {
        return new SwingLayersTree(modelState, uiState, shapeSelector);
    }

    @Inject
    @Provides
    @Singleton
    public SwingCanvas provideFxCanvas() {
        return new SwingCanvas();
    }
}
