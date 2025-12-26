package org.example.demo.core.adapter.ui.state.model;

public interface MutableSelection extends Selection {

    void setSelectShape(Integer id);

    void setMultipleSelectedShapes(Integer... ids);
}
