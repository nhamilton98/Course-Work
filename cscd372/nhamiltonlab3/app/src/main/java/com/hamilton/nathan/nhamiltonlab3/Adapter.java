package com.hamilton.nathan.nhamiltonlab3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseExpandableListAdapter implements View.OnClickListener
{
    Activity mActivity;
    ArrayList<Manufacturer> mMakes;
    LayoutInflater mInflater;

    public Adapter(Activity activity, ArrayList<Manufacturer> makes)
    {
        mActivity = activity;
        mMakes = makes;
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount()
    {
        return mMakes.size();
    }

    @Override
    public int getChildrenCount(int groupIndex)
    {
        return mMakes.get(groupIndex).modelCount();
    }

    @Override
    public Manufacturer getGroup(int groupIndex)
    {
        return mMakes.get(groupIndex);
    }

    @Override
    public String getChild(int groupIndex, int childIndex)
    {
        return mMakes.get(groupIndex).getModel(childIndex);
    }

    @Override
    public long getGroupId(int groupIndex)
    {
        return groupIndex;
    }

    @Override
    public long getChildId(int groupIndex, int childIndex)
    {
        return childIndex;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public View getGroupView(int groupIndex, boolean isExpanded, View convertView, ViewGroup parent)
    {
        if (convertView == null)
            convertView = mInflater.inflate(R.layout.make, null);

        String name = getGroup(groupIndex).getName();
        TextView text = convertView.findViewById(R.id.make);
        text.setText(name);

        return convertView;
    }

    @Override
    public View getChildView(int groupIndex, int childIndex, boolean isLastChild, View convertView, ViewGroup parent)
    {
        if (convertView == null)
            convertView = mInflater.inflate(R.layout.model, null);

        String name = getChild(groupIndex, childIndex);
        TextView text = convertView.findViewById(R.id.model);
        text.setText(name);

        ImageView delete = convertView.findViewById(R.id.delete);
        delete.setTag(R.id.make, groupIndex);
        delete.setTag(R.id.model, childIndex);
        delete.setOnClickListener(this);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupIndex, int childIndex)
    {
        return true;
    }

    @Override
    public void onClick(View view)
    {
        int groupNum = (int) view.getTag(R.id.make);
        int childNum = (int) view.getTag(R.id.model);

        mMakes.get(groupNum).deleteModel(childNum);
        this.notifyDataSetChanged();
    }
}
