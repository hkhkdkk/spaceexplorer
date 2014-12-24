package com.tekmob.spaceexplorer.Controller;

import com.tekmob.spaceexplorer.Model.SpaceObject;

import java.util.ArrayList;

/**
 * Created by Muhammad Fajar on 12/24/2014.
 */
public class ObjectContainer {
    final static String KEY = "item";
    public static ArrayList<SpaceObject> objects = new ArrayList<SpaceObject>();

    public static void initObject(String [] data) {
        for (int i = 0; i < data.length; i++) {
            String [] temp = data[i].split(";");
            objects.add(new SpaceObject(KEY+i, temp[1], Double.parseDouble(temp[2]), temp[3]));
        }
    }

    public static boolean checkObject() {
        if (objects.size() == 0) {
            return true;
        }
        return false;
    }

}
