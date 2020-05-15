package kiz.learnwithvel.lawofclassroom;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import kiz.learnwithvel.lawofclassroom.util.LawKeys;

public class ConditionFragment extends Fragment {

    private static final String TAG = "ConditionFragment";

    private Inflatable mListener;

    //variables
    private String mCondition;
    private String mState;
    private String mLabel;
    private String mPwede;
    private String mBawal;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Bundle args = getArguments();
        if (args != null) {
            mCondition = args.getString(LawKeys.KEY_CONDITION_STATE_CONDITION);
            mState = args.getString(LawKeys.KEY_CONDITION_STATE);
            mLabel = args.getString(LawKeys.KEY_CONDITION_LABEL);
            mPwede = args.getString(LawKeys.KEY_CONDITION_RB_ONE);
            mBawal = args.getString(LawKeys.KEY_CONDITION_RB_TWO);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_condition, container, false);
        TextView state = view.findViewById(R.id.tv_condition_state);
        TextView label = view.findViewById(R.id.tv_condition_label);
        RadioButton pwede = view.findViewById(R.id.rb_pwede_lumabas);
        RadioButton bawal = view.findViewById(R.id.rb_bawal_lumabas);
        state.setText(mState);
        label.setText(mLabel);
        pwede.setText(mPwede);
        bawal.setText(mBawal);

        initPermissionToPassed(view);

        return view;
    }

    private void initPermissionToPassed(View view) {
        //references
        RadioGroup rgPermission = view.findViewById(R.id.rg_permission);
        rgPermission.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selected = group.findViewById(checkedId);

            switch (selected.getId()) {
                case R.id.rb_pwede_lumabas:
                    Log.d(TAG, "initPermissionToPassed() pwede lumabas! ");
                    if (mCondition.equals(LawKeys.KEY_CONDITION_STATE_CONDITION_THREE)) {
                        //meron nagawa mali
                        //ayusin ang law sa classroom
                        mListener.inflate(LawKeys.KEY_INFLATER_FRAGMENT_LAW);
                        break;
                    }
                    mListener.inflate(LawKeys.KEY_INFLATER_FRAGMENT_LABAS_NA);


                    break;
                case R.id.rb_bawal_lumabas:
                    Log.d(TAG, "initPermissionToPassed() " + mBawal);
                    if (mCondition.equals(LawKeys.KEY_CONDITION_STATE_CONDITION_FOUR)) {
                        //ayusin ulit
                        mListener.inflate(LawKeys.KEY_INFLATER_FRAGMENT_LAW);
                        break;
                    }

                    if (mCondition.equals(LawKeys.KEY_CONDITION_STATE_CONDITION_THREE)) {
                        mListener.inflate(LawKeys.KEY_INFLATER_FRAGMENT_LABAS_NA);
                        break;
                    }
                    mListener.inflate(LawKeys.KEY_INFLATER_FRAGMENT_CONDITION, mCondition);
                    break;
            }

        });


    }
}
