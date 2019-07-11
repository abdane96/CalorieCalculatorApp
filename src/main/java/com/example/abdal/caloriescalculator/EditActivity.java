package com.example.abdal.caloriescalculator;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdal on 2/3/2018.
 */

public class EditActivity extends AppCompatActivity{

    static EditText heightInchesValue, weightValue, heightValue, age;

    static Spinner weightList, heightList, activityLevelList;
    RadioGroup radioButtons;
    static RadioButton maleButton, femaleButton;
    Button execute, add;
    ImageButton questionButton;
    String nameSelected;
    Button saveEditButton;
    Profile newEditedProfile;
    ArrayList<Profile> profiles = MainActivity.getProfiles();
    int index = 0;
    int wantedIndex = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        radioButtons = (RadioGroup) findViewById(R.id.MaleFemaleButton);
        weightList = (Spinner) findViewById(R.id.WeightList);
        heightList = (Spinner) findViewById(R.id.HeightList);
        activityLevelList = (Spinner) findViewById(R.id.ActivityLevelList);
        heightInchesValue = (EditText) findViewById(R.id.HeightInchesTextField);
        weightValue = (EditText) findViewById(R.id.WeightTextField);
        heightValue = (EditText) findViewById(R.id.HeightTextField);
        age = (EditText) findViewById(R.id.AgeTextField);
        execute = (Button) findViewById(R.id.CalculateButton);
        add = (Button) findViewById(R.id.addProfile);
        questionButton = (ImageButton) findViewById(R.id.questionButton);
        saveEditButton = (Button) findViewById(R.id.saveButton);
        nameSelected = ThirdActivity.getNameSelected();

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
                LayoutInflater li = LayoutInflater.from(EditActivity.this);
                View promptsView = li.inflate(R.layout.activity_dialog, null);

                final AlertDialog dialogNew = new AlertDialog.Builder(EditActivity.this)
                        .setView(promptsView)
                        .create();
                dialogNew.show();
            }
        });

        for(Profile profile : profiles){
            index++;
            if(profile.getName().equals(nameSelected)){
                wantedIndex = index;
                if(profile.getGender().equals("male")){
                    maleButton.setChecked(true);
                }else{
                    femaleButton.setChecked(true);
                }
                if(profile.getWeightUnit().equals("kg")){
                    weightList.setSelection(0);
                }else{
                    weightList.setSelection(1);
                }
                if(profile.getHeightUnit().equals("cm")){
                    weightList.setSelection(0);
                }else{
                    weightList.setSelection(1);
                }
                if(profile.getActivityLevel().equals("Very Light")){
                    activityLevelList.setSelection(0);
                }else if(profile.getActivityLevel().equals("Light")){
                    activityLevelList.setSelection(1);
                }else if(profile.getActivityLevel().equals("Moderate")) {
                    activityLevelList.setSelection(2);
                }else if(profile.getActivityLevel().equals("Heavy")) {
                    activityLevelList.setSelection(3);
                }
                weightValue.setText(Double.toString(profile.getWeight()));
                heightValue.setText(Double.toString(profile.getHeight()));
                age.setText(Integer.toString(profile.getAge()));
                heightInchesValue.setText(Double.toString(profile.getHeightInches()));
            }
        }
        saveEditButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int radioButtonID = radioButtons.getCheckedRadioButtonId();
                View radioButton = radioButtons.findViewById(radioButtonID);
                int idx = radioButtons.indexOfChild(radioButton);
                RadioButton r = (RadioButton)  radioButtons.getChildAt(idx);

                newEditedProfile = new Profile(nameSelected,r.getText().toString(),Double.parseDouble(weightValue.getText().toString()),Double.parseDouble(heightValue.getText().toString()),weightList.getSelectedItem().toString(),heightList.getSelectedItem().toString(),Double.parseDouble(heightInchesValue.getText().toString()),Integer.parseInt(age.getText().toString()),activityLevelList.getSelectedItem().toString());
                profiles.set(wantedIndex-1, newEditedProfile);

                CharSequence text = "Successfully edited profile " + nameSelected;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(EditActivity.this, text, duration);
                toast.show();
            }
        });
    }
}
