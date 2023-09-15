package com.example.registrationsetup;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Collections;


public class BasicInfoForm extends Fragment {

    final Calendar myCalender = Calendar.getInstance();

    TextView genderTV;
    View view;
    EditText name, datePick, priMob, secMob, emailId;
    RadioGroup genderGroup;
    RadioButton male, female, other;

    Button nextForm1;

    String gender = "";

    // for shared-preference
    SharedPreferences sharedPreferences;
    public static final String UserMobNo = "UserMobileNumber";


    @Override
    public void onResume() {
        super.onResume();
        // Update the current step in the progress bar
        int currentStep = 1; // Replace this with the appropriate current step for this fragment
        ((MainActivity) requireActivity()).updateStepProgressBar(currentStep);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_basic_info_form, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.updatePageTitle("Basic Information");
        mainActivity.updatePageCounter("Step 1-4");


        nextForm1 = view.findViewById(R.id.btnForm1Next);

        name = view.findViewById(R.id.etName);
        datePick = view.findViewById(R.id.etDob);
        genderGroup = view.findViewById(R.id.genderGroup);
        priMob = view.findViewById(R.id.etPrimaryPhoneNum);
        secMob = view.findViewById(R.id.etSecondaryPhoneNum);
        emailId = view.findViewById(R.id.etEmail);

        genderTV = view.findViewById(R.id.tvGender);
        male = view.findViewById(R.id.radBtnMale);
        female = view.findViewById(R.id.radBtnFemale);
        other = view.findViewById(R.id.radBtnOther);


        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                genderTV.setError(null);

                if (i == R.id.radBtnMale) {
                    gender = "Male";
                } else if (i == R.id.radBtnFemale) {
                    gender = "Female";
                } else if (i == R.id.radBtnOther) {
                    gender = "Other";
                }
            }
        });

        datePick.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                datePick.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePickerDialog(view);
            }
        });


        nextForm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
                Log.d("check2", "Input Data : " + name.getText().toString() + " \n" + gender + "\n" + datePick.getText().toString()
                        + "\n" + priMob.getText().toString() + "\n" + secMob.getText().toString() + "\n" + emailId.getText().toString());
            }
        });
        sharedPreferences = this.getActivity().getSharedPreferences("LoginMobileNum", Context.MODE_PRIVATE);

        return view;
    }

    private void checkValidation() {
        String uName = name.getText().toString();
        String dob = datePick.getText().toString();
        String pMob = priMob.getText().toString();
        String email = emailId.getText().toString();


        int selectedGender = genderGroup.getCheckedRadioButtonId();

        if (uName.isEmpty()) {
            name.setError("please enter name");
            name.requestFocus();
        } else if (selectedGender == -1) {
            genderTV.setError("Select Gender");
            genderTV.requestFocus();
        } else if (dob.isEmpty()) {
            datePick.setError("Select DOB");
            datePick.requestFocus();
        } else if (pMob.isEmpty()) {
            priMob.setError("enter mobile number");
            priMob.requestFocus();
        } else if (email.isEmpty()) {
            emailId.setError("enter email id");
            emailId.requestFocus();
        } else {
            // sending mobile number to verification form(Last Page/Step 4) using shared-preference...
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(UserMobNo, pMob);
            editor.commit();


            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new AddressInfoForm());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }


    public void openDatePickerDialog(final View v) {
        // Get Current Date
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.TimePickerTheme,
                (view, year, monthOfYear, dayOfMonth) -> {
                    String selectedDate;
                    if (dayOfMonth < 10 && monthOfYear < 10) {
                        selectedDate = "0" + dayOfMonth + "/" + "0" + (monthOfYear + 1) + "/" + year;
                    } else if (dayOfMonth < 10 && monthOfYear > 9) {
                        selectedDate = "0" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    } else if (dayOfMonth > 9 && monthOfYear < 9) {
                        selectedDate = dayOfMonth + "/" + "0" + (monthOfYear + 1) + "/" + year;
                    } else {
                        selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    }
                    // String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    switch (view.getId()) {
                        case R.id.etDob:
                            ((EditText) v).setText(selectedDate);
                            break;
                    }
                    datePick.setText(selectedDate);
                }, myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DAY_OF_MONTH));


        datePickerDialog.getDatePicker().setMaxDate(myCalender.getTimeInMillis());
        datePickerDialog.show();
        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
    }
}
