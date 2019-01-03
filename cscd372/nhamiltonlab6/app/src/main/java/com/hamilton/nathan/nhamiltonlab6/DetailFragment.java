package com.hamilton.nathan.nhamiltonlab6;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    TextView mModel;
    TextView mYears;
    TextView mEngineSize;
    ImageView mImage;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        mModel = root.findViewById(R.id.model_name);
        mYears = root.findViewById(R.id.model_years);
        mEngineSize = root.findViewById(R.id.model_engine_size);
        mImage = root.findViewById(R.id.model_image);

        MainActivity activity = (MainActivity) getActivity();
        Model model = activity.getSelectedModel();
        mModel.setText(model.getModel());
        mYears.setText(model.getYears());
        mEngineSize.setText(model.getEngineSize());
        mImage.setImageResource(model.getImageID());

        return root;
    }

}
