package com.swing.gui;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

import static com.swing.commons.Constants.*;

class ServerInfo {
    private String name;
    private int id;
    private boolean checked;

    public ServerInfo(String name, int id, boolean checked) {
        this.name = name;
        this.id = id;
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
public class MessagePanel extends JPanel {

    private JTree serverTree;
    private ServerTreeCellRenderer treeCellRenderer;
    private ServerTreeCellEditor treeCellEditor;

    public MessagePanel() {
        treeCellRenderer = new ServerTreeCellRenderer();
        treeCellEditor = new ServerTreeCellEditor();
        serverTree = new JTree(createTree());
        serverTree.setCellRenderer(treeCellRenderer);
        serverTree.setCellEditor(treeCellEditor);
        serverTree.setEditable(true);
        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        treeCellEditor.addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                ServerInfo serverInfo = (ServerInfo) treeCellEditor.getCellEditorValue();
            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });
        setLayout(new BorderLayout());
        add(new JScrollPane(serverTree), BorderLayout.CENTER);
    }

    private DefaultMutableTreeNode createTree() {

        DefaultMutableTreeNode top = new DefaultMutableTreeNode(TOP_TREE_NODE_NAME);

        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode(USA_BRANCH_TREE_NODE_NAME);

        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(
                new ServerInfo(NEW_YORK_SERVER_TREE_LEAF_NAME, NEW_YORK_SERVER_TREE_LEAF_ID, true));

        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(
                new ServerInfo(BOSTON_SERVER_TREE_LEAF_NAME, BOSTON_SERVER_TREE_LEAF_ID, false));

        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(
                new ServerInfo(LOS_ANGELES_SERVER_TREE_LEAF_NAME, LOS_ANGELES_SERVER_TREE_LEAF_ID, true));

        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode(UK_BRANCH_TREE_NODE_NAME);

        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(
                new ServerInfo(LONDON_SERVER_TREE_LEAF_NAME, LONDON_SERVER_TREE_LEAF_ID, false));

        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(
                new ServerInfo(EDINBURGH_SERVER_TREE_LEAF_NAME, EDINBURGH_SERVER_TREE_LEAF_ID, true));

        branch2.add(server4);
        branch2.add(server5);

        top.add(branch1);
        top.add(branch2);

        return top;
    }

}
