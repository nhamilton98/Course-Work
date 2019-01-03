package com.hamilton.nathan.nhamiltonlab6;

import java.util.ArrayList;

public class Manufacturer
{
    String mMake;
    ArrayList<Model> mModels;

    public Manufacturer(String name)
    {
        mMake = name;
        mModels = new ArrayList<Model>();
    }

    public Manufacturer(String name, ArrayList<Model> models)
    {
        mMake = name;
        mModels = models;
    }

    public String getName()
    {
        return mMake;
    }

    public Model getModel(int index)
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

    public void addModel(String name, String years, String engine, int ID)
    {
        mModels.add(new Model(name, years, engine, ID));
    }
}

