package kiz.learnwithvel.lawofclassroom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import kiz.learnwithvel.lawofclassroom.util.LawDialog;
import kiz.learnwithvel.lawofclassroom.util.LawKeys;

public class LawFragment extends Fragment {

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Bundle args = getArguments();
        if (args != null) {
            mLabel = args.getString(LawKeys.KEY_CONDITION_STATE);
        }

        initInflatable();
    }

    private void initInflatable() {
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

    //references
    private Inflatable mListener;

    //widgets
    private String mLabel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_law, container, false);
        TextView label = view.findViewById(R.id.tv_law_state);
        label.setText(mLabel);

        CardView ayusin = view.findViewById(R.id.card_law_ayusin);
        ayusin.setOnClickListener(v -> {
            //Dialog
            final LawDialog dialog = initDialog();
            new Handler().postDelayed(() -> {
                dialog.dismiss();
                mListener.inflate(LawKeys.KEY_INFLATER_FRAGMENT_CONDITION, LawKeys.KEY_CONDITION_STATE_CONDITION_THREE);
            }, 2000);
        });
        return view;
    }

    private LawDialog initDialog() {
        LawDialog dialog = new LawDialog();
        dialog.setView(initDialogView());
        assert getFragmentManager() != null;
        dialog.show(getFragmentManager(), null);
        return dialog;
    }

    @SuppressLint("InflateParams")
    private View initDialogView() {
        return getLayoutInflater().inflate(R.layout.dialog_law, null, false);
    }

}
