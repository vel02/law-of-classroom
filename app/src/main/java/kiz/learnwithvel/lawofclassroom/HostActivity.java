package kiz.learnwithvel.lawofclassroom;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import kiz.learnwithvel.lawofclassroom.services.LawService;
import kiz.learnwithvel.lawofclassroom.util.LawKeys;

public class HostActivity extends AppCompatActivity implements Inflatable {

    @Override
    public void inflate(String tag) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Bundle args;

        switch (tag) {
            case LawKeys
                    .KEY_INFLATER_FRAGMENT_HOME:
                initHomeFragment();
                break;
            case LawKeys
                    .KEY_INFLATER_FRAGMENT_CONDITION:
                mFragmentCondition = new ConditionFragment();
                args = new Bundle();
                args.putString(LawKeys.KEY_CONDITION_STATE_CONDITION, LawKeys.KEY_CONDITION_STATE_CONDITION_ONE);
                args.putString(LawKeys.KEY_CONDITION_LABEL, "Classroom");
                args.putString(LawKeys.KEY_CONDITION_RB_ONE, "Pwede lumabas");
                args.putString(LawKeys.KEY_CONDITION_RB_TWO, "Bawal lumabas");

                mFragmentCondition.setArguments(args);
                transaction.replace(R.id.fragment_container, mFragmentCondition, getString(R.string.tag_fragment_condition));
                transaction.commit();
                break;

            case LawKeys.KEY_INFLATER_FRAGMENT_LABAS_NA:
                initPwedeFragment();
                break;

            case LawKeys.KEY_INFLATER_FRAGMENT_LAW:
                LawFragment lawFragment = new LawFragment();
                args = new Bundle();
                args.putString(LawKeys.KEY_CONDITION_STATE, "Bawal Lumabas!");
                lawFragment.setArguments(args);
                transaction.replace(R.id.fragment_container, lawFragment, getString(R.string.tag_fragment_law));
                transaction.commit();
                break;
        }

    }

    @Override
    public void inflate(String tag, String state) {
        Bundle args;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (state) {
            case LawKeys.KEY_CONDITION_STATE_CONDITION_ONE:
                mFragmentCondition = new ConditionFragment();

                args = new Bundle();
                args.putString(LawKeys.KEY_CONDITION_STATE_CONDITION, LawKeys.KEY_CONDITION_STATE_CONDITION_TWO);
                args.putString(LawKeys.KEY_CONDITION_STATE, "Bawal Lumabas!");
                args.putString(LawKeys.KEY_CONDITION_LABEL, "Nag comply ka ba?");
                args.putString(LawKeys.KEY_CONDITION_RB_ONE, "Oo");
                args.putString(LawKeys.KEY_CONDITION_RB_TWO, "Hindi");
                mFragmentCondition.setArguments(args);

                transaction.replace(R.id.fragment_container, mFragmentCondition, getString(R.string.tag_fragment_condition));
                transaction.commit();
                break;
            case LawKeys.KEY_CONDITION_STATE_CONDITION_TWO:
                mFragmentCondition = new ConditionFragment();

                args = new Bundle();
                args.putString(LawKeys.KEY_CONDITION_STATE_CONDITION, LawKeys.KEY_CONDITION_STATE_CONDITION_THREE);
                args.putString(LawKeys.KEY_CONDITION_STATE, "Bawal Lumabas!");
                args.putString(LawKeys.KEY_CONDITION_LABEL, "May ginawa ka bang bawal?");
                args.putString(LawKeys.KEY_CONDITION_RB_ONE, "Meron");
                args.putString(LawKeys.KEY_CONDITION_RB_TWO, "Wala");
                mFragmentCondition.setArguments(args);

                transaction.replace(R.id.fragment_container, mFragmentCondition, getString(R.string.tag_fragment_condition));
                transaction.commit();
                break;
            case LawKeys.KEY_CONDITION_STATE_CONDITION_THREE:
                if (tag.equals(LawKeys.KEY_INFLATER_FRAGMENT_CONDITION)) {
                    mFragmentCondition = new ConditionFragment();

                    args = new Bundle();
                    args.putString(LawKeys.KEY_CONDITION_STATE_CONDITION, LawKeys.KEY_CONDITION_STATE_CONDITION_FOUR);
                    args.putString(LawKeys.KEY_CONDITION_STATE, "Bawal Lumabas!");
                    args.putString(LawKeys.KEY_CONDITION_LABEL, "Nais mo ba itong ipasa?");
                    args.putString(LawKeys.KEY_CONDITION_RB_ONE, "Oo");
                    args.putString(LawKeys.KEY_CONDITION_RB_TWO, "Hindi");
                    mFragmentCondition.setArguments(args);

                    transaction.replace(R.id.fragment_container, mFragmentCondition, getString(R.string.tag_fragment_condition));
                    transaction.commit();
                    break;
                }

                initPwedeFragment();
                break;
        }
    }


    //references
    private ConditionFragment mFragmentCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            initHomeFragment();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(new Intent(this, LawService.class));
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(new Intent(this, LawService.class));
    }

    private void initHomeFragment() {
        //fragments
        HomeFragment fragmentHome = new HomeFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragmentHome, getString(R.string.tag_fragment_home));
        transaction.commit();
    }

    private void initPwedeFragment() {
        PwedeFragment fragmentPwede = new PwedeFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragmentPwede, getString(R.string.tag_fragment_pwede));
        transaction.commit();
    }
}
