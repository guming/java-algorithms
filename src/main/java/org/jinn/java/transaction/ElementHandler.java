package org.jinn.java.transaction;

import java.util.HashMap;

public class ElementHandler {

    public static HashMap<Integer,Element> elementTable = new HashMap();

    public void insert(Element element){
        elementTable.put(element.getId(),element);
    }

    public int update(Element element,Transaction transaction){
        int result=0;
        if (Scheduler.getScheduler().lock(element.getId(),transaction.getLock(),transaction.getStartTime())) {
                System.out.println("transaction takelock."+transaction.getLock().toString());
                elementTable.put(element.getId(), element);
                result = 1;
        }
        return result;
    }

    public void delete(Integer eid){
        elementTable.remove(eid);
    }

    public Element get(Integer eid){
        return elementTable.get(eid);
    }

}
