package com.swing.gui;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

import static com.swing.commons.Constants.*;

public class MessagePanel extends JPanel {

    private JTree serverTree;

    public MessagePanel() {
        serverTree = new JTree(createTree());
        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        serverTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) serverTree.getLastSelectedPathComponent();
                Object userObject = node.getUserObject();

            }
        });
        setLayout(new BorderLayout());
        add(new JScrollPane(serverTree), BorderLayout.CENTER);
    }

    private DefaultMutableTreeNode createTree() {

        DefaultMutableTreeNode top = new DefaultMutableTreeNode(TOP_TREE_NODE);

        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode(USA_BRANCH_TREE_NODE);
        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(NEW_YORK_SERVER_TREE_LEAF);
        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(BOSTON_SERVER_TREE_LEAF);
        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(LOS_ANGELES_SERVER_TREE_LEAF);

        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode(UK_BRANCH_TREE_NODE);
        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(LONDON_SERVER_TREE_LEAF);
        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(EDINBURGH_SERVER_TREE_LEAF);

        branch2.add(server4);
        branch2.add(server5);

        top.add(branch1);
        top.add(branch2);

        return top;
    }

}
