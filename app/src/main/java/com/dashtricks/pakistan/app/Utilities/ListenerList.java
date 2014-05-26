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

    /**
     * Add listener to list
     *
     * @param listener L
     */
    public void add(L listener) {
        listenerList.add(listener);
    }

    /**
     * Fire events from this list
     *
     * @param fireHandler FireHandler<L>
     */
    public void fireEvent(FireHandler<L> fireHandler) {
        List<L> copy = new ArrayList<L>(listenerList);
        for (L l : copy) {
            fireHandler.fireEvent(l);
        }
    }

    /**
     * Remove listener from list
     *
     * @param listener L
     */
    public void remove(L listener) {
        listenerList.remove(listener);
    }

    /**
     * Return list of listeners
     * @return List<L>
     */
    public List<L> getListenerList() {
        return listenerList;
    }
}