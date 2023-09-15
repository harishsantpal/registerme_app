package com.example.registrationsetup;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
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
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.Locale;


public class AddressInfoForm extends Fragment {

    View view;


    // permanent address information components
    EditText addressPermanent,pinPermanent;
    AutoCompleteTextView aTvCountry,aTvStates,aTvDistrict,aTvTalukas,aTvVillageCity;

    // current address information components
    EditText addressCurrent,pinCurrent;
    AutoCompleteTextView aTvCountryCurrent,aTvStatesCurrent,aTvDistrictCurrent,aTvTalukasCurrent,aTvVillageCityCurrent;

    Button backForm2,nextForm2,copyAddress;

    // auto complete text view for search in alert dialog
    AutoCompleteTextView search;


    @Override
    public void onResume() {
        super.onResume();
        // Update the current step in the progress bar
        int currentStep = 2; // Replace this with the appropriate current step for this fragment
        ((MainActivity) requireActivity()).updateStepProgressBar(currentStep);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_address_info_form, container, false);


        MainActivity mainActivity=(MainActivity) getActivity();
        mainActivity.updatePageTitle("Address Information");
        mainActivity.updatePageCounter("Step 2-4");

        // permanent address information components id initialization
        addressPermanent=view.findViewById(R.id.etAddress);
        pinPermanent=view.findViewById(R.id.etPinCode);

            // dropdown options id initialization
        aTvCountry=view.findViewById(R.id.autoTvCountry);
        aTvStates=view.findViewById(R.id.atuoTvState);
        aTvDistrict=view.findViewById(R.id.autoTvDistrict);
        aTvTalukas=view.findViewById(R.id.autoTvTaluka);
        aTvVillageCity=view.findViewById(R.id.autoTvVillageCity);


        // current address information components id initialization
        addressCurrent=view.findViewById(R.id.etAddress2);

        aTvCountryCurrent=view.findViewById(R.id.autoTvCountry2);
        aTvStatesCurrent=view.findViewById(R.id.atuoTvState2);
        aTvDistrictCurrent=view.findViewById(R.id.autoTvDistrict2);
        aTvTalukasCurrent=view.findViewById(R.id.autoTvTaluka2);
        aTvVillageCityCurrent=view.findViewById(R.id.autoTvVillage2);


        pinCurrent=view.findViewById(R.id.etPinCode2);

        backForm2=view.findViewById(R.id.btnForm2Back);
        nextForm2=view.findViewById(R.id.btnForm2Next);
        copyAddress=view.findViewById(R.id.btnForm2CopyAbove);


        search=view.findViewById(R.id.autoTvSearchCountry);


        // code for selecting Country from list..., on Select Country dropdown..
        aTvCountry.setOnClickListener(new View.OnClickListener() {

            //AutoCompleteTextView textView=view.findViewById(R.id.autoTvSearchCountry);

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext(),R.style.MyDialogTheme);
                View customLayout = getLayoutInflater().inflate(R.layout.country_list, null);
                search=customLayout.findViewById(R.id.autoTvSearchCountry);
                builder.setView(customLayout);

                ListView listView = customLayout.findViewById(R.id.listViewCountry);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(),
                        R.layout.row,
                        getResources().getStringArray(R.array.countryList));
                listView.setAdapter(arrayAdapter);
                Log.d("check9",""+listView);



                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getWindow().setLayout(600,900);



                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Get the selected item
                        String selectedValue = arrayAdapter.getItem(position);

                        // Set the selected value to the TextView
                        aTvCountry.setText(selectedValue.toUpperCase(Locale.ROOT));

