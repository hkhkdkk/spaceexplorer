package com.tekmob.spaceexplorers.Model;

/**
 * Created by Muhammad Fajar Siddiq on 12/24/2014.
 */
public class SpaceObject {
    private String key;
    private String name;
    private double distance;
    private String desc;

    public SpaceObject(String key, String name, double distance, String desc) {
        this.key = key;
        this.name = name;
        this.distance = distance;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public double getDistance() {
        return distance;
    }

    public String getDesc() {
        return desc;
    }

}
