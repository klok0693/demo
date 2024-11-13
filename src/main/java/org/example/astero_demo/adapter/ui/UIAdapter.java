package org.example.astero_demo.adapter.ui;

import org.example.astero_demo.controller.ViewController;

public abstract class UIAdapter {
    protected final ViewController controller;

    public UIAdapter(ViewController controller) {
        this.controller = controller;
    }

    public abstract void update();
}
