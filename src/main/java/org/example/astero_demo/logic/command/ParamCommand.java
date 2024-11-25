package org.example.astero_demo.logic.command;

import org.example.astero_demo.model.metadata.ParamInfo;

import java.util.List;

/**
 * Command, having collection of the shape's parameters
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class ParamCommand extends Command {
    protected final List<ParamInfo> paramInfos;

    protected ParamCommand(final ParamInfo... infos) {
        this.paramInfos = List.of(infos);
    }
}
