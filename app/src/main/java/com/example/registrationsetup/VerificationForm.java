package com.example.registrationsetup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class VerificationForm extends Fragment {

    View view;
    TextView mobNum;

    Button backForm4;

    // for shared-preference
    SharedPreferences sharedPreferences;



    @Override
    public void onResume() {
        super.onResume();
        // Update the current step in the progress bar
        int currentStep = 4; // Replace this with the appropriate current step for this fragment
        ((MainActivity) requireActivity()).updateStepProgressBar(currentStep);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_verification_form, container, false);

        MainActivity mainActivity=(MainActivity) getActivity();
        mainActivity.updatePageTitle("");
        mainActivity.updatePageCounter("Step 4-4");

        backForm4=view.findViewById(R.id.btnForm4Back);
        mobNum=view.findViewById(R.id.tvMobileNo);

        sharedPreferences=this.getActivity().getSharedPreferences("LoginMobileNum", Context.MODE_PRIVATE);
        String uMob=sharedPreferences.getString("UserMobileNumber","");
        SharedPreferences.Editor editor=sharedPreferences.edit();

        mobNum.setText(uMob);
        editor.commit();
        Log.d("check7Mob",""+uMob);






        backForm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new PersonalInfoForm());
                // fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

              */
                FragmentManager fm=getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });

        return view;
    }
}