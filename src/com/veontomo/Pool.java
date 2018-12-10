package com.veontomo;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

/**
 * Slightly modified version of a demo of Semaphore class from Java™ Platform, Standard Edition 8
 * API Specification.
 *
 * @url https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Semaphore.html
 */
class Pool {
    private final int MAX_AVAILABLE;
    private final Semaphore available;

    private Object[] items;
    private boolean[] used;

    Pool(int size) {
        MAX_AVAILABLE = size;
        available = new Semaphore(MAX_AVAILABLE, true);
        items = new Object[MAX_AVAILABLE];
        used = new boolean[MAX_AVAILABLE];
        Arrays.setAll(items, (index) -> new Object());
    }

    Object getItem() throws InterruptedException {
        available.acquire();
        return getNextAvailableItem();
    }

    void putItem(Object x) {
        if (markAsUnused(x)) {
            available.release();
        }
    }


    private synchronized Object getNextAvailableItem() {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (!used[i]) {
                used[i] = true;
                return items[i];
            }
        }
        return null; // not reached
    }

    private synchronized boolean markAsUnused(Object item) {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (item == items[i]) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }
}