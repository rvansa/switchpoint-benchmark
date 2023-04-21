package com.azul.benchmark;


import jdk.crac.RCULock;

public class RCULocked implements Component {
    private final Unsynchronized delegate = new Unsynchronized();
    private final RCULock rcuLock = RCULock.forClasses(RCULocked.class);

    @Override
    public void quick() {
        try {
            quickImpl();
        } finally {
            rcuLock.readUnlock();
        }
    }

    @RCULock.Critical
    public void quickImpl() {
        rcuLock.readLock();
        delegate.quick();
    }

    @Override
    public void slow() {
        rcuLock.synchronizeBegin();
        try {
            delegate.slow();
        } finally {
            rcuLock.synchronizeEnd();
        }
    }
}
