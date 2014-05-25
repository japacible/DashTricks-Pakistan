package com.dashtricks.pakistan.app.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Adopted from http://stackoverflow.com/questions/3592717/choose-file-dialog
 *
 * Created by japacible on 5/24/14.
 */
public class ListenerList<L> {
    private List<L> listenerList = new ArrayList<L>();

    public interface FireHandler<L> {
        void fireEvent(L listener);
    }

    public void add(L listener) {
        listenerList.add(listener);
    }

    public void fireEvent(FireHandler<L> fireHandler) {
        List<L> copy = new ArrayList<L>(listenerList);
        for (L l : copy) {
            fireHandler.fireEvent(l);
        }
    }

    public void remove(L listener) {
        listenerList.remove(listener);
    }

    public List<L> getListenerList() {
        return listenerList;
    }
}