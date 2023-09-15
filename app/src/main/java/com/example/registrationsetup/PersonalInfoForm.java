package com.example.registrationsetup;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalInfoForm extends Fragment {

    View view;
    // hidden textView
    TextView otherEduTv, otherProfessionTv, otherSubProTv, otherOccupationTv;
    EditText otherEduEt, otherProfessionEt, otherSubProEt, otherOccupationEt;

    // dropdown list variable initialization
    AutoCompleteTextView disabilityATV, educationATV, professionATV, subProfessionATV, occupationATV;

    Button backForm3, nextForm3;

    String MartialStatus="";
    RadioGroup checkMartialStatus;

    AutoCompleteTextView search;


    @Override
    public void onResume() {
        super.onResume();
        // Update the current step in the progress bar
        int currentStep = 3; // Replace this with the appropriate current step for this fragment
        ((MainActivity) requireActivity()).updateStepProgressBar(currentStep);

        getBack();
        Log.d("check6Data",""+educationATV.getText().toString()+"\t"+professionATV.getText().toString()+"\n"+subProfessionATV.getText().toString()+"\t"+occupationATV.getText().toString());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_personal_info_form, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.updatePageTitle("Personal Information");
        mainActivity.updatePageCounter("Step 3-4");

        backForm3 = view.findViewById(R.id.btnForm3Back);
        nextForm3 = view.findViewById(R.id.btnForm3Next);

        // id initialization of dropdown list
        disabilityATV = view.findViewById(R.id.autoTvDisability);
        educationATV = view.findViewById(R.id.autoTvEducation);
        professionATV = view.findViewById(R.id.autoTvProfession);
        subProfessionATV = view.findViewById(R.id.autoTvSubProfession);
        occupationATV = view.findViewById(R.id.autoTvOccupation);

        // hidden textview and edittext id initialization..
        otherEduTv = view.findViewById(R.id.tvOtherEdu);
        otherProfessionTv = view.findViewById(R.id.tvOtherProfession);
        otherSubProTv = view.findViewById(R.id.tvOtherSubProfession);
        otherOccupationTv = view.findViewById(R.id.tvOtherOccupation);

        otherEduEt = view.findViewById(R.id.etOtherEduDesc);
        otherProfessionEt = view.findViewById(R.id.etOtherProfessionDesc);
        otherSubProEt = view.findViewById(R.id.etOtherSubProfessionDesc);
        otherOccupationEt = view.findViewById(R.id.etOtherOccupationDesc);

        checkMartialStatus=view.findViewById(R.id.radGroMartialStatus);

        search=view.findViewById(R.id.autoTvSearchCountry);

        checkMartialStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int martialStatus) {
                if(martialStatus==R.id.radBtnMarried){
                    MartialStatus="Married";
                }
                else if(martialStatus==R.id.radBtnSingle){
                    MartialStatus="Single";
                }
            }
        });



        // code for selecting of disability form list..
        disabilityATV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(),R.style.MyDialogTheme);
                View customLayout = getLayoutInflater().inflate(R.layout.country_list, null);
                builder.setView(customLayout);

                ListView listView = customLayout.findViewById(R.id.listViewCountry);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(),
                        R.layout.row,
                        getResources().getStringArray(R.array.physical_disability));
                listView.setAdapter(arrayAdapter);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getWindow().setLayout(600,900);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Get the selected item
                        String selectedValue = arrayAdapter.getItem(position);

                        // Set the selected value to the TextView
                        disabilityATV.setText(selectedValue);

                        // Dismiss the AlertDialog
                        alertDialog.dismiss();
                    }
                });

                search=customLayout.findViewById(R.id.autoTvSearchCountry);
                // search.setAdapter(arrayAdapter);
                // search.setThreshold(1);
                search.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        arrayAdapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        });

        disabilityATV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    view.performClick();
                    view.requestFocus();
                }
            }
        });


        // code for selecting education type from list...
        educationATV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(),R.style.MyDialogTheme);
                View customLayout = getLayoutInflater().inflate(R.layout.country_list, null);
                builder.setView(customLayout);

                ListView listView = customLayout.findViewById(R.id.listViewCountry);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(),
                        R.layout.row,
                        getResources().getStringArray(R.array.education_list));
                listView.setAdapter(arrayAdapter);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getWindow().setLayout(600,900);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Get the selected item
                        String selectedValue = arrayAdapter.getItem(position);
                        String options = String.valueOf(R.array.education_list);
                        if (position == parent.getAdapter().getItemId(parent.getAdapter().getCount() - 1)) {
                            otherEduTv.setVisibility(View.VISIBLE);
                            otherEduEt.setVisibility(View.VISIBLE);
                        } else {
                            otherEduTv.setVisibility(View.GONE);
                            otherEduEt.setVisibility(View.GONE);
                            otherEduEt.setText("");

                        }

                        // Set the selected value to the TextView
                        educationATV.setText(selectedValue);

                        // Dismiss the AlertDialog
                        alertDialog.dismiss();
                    }
                });

                search=customLayout.findViewById(R.id.autoTvSearchCountry);
                // search.setAdapter(arrayAdapter);
                // search.setThreshold(1);
                search.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        arrayAdapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });


            }
        });

        educationATV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    view.performClick();
                    view.requestFocus();
                }
            }
        });

        // code for selecting profession from list...
        professionATV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(),R.style.MyDialogTheme);
                View customLayout = getLayoutInflater().inflate(R.layout.country_list, null);
                builder.setView(customLayout);

                ListView listView = customLayout.findViewById(R.id.listViewCountry);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(),
                        R.layout.row,
                        getResources().getStringArray(R.array.profession_list));
                listView.setAdapter(arrayAdapter);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getWindow().setLayout(600,900);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Get the selected item
                        String selectedValue = arrayAdapter.getItem(position);

                        String options = String.valueOf(R.array.profession_list);
                        if (position == parent.getAdapter().getItemId(parent.getAdapter().getCount() - 1)) {
                            otherProfessionTv.setVisibility(View.VISIBLE);
                            otherProfessionEt.setVisibility(View.VISIBLE);
                        } else {
                            otherProfessionTv.setVisibility(View.GONE);
                            otherProfessionEt.setVisibility(View.GONE);
                            otherProfessionEt.setText("");
                        }

                        // Set the selected value to the TextView
                        professionATV.setText(selectedValue);

                        // Dismiss the AlertDialog
                        alertDialog.dismiss();
                    }
                });

                search=customLayout.findViewById(R.id.autoTvSearchCountry);
                // search.setAdapter(arrayAdapter);
                // search.setThreshold(1);
                search.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        arrayAdapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        });

        professionATV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    view.performClick();
                    view.requestFocus();
                }
            }
        });

        // code for selecting sub-profession form list...
        subProfessionATV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(),R.style.MyDialogTheme);
                View customLayout = getLayoutInflater().inflate(R.layout.country_list, null);
                builder.setView(customLayout);

                ListView listView = customLayout.findViewById(R.id.listViewCountry);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(),
                        R.layout.row,
                        getResources().getStringArray(R.array.sub_profession_list));
                listView.setAdapter(arrayAdapter);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getWindow().setLayout(600,900);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Get the selected item
                        String selectedValue = arrayAdapter.getItem(position);

                        String options = String.valueOf(R.array.sub_profession_list);
                        if (position == parent.getAdapter().getItemId(parent.getAdapter().getCount() - 1)) {
                            otherSubProTv.setVisibility(View.VISIBLE);
                            otherSubProEt.setVisibility(View.VISIBLE);
                        } else {
                            otherSubProTv.setVisibility(View.GONE);
                            otherSubProEt.setVisibility(View.GONE);
                            otherSubProEt.setText("");
                        }

                        // Set the selected value to the TextView
                        subProfessionATV.setText(selectedValue);

                        // Dismiss the AlertDialog
                        alertDialog.dismiss();
                    }
                });

                search=customLayout.findViewById(R.id.autoTvSearchCountry);
                // search.setAdapter(arrayAdapter);
                // search.setThreshold(1);
                search.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        arrayAdapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        });

        subProfessionATV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    view.performClick();
                    view.requestFocus();
                }
            }
        });

        // code for selecting occupation from list...
        occupationATV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(),R.style.MyDialogTheme);
                View customLayout = getLayoutInflater().inflate(R.layout.country_list, null);
                builder.setView(customLayout);

                ListView listView = customLayout.findViewById(R.id.listViewCountry);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(),
                        R.layout.row,
                        getResources().getStringArray(R.array.occupation_list));
                listView.setAdapter(arrayAdapter);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getWindow().setLayout(600,900);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Get the selected item
                        String selectedValue = arrayAdapter.getItem(position);

                        String options = String.valueOf(R.array.occupation_list);
                        if (position == parent.getAdapter().getItemId(parent.getAdapter().getCount() - 1)) {
                            otherOccupationTv.setVisibility(View.VISIBLE);
                            otherOccupationEt.setVisibility(View.VISIBLE);
                        } else {
                            otherOccupationTv.setVisibility(View.GONE);
                            otherOccupationEt.setVisibility(View.GONE);
                            otherOccupationEt.setText("");
                        }

                        // Set the selected value to the TextView
                        occupationATV.setText(selectedValue);

                        // Dismiss the AlertDialog
                        alertDialog.dismiss();
                    }
                });

                search=customLayout.findViewById(R.id.autoTvSearchCountry);
                // search.setAdapter(arrayAdapter);
                // search.setThreshold(1);
                search.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        arrayAdapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        });

        occupationATV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    view.performClick();
                    view.requestFocus();
                }
            }
        });



        backForm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new AddressInfoForm());
                // fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                 */
                onBackPressed();

            }
        });

        nextForm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new VerificationForm());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                Log.d("check5Data",""+MartialStatus+"\t"+educationATV.getText().toString()+"\t"+professionATV.getText().toString()+"\n"+subProfessionATV.getText().toString()+"\t"+occupationATV.getText().toString());

                Log.d("checkOtherData2","Other entered data  "+otherEduEt.getText().toString()+"\t"+otherProfessionEt.getText().toString()
                        +"\n"+otherSubProEt.getText().toString()+"\t"+otherOccupationEt.getText().toString());
            }
        });


        return view;
    }
    public void onBackPressed(){
        FragmentManager fm=getActivity().getSupportFragmentManager();
        fm.popBackStack();
    }

    public void getBack(){
        String s="Other";
        String backEdu=educationATV.getText().toString();
        String backPro=professionATV.getText().toString();
        String backSubPro=subProfessionATV.getText().toString();
        String backOcc=occupationATV.getText().toString();

        if(backEdu.equals(s)){
            otherEduTv.setVisibility(View.VISIBLE);
            otherEduEt.setVisibility(View.VISIBLE);
        }
        if(backPro.equals(s)){
            otherProfessionTv.setVisibility(View.VISIBLE);
            otherProfessionEt.setVisibility(View.VISIBLE);
        }
        if(backSubPro.equals(s)){
            otherSubProTv.setVisibility(View.VISIBLE);
            otherSubProEt.setVisibility(View.VISIBLE);
        }
        if(backOcc.equals(s)){
            otherOccupationTv.setVisibility(View.VISIBLE);
            otherOccupationEt.setVisibility(View.VISIBLE);
        }
        Log.d("checkOtherData","Other entered data  "+otherEduEt.getText().toString()+"\t"+otherProfessionEt.getText().toString()
                +"\n"+otherSubProEt.getText().toString()+"\t"+otherOccupationEt.getText().toString());
    }

}
