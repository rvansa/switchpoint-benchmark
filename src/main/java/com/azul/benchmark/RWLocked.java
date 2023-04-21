package com.azul.benchmark;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLocked extends Unsynchronized {
    private final ReadWriteLock rwlock = new ReentrantReadWriteLock();
    @Override
    public void quick() {
        Lock lock = rwlock.readLock();
        lock.lock();
        try {
            super.quick();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void slow() {
        Lock lock = rwlock.writeLock();
        lock.lock();
        try {
            super.slow();
        } finally {
            lock.unlock();
        }
    }
}
