package com.caltr.yungchai.maps;

import java.util.HashMap;
import java.util.UUID;

public class NeedleCounter {
    public HashMap<UUID, Integer> needleMap;

    public void setup () {
        needleMap = new HashMap<>();
    }

    public void push (UUID p) {
        needleMap.put(p, 0);
    }

    public boolean check (UUID p) {
        return needleMap.containsKey(p);
    }

    public int get (UUID p) {
        return needleMap.get(p);
    }

    public void increment (UUID p) {
        needleMap.replace(p, needleMap.get(p)+1);
    }

    public void set (UUID p, int newVal) {
        needleMap.replace(p, newVal);
    }
}