                        // Dismiss the AlertDialog
                        alertDialog.dismiss();
                    }
                });


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

        // to get focus on single click
        aTvCountry.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    view.performClick();
                    view.requestFocus();
                }
            }
        });

        // code for selecting State from list..., on Select state dropdown..
        aTvStates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext(),R.style.MyDialogTheme);
                View customLayout = getLayoutInflater().inflate(R.layout.country_list, null);
                search=customLayout.findViewById(R.id.autoTvSearchCountry);
                builder.setView(customLayout);

                ListView listView = customLayout.findViewById(R.id.listViewCountry);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(),
                        R.layout.row,
                        getResources().getStringArray(R.array.states_list));
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
                        aTvStates.setText(selectedValue);

                        // Dismiss the AlertDialog
                        alertDialog.dismiss();
                    }
                });

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

        // to get focus on single click
        aTvStates.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    view.performClick();
                    view.requestFocus();
                }
            }
        });

        // code for selecting district from list..., on Select district dropdown..
        aTvDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext(),R.style.MyDialogTheme);
                View customLayout = getLayoutInflater().inflate(R.layout.country_list, null);
                search=customLayout.findViewById(R.id.autoTvSearchCountry);
                builder.setView(customLayout);

                ListView listView = customLayout.findViewById(R.id.listViewCountry);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(),
                        R.layout.row,
                        getResources().getStringArray(R.array.district_list));
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
                        aTvDistrict.setText(selectedValue);

                        // Dismiss the AlertDialog
                        alertDialog.dismiss();
                    }
                });

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

        // to get focus on single click
        aTvDistrict.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    view.performClick();
                    view.requestFocus();
                }
            }
        });

        // code for selecting talukas from list..., on Select talukas dropdown..
        aTvTalukas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext(),R.style.MyDialogTheme);
                View customLayout = getLayoutInflater().inflate(R.layout.country_list, null);
                search=customLayout.findViewById(R.id.autoTvSearchCountry);
                builder.setView(customLayout);

                ListView listView = customLayout.findViewById(R.id.listViewCountry);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(),
                        R.layout.row,
                        getResources().getStringArray(R.array.talukas_list));
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
                        aTvTalukas.setText(selectedValue);

                        // Dismiss the AlertDialog
                        alertDialog.dismiss();
                  }
                 });

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

        // to get focus on single click
        aTvTalukas.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    view.performClick();
                    view.requestFocus();
                }
            }
        });

        // code for selecting village/city from list..., on Select village/city dropdown..
        aTvVillageCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext(),R.style.MyDialogTheme);
                View customLayout = getLayoutInflater().inflate(R.layout.country_list, null);
                search=customLayout.findViewById(R.id.autoTvSearchCountry);
                builder.setView(customLayout);

                ListView listView = customLayout.findViewById(R.id.listViewCountry);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(),
                        R.layout.row,
                        getResources().getStringArray(R.array.villages_city));
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
                        aTvVillageCity.setText(selectedValue);

                        // Dismiss the AlertDialog
                        alertDialog.dismiss();
                    }
                });

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

        // to get focus on single click
        aTvVillageCity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    view.performClick();
                    view.requestFocus();
                }
            }
        });

        /* ===================================================================================================================== */

        // dropdown code for current address information....

        // code for selecting State for current address from list..., on Select state dropdown..
        aTvCountryCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext(),R.style.MyDialogTheme);
                View customLayout = getLayoutInflater().inflate(R.layout.country_list, null);
                search=customLayout.findViewById(R.id.autoTvSearchCountry);
                builder.setView(customLayout);

                ListView listView = customLayout.findViewById(R.id.listViewCountry);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(),
                        R.layout.row,
                        getResources().getStringArray(R.array.countryList));
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
                        aTvCountryCurrent.setText(selectedValue);

                        // Dismiss the AlertDialog
                        alertDialog.dismiss();
                    }
                });

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

        // to get focus on single click
        aTvCountryCurrent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    view.performClick();
                    view.requestFocus();
                }
            }
        });

        // code for selecting State for current address from list..., on Select state dropdown..
        aTvStatesCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext(),R.style.MyDialogTheme);
                View customLayout = getLayoutInflater().inflate(R.layout.country_list, null);
                search=customLayout.findViewById(R.id.autoTvSearchCountry);
                builder.setView(customLayout);

                ListView listView = customLayout.findViewById(R.id.listViewCountry);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(),
                        R.layout.row,
                        getResources().getStringArray(R.array.states_list));
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
                        aTvStatesCurrent.setText(selectedValue);

                        // Dismiss the AlertDialog
                        alertDialog.dismiss();
                    }
                });

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

        // to get focus on single click
        aTvStatesCurrent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    view.performClick();
                    view.requestFocus();
                }
            }
        });

        // code for selecting district for current address information from list..., on Select district dropdown..
        aTvDistrictCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext(),R.style.MyDialogTheme);
                View customLayout = getLayoutInflater().inflate(R.layout.country_list, null);
                search=customLayout.findViewById(R.id.autoTvSearchCountry);
                builder.setView(customLayout);

                ListView listView = customLayout.findViewById(R.id.listViewCountry);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(),
                        R.layout.row,
                        getResources().getStringArray(R.array.district_list));
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
                        aTvDistrictCurrent.setText(selectedValue);

                        // Dismiss the AlertDialog
                        alertDialog.dismiss();
                    }
                });

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

        // to get focus on single click
        aTvDistrictCurrent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    view.performClick();
                    view.requestFocus();
                }
            }
        });

        // code for selecting talukas for current address information from list..., on Select talukas dropdown..
        aTvTalukasCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext(),R.style.MyDialogTheme);
                View customLayout = getLayoutInflater().inflate(R.layout.country_list, null);
                search=customLayout.findViewById(R.id.autoTvSearchCountry);
                builder.setView(customLayout);

                ListView listView = customLayout.findViewById(R.id.listViewCountry);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(),
                        R.layout.row,
                        getResources().getStringArray(R.array.talukas_list));
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
                        aTvTalukasCurrent.setText(selectedValue);

                        // Dismiss the AlertDialog
                        alertDialog.dismiss();
                    }
                });

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

        // to get focus on single click
        aTvTalukasCurrent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    view.performClick();
                    view.requestFocus();
                }
            }
        });

        // code for selecting village/city for current address information from list..., on Select village/city dropdown..
        aTvVillageCityCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext(),R.style.MyDialogTheme);
                View customLayout = getLayoutInflater().inflate(R.layout.country_list, null);
                search=customLayout.findViewById(R.id.autoTvSearchCountry);
                builder.setView(customLayout);

                ListView listView = customLayout.findViewById(R.id.listViewCountry);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(),
                        R.layout.row,
                        getResources().getStringArray(R.array.villages_city));
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
                        aTvVillageCityCurrent.setText(selectedValue);

                        // Dismiss the AlertDialog
                        alertDialog.dismiss();
                    }
                });

                //search=customLayout.findViewById(R.id.autoTvSearchCountry);
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

        // to get focus on single click
        aTvVillageCityCurrent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    view.performClick();
                    view.requestFocus();
                }
            }
        });





        copyAddress.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                String pinCodePer=pinPermanent.getText().toString();
                if(pinCodePer.isEmpty()){
                    pinPermanent.setError("Enter PIN code");
                    pinPermanent.requestFocus();
                } else{
                copyPermanentAddress();
                Snackbar snackbar=Snackbar.make(view,"Address copied",Snackbar.LENGTH_SHORT);
                Snackbar.SnackbarLayout snackbarLayout=(Snackbar.SnackbarLayout)snackbar.getView();
                FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams) snackbarLayout.getLayoutParams();
                layoutParams.setMargins(20,0,20,20);
                snackbarLayout.setLayoutParams(layoutParams);


                snackbar.show();
                }
            }
        });

        backForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new BasicInfoForm());
                fragmentTransaction.commit();
                
               */

                FragmentManager fm=getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });

        nextForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pinPerTXT=pinPermanent.getText().toString();
                String pinCurrTXT=pinCurrent.getText().toString();

                if(pinPerTXT.isEmpty()){
                    pinPermanent.setError("Enter PIN code");
                    pinPermanent.requestFocus();
                }
                else if(pinCurrTXT.isEmpty()){
                    pinCurrent.setError("Enter PIN code");
                    pinCurrent.requestFocus();
                }else {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, new PersonalInfoForm());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                    /// log data for Permanent address
                    String pAddress=addressPermanent.getText().toString();
                    String pCountry=aTvCountry.getText().toString();
                    String pState=aTvStates.getText().toString();
                    String pDistrict=aTvDistrict.getText().toString();
                    String pTaluka=aTvTalukas.getText().toString();
                    String pVillageCity=aTvVillageCity.getText().toString();
                    String pPin=pinPermanent.getText().toString();

                    String cAddress=addressCurrent.getText().toString();
                    String cCountry=aTvCountryCurrent.getText().toString();
                    String cState=aTvStatesCurrent.getText().toString();
                    String cDistrict=aTvDistrictCurrent.getText().toString();
                    String cTaluka=aTvTalukasCurrent.getText().toString();
                    String cVillageCity=aTvVillageCityCurrent.getText().toString();
                    String cPin=pinCurrent.getText().toString();

                    Log.d("checkPerAddress",""+pAddress+"\t"+pCountry+"\t"+pState+"\t"+pDistrict+"\t"+pTaluka+"\t"+pVillageCity+"\t"+pPin);
                    Log.d("checkCurrentAddress",""+cAddress+"\t"+cCountry+"\t"+cState+"\t"+cDistrict+"\t"+cTaluka+"\t"+cVillageCity+"\t"+cPin);
                }
            }
        });

        pinCurrent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    pinCurrent.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });


        return view;
    }




    private void copyPermanentAddress() {
        String pAddress=addressPermanent.getText().toString();

        String pCountry=aTvCountry.getText().toString();
        String pState=aTvStates.getText().toString();
        String pDistrict=aTvDistrict.getText().toString();
        String pTaluka=aTvTalukas.getText().toString();
        String pVillageCity=aTvVillageCity.getText().toString();

        String pPin=pinPermanent.getText().toString();

        addressCurrent.setText(pAddress);

        aTvCountryCurrent.setText(pCountry);
        aTvStatesCurrent.setText(pState);
        aTvDistrictCurrent.setText(pDistrict);
        aTvTalukasCurrent.setText(pTaluka);
        aTvVillageCityCurrent.setText(pVillageCity);

        pinCurrent.setText(pPin);

        Log.d("checkAddress",""+pAddress+"\t"+pCountry+"\t"+pState+"\t"+pDistrict+"\t"+pTaluka+"\t"+pVillageCity+"\t"+pPin);
    }
}