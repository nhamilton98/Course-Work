package com.hamilton.nathan.nhamiltonlab6;

import java.io.Serializable;

public class Model implements Serializable
{
    private String mModel;
    private String mYears;
    private String mEngineSize;
    private int mPicID;

    public Model(String model, String years, String engine, int ID)
    {
        mModel = model;
        mYears = years;
        mEngineSize = engine;
        mPicID = ID;
    }

    public String getModel()
    {
        return mModel;
    }

    public String getYears()
    {
        return mYears;
    }

    public String getEngineSize()
    {
        return mEngineSize;
    }

    public int getImageID()
    {
        return mPicID;
    }
}
