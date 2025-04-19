package org.example.astero_demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.model.metadata.ParamInfo;
import org.example.astero_demo.model.metadata.ShapeParam;
import org.example.astero_demo.logic.command.Command;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;
import org.example.astero_demo.logic.event.ui.*;

import java.util.List;

import static org.example.astero_demo.model.entity.ShapeType.valueOf;
import static org.example.astero_demo.util.ParamUtils.getParamInfo;

/**
 * An abstract class representing a controller that delegates LogicEvents to the CommandProcessor
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Slf4j
public abstract class AbstractController {

}
