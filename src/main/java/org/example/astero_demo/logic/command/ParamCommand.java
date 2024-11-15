package org.example.astero_demo.logic.command;

import org.example.astero_demo.adapter.model.ParamInfo;

public abstract class ParamCommand extends Command {
    protected final ParamInfo[] paramInfos;

    protected ParamCommand(final ParamInfo... infos) {
        this.paramInfos = infos;
    }
}
