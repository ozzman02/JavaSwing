package com.swing.controller;

import com.swing.model.Message;

import java.util.*;

/*
    This is a sort of simulated Message Server
 */
public class MessageServer implements Iterable<Message> {

    private Map<Integer, List<Message>> messages;

    private List<Message> selected;

    public MessageServer() {
        selected = new ArrayList<>();
        messages = new TreeMap<>();

        List<Message> list = new ArrayList<>();
        list.add(new Message("The cat is missing","Have you seen Felix anywhere?"));
        list.add(new Message("See you later?","Are we still meeting in the pub?"));
        list.add(new Message("Recovery process","Continue with meetings"));
        list.add(new Message("Talk to your godfather","Work the 12 Steps"));
        list.add(new Message("Breakfast?","Gallo pinto con carne mechada en salsa !!!"));
        list.add(new Message("Nike?","Thinking on Air Max !!"));
        list.add(new Message("Kamikaze II?","Old model but I would like to have it !!"));
        messages.put(0, list);

        list = new ArrayList<>();
        list.add(new Message("Coding again?","Let's eat pizza !!"));
        list.add(new Message("Thinking on getting better?","Good, keep going man!"));
        list.add(new Message("Java certification","11 V or 17 V?"));
        list.add(new Message("AWS devops?","Why not?"));
        list.add(new Message("Learn Scala !!!","Yeah sure !!!"));
        list.add(new Message("What about Kubernetes?","In progress ..."));
        list.add(new Message("React or Angular?","It seems React ... but Angular too ..."));
        messages.put(1, list);
    }

    public void setSelectedServers(Set<Integer> servers) {
        selected.clear();
        for (Integer id : servers) {
            if (messages.containsKey(id)) {
                selected.addAll(messages.get(id));
            }
        }
    }

    public int getMessageCount() {
        return selected.size();
    }

    @Override
    public Iterator<Message> iterator() {
        return new MessageIterator(selected);
    }

}

class MessageIterator implements Iterator {

    private Iterator<Message> iterator;

    public MessageIterator(List<Message> messages) {
        iterator = messages.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Object next() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        return iterator.next();
    }

    @Override
    public void remove() {
        iterator.remove();
    }

}