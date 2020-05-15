package kiz.learnwithvel.lawofclassroom;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import kiz.learnwithvel.lawofclassroom.util.LawKeys;
import kiz.learnwithvel.lawofclassroom.util.Resources;

public class HomeFragment extends Fragment implements View.OnClickListener {


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_home_start) {
            mListener.inflate(LawKeys.KEY_INFLATER_FRAGMENT_CONDITION);
        }
    }


    //references
    private Inflatable mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = getActivity();
        if (!(activity instanceof Inflatable)) {
            assert activity != null;
            throw new ClassCastException(activity.getClass().getSimpleName()
                    + " must implement Inflatable interface.");
        }
        mListener = (Inflatable) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //widgets
        ImageView imvKim = view.findViewById(R.id.imv_image_kim);
        Button btnStart = view.findViewById(R.id.btn_home_start);
        btnStart.setOnClickListener(this);
        Glide.with(view).load(Resources.IMAGE_URL).into(imvKim);
        return view;
    }


}
