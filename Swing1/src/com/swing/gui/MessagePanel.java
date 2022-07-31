package com.swing.gui;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

import static com.swing.commons.Constants.*;

class ServerInfo {
    private String name;
    private int id;

    public ServerInfo(String name, int id) {
        this.name = name;
        this.id = id;
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

    @Override
    public String toString() {
        return this.getName();
    }

}
public class MessagePanel extends JPanel {

    private JTree serverTree;
    private DefaultTreeCellRenderer treeCellRenderer;

    public MessagePanel() {
        treeCellRenderer = new DefaultTreeCellRenderer();
        treeCellRenderer.setLeafIcon(Utils.createIcon(TREE_LEAF_ICON_BUTTON_PATH));
        treeCellRenderer.setOpenIcon(Utils.createIcon(TREE_OPEN_ICON_BUTTON_PATH));
        treeCellRenderer.setClosedIcon(Utils.createIcon(TREE_CLOSED_ICON_BUTTON_PATH));
        serverTree = new JTree(createTree());
        serverTree.setCellRenderer(treeCellRenderer);
        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        serverTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) serverTree.getLastSelectedPathComponent();
                Object userObject = node.getUserObject();
                if (userObject instanceof ServerInfo) {
                    int id = ((ServerInfo) userObject).getId();
                }
            }
        });
        setLayout(new BorderLayout());
        add(new JScrollPane(serverTree), BorderLayout.CENTER);
    }

    private DefaultMutableTreeNode createTree() {

        DefaultMutableTreeNode top = new DefaultMutableTreeNode(TOP_TREE_NODE_NAME);

        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode(USA_BRANCH_TREE_NODE_NAME);

        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(
                new ServerInfo(NEW_YORK_SERVER_TREE_LEAF_NAME, NEW_YORK_SERVER_TREE_LEAF_ID));

        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(
                new ServerInfo(BOSTON_SERVER_TREE_LEAF_NAME, BOSTON_SERVER_TREE_LEAF_ID));

        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(
                new ServerInfo(LOS_ANGELES_SERVER_TREE_LEAF_NAME, LOS_ANGELES_SERVER_TREE_LEAF_ID));

        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode(UK_BRANCH_TREE_NODE_NAME);

        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(
                new ServerInfo(LONDON_SERVER_TREE_LEAF_NAME, LONDON_SERVER_TREE_LEAF_ID));

        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(
                new ServerInfo(EDINBURGH_SERVER_TREE_LEAF_NAME, EDINBURGH_SERVER_TREE_LEAF_ID));

        branch2.add(server4);
        branch2.add(server5);

        top.add(branch1);
        top.add(branch2);

        return top;
    }

}
