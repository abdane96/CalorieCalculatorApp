package com.example.abdal.caloriescalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String SHARED_PREFS_FILE = "MY_SHARED_PREF";

    static ArrayList<Profile> profiles;// = new ArrayList<>();

    static EditText heightInchesValue, weightValue, weightValue2, heightValue, age;

    static Spinner weightList, weightList2, heightList, activityLevelList, dateList;
    RadioGroup radioButtons;
    static RadioButton maleButton, femaleButton;
    Button execute, add;
    ImageButton questionButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();

        radioButtons = (RadioGroup) findViewById(R.id.MaleFemaleButton);
        weightList = (Spinner) findViewById(R.id.WeightList);
        weightList2 = (Spinner) findViewById(R.id.weightList);
        heightList = (Spinner) findViewById(R.id.HeightList);
        activityLevelList = (Spinner) findViewById(R.id.ActivityLevelList);
        dateList = (Spinner) findViewById(R.id.dateList);
        heightInchesValue = (EditText) findViewById(R.id.HeightInchesTextField);
        weightValue = (EditText) findViewById(R.id.WeightTextField);
        weightValue2 = (EditText) findViewById(R.id.weightToLoseTextField);
        heightValue = (EditText) findViewById(R.id.HeightTextField);
        age = (EditText) findViewById(R.id.AgeTextField);
        execute = (Button) findViewById(R.id.CalculateButton);
        add = (Button) findViewById(R.id.addProfile);
        questionButton = (ImageButton) findViewById(R.id.questionButton);

        maleButton = new RadioButton(this);
        femaleButton = new RadioButton(this);
        maleButton.setText("Male");
        maleButton.setPadding(0, 0, 100, 0);
        femaleButton.setText("Female");
        radioButtons.addView(maleButton);
        radioButtons.addView(femaleButton);

        final List<String> weights = new ArrayList<String>();
        weights.add("kg");
        weights.add("lbs");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, weights);
        weightList.setAdapter(arrayAdapter);
        weightList2.setAdapter(arrayAdapter);

        final List<String> heights = new ArrayList<String>();
        heights.add("cm");
        heights.add("ft");
        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, heights);
        heightList.setAdapter(arrayAdapter2);

        final List<String> activityLevels = new ArrayList<String>();
        activityLevels.add("Very Light");
        activityLevels.add("Light");
        activityLevels.add("Moderate");
        activityLevels.add("Heavy");
        final ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this, R.layout.spinner_item, activityLevels);
        activityLevelList.setAdapter(arrayAdapter3);

        final List<String> dates = new ArrayList<String>();
        dates.add("1 Week");
        dates.add("2 Weeks");
        dates.add("3 Weeks");
        dates.add("1 Month");
        dates.add("2 Months");
        dates.add("3 Months");
        dates.add("4 Months");
        dates.add("5 Months");
        dates.add("6 Months");
        dates.add("7 Months");
        dates.add("8 Months");
        dates.add("9 Months");
        dates.add("10 Months");
        dates.add("11 Months");
        dates.add("1 Year");
        final ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<String>(this, R.layout.spinner_item, dates);
        dateList.setAdapter(arrayAdapter4);

        weightList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (weightList.getSelectedItem().toString().equals("kg")) {
                    weightValue.setHint("Kilograms");
                } else {
                    weightValue.setHint("Pounds");
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                ;
            }
        });

        weightList2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (weightList2.getSelectedItem().toString().equals("kg")) {
                    weightValue2.setHint("Kilograms");
                } else {
                    weightValue2.setHint("Pounds");
                }

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                ;
            }
        });

        heightList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (heightList.getSelectedItem().toString().equals("ft")) {
                    heightValue.setHint("Feet");
                    heightInchesValue.setVisibility(View.VISIBLE);
                } else {
                    heightValue.setHint("Centimetres");
                    heightInchesValue.setVisibility(View.GONE);
                }

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                ;
            }
        });

        questionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                View promptsView = li.inflate(R.layout.activity_dialog, null);

                final AlertDialog dialogNew = new AlertDialog.Builder(MainActivity.this)
                        .setView(promptsView)
                        .create();
                dialogNew.show();
            }
        });

        execute.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this,
                        SecondActivity.class);
                startActivity(myIntent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                View promptsView = li.inflate(R.layout.prompts, null);

                final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setView(promptsView)
                        .setPositiveButton(R.string.add, null)
                        .setNegativeButton(android.R.string.cancel, null)
                        .create();

                final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);

                final TextView promptInfo = (TextView) promptsView.findViewById(R.id.promptInfo);
                final TextView promptError = (TextView) promptsView.findViewById(R.id.promptError);
                final TextView promptError2 = (TextView) promptsView.findViewById(R.id.promptError2);

                promptError.setVisibility(View.GONE);
                promptError2.setVisibility(View.GONE);

                dialog.setOnShowListener(new DialogInterface.OnShowListener() {

                    @Override
                    public void onShow(DialogInterface dialogInterface) {

                        Button add2 = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);

                        add2.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                try {
                                    if (userInput.getText().toString().isEmpty()) {
                                        promptInfo.setVisibility(View.GONE);
                                        promptError.setVisibility(View.GONE);
                                        promptError2.setVisibility(View.VISIBLE);
                                    } else {
                                        if (getGender() == null) {
                                            promptInfo.setVisibility(View.GONE);
                                            promptError.setVisibility(View.VISIBLE);
                                            promptError2.setVisibility(View.GONE);
                                        }
                                        if (!heightInchesValue.getText().toString().isEmpty()) {
                                            profiles.add(new Profile(userInput.getText().toString(), getGender(), getWeight(), getHeight(), getWeightUnit(), getHeightUnit(), getHeightInches(), getAge(), getActivityLevel()));
                                            dialog.dismiss();

                                            CharSequence text = "Successfully added profile " + userInput.getText().toString();
                                            int duration = Toast.LENGTH_SHORT;

                                            Toast toast = Toast.makeText(MainActivity.this, text, duration);
                                            toast.show();
                                        } else {
                                            profiles.add(new Profile(userInput.getText().toString(), getGender(), getWeight(), getHeight(), getWeightUnit(), getHeightUnit(), 0.0, getAge(), getActivityLevel()));
                                            dialog.dismiss();

                                            CharSequence text = "Successfully added profile " + userInput.getText().toString();
                                            int duration = Toast.LENGTH_SHORT;

                                            Toast toast = Toast.makeText(MainActivity.this, text, duration);
                                            toast.show();
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    promptInfo.setVisibility(View.GONE);
                                    promptError.setVisibility(View.VISIBLE);
                                    promptError2.setVisibility(View.GONE);
                                }
                            }
                        });
                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    protected void onStop(){
        saveData();
        super.onStop();
    }

    protected void onDestroy(){
        saveData();
        super.onDestroy();
    }

    public void saveData(){
        SharedPreferences sp = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(profiles);
        editor.putString("profiles",json);
        editor.apply();
    }

    public void loadData(){

        SharedPreferences sp = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("profiles",null);
        Type type = new TypeToken<ArrayList<Profile>>(){}.getType();
        profiles = gson.fromJson(json,type);

        if(profiles == null){
            profiles = new ArrayList<>();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profiles:
                Intent myIntent = new Intent(MainActivity.this,
                        ThirdActivity.class);
                startActivity(myIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static String getGender() {
        if (maleButton.isChecked()) {
            return "male";
        }
        if (femaleButton.isChecked()) {
            return "female";
        }
        return null;
    }

    public static String getWeightUnit() {
        return weightList.getSelectedItem().toString();
    }

    public static String getHeightUnit() {
        return heightList.getSelectedItem().toString();
    }

    public static String getWeightToLoseUnit() {
        return weightList2.getSelectedItem().toString();
    }

    public static int getAge() {
        return Integer.parseInt(age.getText().toString());
    }

    public static String getActivityLevel() {
        return activityLevelList.getSelectedItem().toString();
    }

    public static double getWeight() {
        return Double.parseDouble(weightValue.getText().toString());
    }

    public static double getHeight() {
        return Double.parseDouble(heightValue.getText().toString());
    }

    public static double getWeightToLose() {
        return Double.parseDouble(weightValue2.getText().toString());
    }

    public static double getHeightInches() {
        return Double.parseDouble(heightInchesValue.getText().toString());
    }

    public static String getTime() {
        return dateList.getSelectedItem().toString();
    }

    public static ArrayList<Profile> getProfiles() {
        return profiles;
    }

    public static void setProfiles(ArrayList<Profile> newProfiles) {
        profiles = newProfiles;
    }

    public static void setHeightInchesValue(Double heightInchesValue) {
        MainActivity.heightInchesValue.setText(heightInchesValue.toString());
    }

    public static void setWeightValue(Double weightValue) {
        MainActivity.weightValue.setText(weightValue.toString());
    }

    public static void setHeightValue(Double heightValue) {
        MainActivity.heightValue.setText(heightValue.toString());
    }

    public static void setAge(int age) {
        MainActivity.age.setText(age + "");
    }

    public static void setWeightList(String weightList) {
        if (weightList.equals("kg")) {
            MainActivity.weightList.setSelection(0);
        } else {
            MainActivity.weightList.setSelection(1);
        }
    }

    public static void setHeightList(String heightList) {
        if (heightList.equals("cm")) {
            MainActivity.heightList.setSelection(0);
        } else {
            MainActivity.heightList.setSelection(1);
        }
    }

    public static void setActivityLevelList(String x) {
        if (x.equals("Very Light")) {
            MainActivity.activityLevelList.setSelection(0);
        } else if (x.equals("Light")) {
            MainActivity.activityLevelList.setSelection(1);
        } else if (x.equals("Moderate")) {
            MainActivity.activityLevelList.setSelection(2);
        } else {
            MainActivity.activityLevelList.setSelection(3);
        }
    }

    public static void setGender(String Gender) {
        if (Gender.equals("male")) {
            maleButton.setChecked(true);
        } else {
            femaleButton.setChecked(true);
        }
    }
}