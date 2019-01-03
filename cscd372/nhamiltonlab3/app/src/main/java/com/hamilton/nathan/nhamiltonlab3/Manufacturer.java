package com.hamilton.nathan.nhamiltonlab3;

import java.util.ArrayList;

public class Manufacturer
{
    String mMake;
    ArrayList<String> mModels;

    public Manufacturer(String name)
    {
        mMake = name;
        mModels = new ArrayList<>();
    }

    public Manufacturer(String name, ArrayList<String> models)
    {
        mMake = name;
        mModels = models;
    }

    public String getName()
    {
        return mMake;
    }

    public String getModel(int index)
    {
        return mModels.get(index);
    }

    public void deleteModel(int index)
    {
        mModels.remove(index);
    }

    public int modelCount()
    {
        return mModels.size();
    }

    public void addModel(String name)
    {
        mModels.add(name);
    }
}
