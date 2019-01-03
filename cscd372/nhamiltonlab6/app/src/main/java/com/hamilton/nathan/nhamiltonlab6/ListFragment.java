package com.hamilton.nathan.nhamiltonlab6;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment implements ExpandableListView.OnChildClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "ARG_LIST";

    // TODO: Rename and change types of parameters
    private ArrayList<Manufacturer> mList;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param list parameter
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(ArrayList<Manufacturer> list) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mList = (ArrayList<Manufacturer>) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        ExpandableListView expList = root.findViewById(R.id.fragment_exp_list);

        Adapter adapter = new Adapter(this.getActivity(), mList);
        expList.setAdapter(adapter);
        expList.setOnChildClickListener(this);

        return root;
    }

    @Override
    public boolean onChildClick(ExpandableListView list, View view, int groupIndex, int childIndex, long id)
    {
        String manufacturer = mList.get(groupIndex).getName();
        Model model = mList.get(groupIndex).getModel(childIndex);

        MainActivity activity = (MainActivity) getActivity();
        activity.changePage(model);

        return true;
    }
}
