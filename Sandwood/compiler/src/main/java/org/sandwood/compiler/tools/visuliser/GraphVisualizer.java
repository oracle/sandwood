/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tools.visuliser;

//import java.util.HashMap;
//import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
//import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.trees.Tree;

/**
 * This class visualises the DAGs and trees produced in the compiler. It is intended that it will one day be extended to
 * construct plate diagrams. However, J-Graph is now a dead project so the code that uses it is commented out to remove
 * the dependency. TODO using this as a template reimplement this functionality with a maintained library.
 * 
 * @author djgoodma
 *
 */
public class GraphVisualizer extends JFrame {

    private static final long serialVersionUID = -2707712944901661771L;
    /*
     * private static String[] palette = { "#79FF6B", "#FF9688", "#B2DFDB", "#FFFFFF", "#607D8B", "#757575", "#BDBDBD",
     * "#fbc02d", "#fff9c4", "#ffeb3b", "#ff5252" }; private mxGraph graph = new mxGraph();
     * 
     * private void buildTree(Tree<?> tree) {
     * 
     * graph.setAutoSizeCells(true); Object parent = graph.getDefaultParent();
     * 
     * graph.getModel().beginUpdate(); try { addSubtree(tree, null, parent, 0); } finally {
     * graph.getModel().endUpdate(); }
     * 
     * new mxHierarchicalLayout(graph, SwingConstants.WEST).execute(graph.getDefaultParent());
     * 
     * mxGraphComponent graphComponent = new mxGraphComponent(graph);
     * 
     * getContentPane().add(graphComponent);
     * 
     * }
     * 
     * private void addSubtree(Tree tree, Object parentRef, Object parentGraph, int scopeId) {
     * 
     * // Add tree Object vertexRef = graph.insertVertex(parentGraph, null, tree.getDescription(), 0, 0, 0, 0);
     * 
     * // To modify the colour of a vertex: String scopeColour = palette[scopeId % palette.length];
     * 
     * graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, scopeColour, new Object[] { vertexRef });
     * graph.updateCellSize(vertexRef);
     * 
     * // Add link up tree if (parentRef != null) graph.insertEdge(parentGraph, null, "", parentRef, vertexRef);
     * 
     * // Add in subtrees for (Tree t : tree.getChildren()) addSubtree(t, vertexRef, parentGraph,
     * tree.scopeId(scopeId));
     * 
     * }
     * 
     * private void buildGraph(Set<DataflowTask<?>> tasks, Set<Variable<?>> variables) {
     * 
     * int scopeId = 0; Map<Scope, Integer> scopeColourMap = new HashMap<>();
     * 
     * graph.setAutoSizeCells(true); Object parent = graph.getDefaultParent();
     * 
     * Map<DataflowTask<?>, Object> taskVertexLookup = new HashMap<>(); Map<Variable<?>, Object> variableVertexLookup =
     * new HashMap<>();
     * 
     * graph.getModel().beginUpdate(); try { for (Variable<?> var : variables) { Object varRef =
     * graph.insertVertex(parent, null, "  " + var.getName() + ":" + var.getId() + "\n  " + var.getType().getJavaType()
     * + "  ", 0, 0, 0, 0, "shape=ellipse;perimeter=ellipsePerimeter"); // To modify the colour of a vertex: String
     * scopeColour = null; if (scopeColourMap.containsKey(var.getEnclosingScope())) scopeColour =
     * palette[scopeColourMap.get(var.getEnclosingScope()) % palette.length]; else { scopeColour = palette[scopeId %
     * palette.length]; scopeColourMap.put(var.getEnclosingScope(), scopeId++); }
     * 
     * graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, scopeColour, new Object[] { varRef });
     * variableVertexLookup.put(var, varRef); graph.updateCellSize(varRef); }
     * 
     * for (DataflowTask<?> task : tasks) { // Add tasks Object taskRef = graph.insertVertex(parent, null,
     * task.getName() + "\n" + task.getSandwoodString(false), 0, 0, 0, 0);
     * 
     * // To modify the colour of a vertex: String scopeColour = null; if (scopeColourMap.containsKey(task.scope()))
     * scopeColour = palette[scopeColourMap.get(task.scope()) % palette.length]; else { scopeColour = palette[scopeId %
     * palette.length]; scopeColourMap.put(task.scope(), scopeId++); } graph.setCellStyles(mxConstants.STYLE_FILLCOLOR,
     * scopeColour, new Object[] { taskRef }); taskVertexLookup.put(task, taskRef); graph.updateCellSize(taskRef);
     * 
     * // Add links to inputs. for (Variable<?> var : task.getInputs()) { graph.insertEdge(parent, null, "",
     * variableVertexLookup.get(var), taskVertexLookup.get(task)); }
     * 
     * // Add lints to outputs. if(task.outputSet()) { Variable<?> var = task.getOutput(); if
     * (variableVertexLookup.containsKey(var)) graph.insertEdge(parent, null, "", taskVertexLookup.get(task),
     * variableVertexLookup.get(var)); } } } finally { graph.getModel().endUpdate(); }
     * 
     * new mxHierarchicalLayout(graph, SwingConstants.WEST).execute(graph.getDefaultParent());
     * 
     * mxGraphComponent graphComponent = new mxGraphComponent(graph);
     * 
     * getContentPane().add(graphComponent);
     * 
     * }
     */

    public static GraphVisualizer visualize(Set<DataflowTask<?>> tasks, Set<Variable<?>> variables) {
        GraphVisualizer frame = new GraphVisualizer();
        // frame.buildGraph(tasks, variables);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);

        return frame;
    }

    public static GraphVisualizer visualize(Tree tree) {
        GraphVisualizer frame = new GraphVisualizer();
        // frame.buildTree(tree);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);

        return frame;
    }
}
