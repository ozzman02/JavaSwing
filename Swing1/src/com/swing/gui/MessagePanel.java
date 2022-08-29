package com.swing.gui;

import com.swing.controller.MessageServer;
import com.swing.model.Message;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;

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
public class MessagePanel extends JPanel implements ProgressDialogListener {

    private JTree serverTree;
    private ServerTreeCellRenderer treeCellRenderer;
    private ServerTreeCellEditor treeCellEditor;
    private Set<Integer> selectedServers;
    private MessageServer messageServer;
    private ProgressDialog progressDialog;
    private SwingWorker<List<Message>, Integer> worker;
    private TextPanel textPanel;
    private JList<Message> messageList;
    private JSplitPane upperPane;
    private JSplitPane lowerPane;
    private DefaultListModel<Message> messageListModel;

    public MessagePanel(JFrame panel) {

        messageListModel = new DefaultListModel<>();

        progressDialog = new ProgressDialog(panel, "Message Downloading...");
        messageServer = new MessageServer();

        progressDialog.setProgressDialogListener(this);

        selectedServers = new TreeSet<>();
        selectedServers.add(0);
        selectedServers.add(1);
        selectedServers.add(4);

        treeCellRenderer = new ServerTreeCellRenderer();
        treeCellEditor = new ServerTreeCellEditor();

        serverTree = new JTree(createTree());
        serverTree.setCellRenderer(treeCellRenderer);
        serverTree.setCellEditor(treeCellEditor);
        serverTree.setEditable(true);
        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        messageServer.setSelectedServers(selectedServers);

        treeCellEditor.addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                ServerInfo serverInfo = (ServerInfo) treeCellEditor.getCellEditorValue();
                System.out.println(serverInfo + ": " + serverInfo.getId() + "; " + serverInfo.isChecked());
                int serverId = serverInfo.getId();
                if (serverInfo.isChecked()) {
                    selectedServers.add(serverId);
                } else {
                    selectedServers.remove(serverId);
                }
                messageServer.setSelectedServers(selectedServers);
                retrieveMessages();
            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });

        setLayout(new BorderLayout());

        textPanel = new TextPanel();
        textPanel.setMinimumSize(new Dimension(10, 100));

        messageList = new JList<>(messageListModel);
        messageList.setCellRenderer(new MessageListRenderer());
        messageList.setMinimumSize(new Dimension(10, 100));
        messageList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Message message = messageList.getSelectedValue();
                if (message != null) textPanel.setText(message.getContents());
            }
        });

        lowerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(messageList), textPanel);
        upperPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(serverTree), lowerPane);
        upperPane.setResizeWeight(0.5);
        lowerPane.setResizeWeight(0.5);

        add(upperPane, BorderLayout.CENTER);
    }

    public void refresh() {
        retrieveMessages();
    }

    private void retrieveMessages() {
        progressDialog.setMaximum(messageServer.getMessageCount());
        progressDialog.setVisible(true);

        worker = new SwingWorker<>() {

            @Override
            protected List<Message> doInBackground() throws Exception {
                List<Message> retrievedMessages = new ArrayList<>();
                int count = 0;
                for (Message message : messageServer) {
                    if (isCancelled()) break;
                    System.out.println(message.getTitle());
                    retrievedMessages.add(message);
                    count++;
                    publish(count);
                }
                return retrievedMessages;
            }

            @Override
            protected void process(List<Integer> counts) {
                int retrieved = counts.get(counts.size() - 1);
                progressDialog.setValue(retrieved);
            }

            @Override
            protected void done() {
                progressDialog.setVisible(false);
                if (isCancelled()) return;
                try {
                    List<Message> retrievedMessages = get();
                    messageListModel.removeAllElements();
                    for (Message message : retrievedMessages) {
                        messageListModel.addElement(message);
                    }
                    //messageList.setSelectedIndex(0);
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        worker.execute();
    }

    private DefaultMutableTreeNode createTree() {

        DefaultMutableTreeNode top = new DefaultMutableTreeNode(TOP_TREE_NODE_NAME);

        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode(USA_BRANCH_TREE_NODE_NAME);

        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(
                new ServerInfo(NEW_YORK_SERVER_TREE_LEAF_NAME, NEW_YORK_SERVER_TREE_LEAF_ID,
                        selectedServers.contains(0)
                ));

        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(
                new ServerInfo(BOSTON_SERVER_TREE_LEAF_NAME, BOSTON_SERVER_TREE_LEAF_ID,
                        selectedServers.contains(1)
                ));

        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(
                new ServerInfo(LOS_ANGELES_SERVER_TREE_LEAF_NAME, LOS_ANGELES_SERVER_TREE_LEAF_ID,
                        selectedServers.contains(2)
                ));

        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode(UK_BRANCH_TREE_NODE_NAME);

        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(
                new ServerInfo(LONDON_SERVER_TREE_LEAF_NAME, LONDON_SERVER_TREE_LEAF_ID,
                        selectedServers.contains(3)
                ));

        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(
                new ServerInfo(EDINBURGH_SERVER_TREE_LEAF_NAME, EDINBURGH_SERVER_TREE_LEAF_ID,
                        selectedServers.contains(4)
                ));

        branch2.add(server4);
        branch2.add(server5);

        top.add(branch1);
        top.add(branch2);

        return top;
    }

    @Override
    public void progressDialogCancelled() {
        if (worker != null) {
            worker.cancel(true);
        }
    }
}
