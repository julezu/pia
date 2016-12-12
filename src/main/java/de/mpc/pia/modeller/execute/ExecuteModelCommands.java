package de.mpc.pia.modeller.execute;

import java.util.List;

import de.mpc.pia.modeller.PIAModeller;
import de.mpc.pia.modeller.execute.xmlparams.NODEType;


/**
 * Interface
 * @author julian
 *
 * @param <T>
 */
public interface ExecuteModelCommands<T> {

    /**
     * Executes the command for the given model and parameters.
     *
     * @param modeller
     * @param params
     * @return
     */
    boolean execute(T modeller, PIAModeller piaModeller, String[] params);


    /**
     * Gives a short description for the command
     * @return
     */
    String describe();


    /**
     * The returned list reflects the XML parameters for this execution.<br/>
     * If the returned list is null, the execution may not be executed by an XML
     * parameter file.<br/>
     *
     * Otherwise, one List<String> in the returned List stands for one needed
     * parameter. The first string is the parameter NAME and any following
     * Strings are used to restrict the valid values. If the list is empty, no
     * parameters are needed for the execution.
     *
     * @return
     */
    List<List<String>> neededXMLParameters();


    /**
     * Execute the command with the parameters given by the node on the model.
     *
     * @param node
     */
    void executeXMLParameters(NODEType node, T modeller, PIAModeller piaModeller);


    /**
     * Creates a node with the given params. The params should be in the same
     * order as specified by {@link #neededXMLParameters()}, except for the
     * first parameter, which indicates the NAME of the execute command, either
     * with or without PREFIX.
     *
     * @param params
     * @return null, if the parameters were invalid, else a node which can be
     * processed in a pipeline call
     */
    NODEType generateNode(String[] params);


    /**
     * The PREFIX for the commands in this enumeration.
     * @return
     */
    String prefix();
}