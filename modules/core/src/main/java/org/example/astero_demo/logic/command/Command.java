package org.example.astero_demo.logic.command;

/**
 * Realization of Command template
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class Command {

    public abstract void doCommand();

    public abstract void undoCommand();
}
