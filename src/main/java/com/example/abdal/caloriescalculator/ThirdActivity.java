package com.example.abdal.caloriescalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by abdal on 12/29/2017.
 */

public class ThirdActivity extends AppCompatActivity {

    ArrayList<Profile> profiles = MainActivity.getProfiles();
    static String NameSelected = "";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        //loadData();

        final TextView noProfiles = (TextView) findViewById(R.id.no_profiles);

        final ListView profileList = (ListView) findViewById(R.id.profileList);
        final ArrayList<String> profileNames = new ArrayList<>();

        for(int i = 0; i<profiles.size(); i++){
            profileNames.add(profiles.get(i).getName());
        }

        profileList.setVisibility(View.GONE);
        noProfiles.setVisibility(View.GONE);

        final ArrayAdapter<String> arrayAdapter;

        if(profiles.size()>0) {
            profileList.setVisibility(View.VISIBLE);
            arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, profileNames);
            profileList.setAdapter(arrayAdapter);
        }else{
            noProfiles.setVisibility(View.VISIBLE);
        }

        profileList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String selectedFromList = (profileList.getItemAtPosition(position).toString());
                for(Profile profile : profiles){
                    if (profile.getName().equals(selectedFromList)) {
                        MainActivity.setGender(profile.getGender());
                        MainActivity.setWeightValue(profile.getWeight());
                        MainActivity.setWeightList(profile.getWeightUnit());
                        MainActivity.setHeightValue(profile.getHeight());
                        MainActivity.setHeightList(profile.getHeightUnit());
                        MainActivity.setHeightInchesValue(profile.getHeightInches());
                        MainActivity.setAge(profile.getAge());
                        MainActivity.setActivityLevelList(profile.getActivityLevel());
                        break;
                    }
                }
                finish();
            }
        });

        profileList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {

                final String selectedFromList = (profileList.getItemAtPosition(position).toString());

                LayoutInflater li = LayoutInflater.from(ThirdActivity.this);
                View promptsView = li.inflate(R.layout.prompts_for_delete_rename_edit, null);
                final AlertDialog dialog = new AlertDialog.Builder(ThirdActivity.this)
                        .setView(promptsView)
                        .create();

                final Button delete = (Button) promptsView.findViewById(R.id.deleteButton);
                final Button rename = (Button) promptsView.findViewById(R.id.renameButton);
                final Button edit = (Button) promptsView.findViewById(R.id.editButton);

                dialog.setOnShowListener(new DialogInterface.OnShowListener() {

                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        delete.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                LayoutInflater li2 = LayoutInflater.from(ThirdActivity.this);
                                View promptsView2 = li2.inflate(R.layout.delete_dialog_confirmation, null);

                                final AlertDialog dialog3 = new AlertDialog.Builder(ThirdActivity.this)
                                        .setView(promptsView2)
                                        .setPositiveButton(R.string.yes, null)
                                        .setNegativeButton(R.string.no, null)
                                        .create();

                                dialog3.setOnShowListener(new DialogInterface.OnShowListener() {

                                    @Override
                                    public void onShow(DialogInterface dialogInterface) {

                                        Button yes = ((AlertDialog) dialog3).getButton(AlertDialog.BUTTON_POSITIVE);

                                        yes.setOnClickListener(new View.OnClickListener() {

                                            @Override
                                            public void onClick(View view) {
                                                dialog3.dismiss();
                                                for(int i = 0;i<profileNames.size(); i++){
                                                    if(profileNames.get(i).equals(selectedFromList)){
                                                        profileNames.remove(i);
                                                        profiles.remove(i);
                                                        MainActivity.setProfiles(profiles);
                                                        profileList.setAdapter(new ArrayAdapter<String>(ThirdActivity.this, R.layout.spinner_item, profileNames));
                                                        if(profileNames.size()<=0) {
                                                            noProfiles.setVisibility(View.VISIBLE);
                                                        }
                                                        break;
                                                    }
                                                }
                                            }
                                        });
                                    }
                                });
                                dialog3.show();
                            }
                        });

                        rename.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                LayoutInflater li = LayoutInflater.from(ThirdActivity.this);
                                final View promptsView = li.inflate(R.layout.prompt_for_rename, null);
                                final AlertDialog dialog2 = new AlertDialog.Builder(ThirdActivity.this)
                                        .setView(promptsView)
                                        .create();

                                final Button renameButton = (Button) promptsView.findViewById(R.id.renameButton);
                                final EditText renameEditField = (EditText) promptsView.findViewById(R.id.renameInputField);

                                dialog2.setOnShowListener(new DialogInterface.OnShowListener() {

                                    @Override
                                    public void onShow(DialogInterface dialogInterface) {
                                        renameButton.setOnClickListener(new View.OnClickListener() {

                                            @Override
                                            public void onClick(View view) {
                                                for (Profile profile : profiles) {
                                                    if (profile.getName().equals(selectedFromList)) {
                                                        profile.setName(renameEditField.getText().toString());
                                                        break;
                                                    }
                                                }
                                                MainActivity.setProfiles(profiles);
                                                for(int i = 0;i<profileNames.size(); i++){
                                                    if(profileNames.get(i).equals(selectedFromList)){
                                                        profileNames.set(i,renameEditField.getText().toString());
                                                        profileList.setAdapter(new ArrayAdapter<String>(ThirdActivity.this, R.layout.spinner_item, profileNames));
                                                        break;
                                                    }
                                                }
                                                dialog2.dismiss();
                                            }
                                        });
                                    }
                                });
                                dialog2.show();
                            }
                        });
                        edit.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                NameSelected = selectedFromList;
                                Intent myIntent = new Intent(ThirdActivity.this,
                                        EditActivity.class);
                                startActivity(myIntent);
                            }
                        });
                    }
                });
                dialog.show();
                return true;
            }
        });

    }
    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        // perform your action here

    }*/
    public static String getNameSelected(){
        return NameSelected;
    }

    @Override
    protected void onStop(){
        saveData();
        super.onStop();
    }

    @Override
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

    /*public void loadData(){

        SharedPreferences sp = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("profiles",null);
        Type type = new TypeToken<ArrayList<Profile>>(){}.getType();
        profiles = gson.fromJson(json,type);

        if(profiles == null){
            profiles = new ArrayList<>();
        }
    }*/

}
