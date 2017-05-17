package com.mmclcs;

import java.util.Collection;

/**
 * This is a multi-threaded worker class, it's design is based off of how GPUs work,
 * multiple different systems doing the same task at the same time.
 * It creates X amount of threads that run given Runnable objects, it is mostly
 * meant for working over large lists.
 * Created by mackenzie on 17/05/17.
 */
public class MultiWorker {

    private Worker[] threads;
    private boolean[] threadStatus;
    private int collectionGrab;
    private boolean running;
    private Collection<?> objects;
    private Object[][] seperatedObjects;
    private WorkerRunnable task;

    public MultiWorker(int threadCount) {
        // Initialize values
        running = false;
        threads = new Worker[threadCount];
        for (int item = 0; item < threads.length; item++) {
            threads[item] = new Worker();
        }
        for (int item = 0; item < threadStatus.length; item++) {
            threadStatus[item] = false;
        }
    }

    /**
     * This will set the task the worker threads are supposed to do, if the worker threads are already running
     * it will return false and not have changed over the task.
     *
     * @param task the task the threads should be running
     * @return whether or not it was able to release a lock on the task
     */
    public boolean setTask(WorkerRunnable task) {
        if (running) return false;
        this.task = task;
        return true;
    }

    /**
     * This will set the current collection that the worker threads should access
     * it will return false if the worker threads are already running, because it will not have been able
     * to set the current collection
     *
     * @param objects Collection that the worker threads should read from
     * @return whether or not it was able to set the collection (if worker threads are running)
     */
    public boolean setCollection(Collection<?> objects) {
        if (running) return false;
        this.objects = objects;
        return true;
    }

    public void run() {
        int amount = objects.size();
        int objectsPerThread = amount / objects.size();
        int overflow = amount % threads.length;
        if (overflow > 0)
            objectsPerThread++;
        seperatedObjects = new Object[threads.length][objectsPerThread];
        for (int index = 0; index < seperatedObjects.length; index++) {
            for (int item = 0; item < seperatedObjects[index].length; item++) {

            }
        }
    }

    private synchronized Object[] getArray() {
        return seperatedObjects[collectionGrab++];
    }

    private class Worker extends Thread {

        private Object[] objects;

        @Override
        public void start() {
            objects = getArray();
        }

        @Override
        public void run() {
            for (Object item : objects) {
                task.run(item);
            }
        }

    }

}
