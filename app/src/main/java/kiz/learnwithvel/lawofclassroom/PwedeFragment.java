package kiz.learnwithvel.lawofclassroom;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import kiz.learnwithvel.lawofclassroom.util.LawKeys;

public class PwedeFragment extends Fragment {

    private Inflatable mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = getActivity();
        if (!(activity instanceof Inflatable)) {
            throw new ClassCastException(activity.getClass().getSimpleName()
                    + " must implement Inflatable interface.");
        }
        mListener = (Inflatable) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_pwede, container, false);
        Button btnRestart = view.findViewById(R.id.btn_labasna_restart);
        btnRestart.setOnClickListener(v -> {
            mListener.inflate(LawKeys.KEY_INFLATER_FRAGMENT_HOME);
        });

        return view;
    }
}
