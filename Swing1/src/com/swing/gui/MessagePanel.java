package com.swing.gui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class MessagePanel extends JPanel {

    private JTree serverTree;

    public MessagePanel() {
        serverTree = new JTree(createTree());
        setLayout(new BorderLayout());
        add(new JScrollPane(serverTree), BorderLayout.CENTER);
    }

    private DefaultMutableTreeNode createTree() {

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");

        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("USA");
        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode("New York");
        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode("Boston");
        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode("Los Angeles");

        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode("London");
        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode("Edinburgh");

        branch2.add(server4);
        branch2.add(server5);

        top.add(branch1);
        top.add(branch2);

        return top;
    }

}
