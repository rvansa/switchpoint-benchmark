package com.azul.benchmark;

import org.openjdk.jmh.infra.Blackhole;

public class Unsynchronized implements Component {
    @Override
    public void quick() {
        Blackhole.consumeCPU(1);
    }

    @Override
    public void slow() {
        Blackhole.consumeCPU(1000);
    }
}
