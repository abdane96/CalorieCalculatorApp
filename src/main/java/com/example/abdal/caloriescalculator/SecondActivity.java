package com.example.abdal.caloriescalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdal on 12/28/2017.
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        try {
            double BMR = 0.0;
            int intWeeks = 0;
            double weightToLose = MainActivity.getWeightToLose();
            String numberOfWeeks = MainActivity.getTime();
            double finalValue = 0.0;
            double scale = 0.0;
            //double newBMR = 0.0; //BMR-(MainActivity.getWeight()-weightLostPerWeek);

            if (MainActivity.getGender().equals("male")) {
                if (MainActivity.getHeightUnit().equals("cm") && MainActivity.getWeightUnit().equals("kg")) {
                    BMR = 66 + (13.7 * MainActivity.getWeight()) + (5 * MainActivity.getHeight()) - (6.8 * MainActivity.getAge());
                    if (MainActivity.getActivityLevel().equals("Very Light")) {
                        BMR = BMR * 1.2;
                    } else if (MainActivity.getActivityLevel().equals("Light")) {
                        BMR = BMR * 1.375;
                    } else if (MainActivity.getActivityLevel().equals("Moderate")) {
                        BMR = BMR * 1.55;
                    } else if (MainActivity.getActivityLevel().equals("Heavy")) {
                        BMR = BMR * 1.725;
                    }
                }
                if (MainActivity.getHeightUnit().equals("ft") && MainActivity.getWeightUnit().equals("lbs")) {
                    BMR = 66 + (6.23 * MainActivity.getWeight()) + (12.7 * (MainActivity.getHeight() * 12 + MainActivity.getHeightInches())) - (6.8 * MainActivity.getAge());
                    if (MainActivity.getActivityLevel().equals("Very Light")) {
                        BMR = BMR * 1.2;
                    } else if (MainActivity.getActivityLevel().equals("Light")) {
                        BMR = BMR * 1.375;
                    } else if (MainActivity.getActivityLevel().equals("Moderate")) {
                        BMR = BMR * 1.55;
                    } else if (MainActivity.getActivityLevel().equals("Heavy")) {
                        BMR = BMR * 1.725;
                    }
                }
            }
            if (MainActivity.getGender().equals("female")) {
                if (MainActivity.getHeightUnit().equals("cm") && MainActivity.getWeightUnit().equals("kg")) {
                    BMR = 655 + (9.6 * MainActivity.getWeight()) + (1.8 * MainActivity.getHeight()) - (4.7 * MainActivity.getAge());
                    if (MainActivity.getActivityLevel().equals("Very Light")) {
                        BMR = BMR * 1.2;
                    } else if (MainActivity.getActivityLevel().equals("Light")) {
                        BMR = BMR * 1.375;
                    } else if (MainActivity.getActivityLevel().equals("Moderate")) {
                        BMR = BMR * 1.55;
                    } else if (MainActivity.getActivityLevel().equals("Heavy")) {
                        BMR = BMR * 1.725;
                    }
                }
                if (MainActivity.getHeightUnit().equals("ft") && MainActivity.getWeightUnit().equals("lbs")) {
                    BMR = 655 + (4.35 * MainActivity.getWeight()) + (4.7 * (MainActivity.getHeight() * 12 + MainActivity.getHeightInches())) - (4.7 * MainActivity.getAge());
                    if (MainActivity.getActivityLevel().equals("Very Light")) {
                        BMR = BMR * 1.2;
                    } else if (MainActivity.getActivityLevel().equals("Light")) {
                        BMR = BMR * 1.375;
                    } else if (MainActivity.getActivityLevel().equals("Moderate")) {
                        BMR = BMR * 1.55;
                    } else if (MainActivity.getActivityLevel().equals("Heavy")) {
                        BMR = BMR * 1.725;
                    }
                }
            }

            if (numberOfWeeks.contains("Week")) {
                intWeeks = Integer.parseInt(numberOfWeeks.substring(0, 2).replaceAll(" ", ""));
            }
            if (numberOfWeeks.contains("Month")) {
                intWeeks = Integer.parseInt(numberOfWeeks.substring(0, 2).replaceAll(" ", "")) * 4;
                if (intWeeks == 16) {
                    intWeeks = 17;
                } else if (intWeeks == 20) {
                    intWeeks = 21;
                } else if (intWeeks == 24) {
                    intWeeks = 25;
                } else if (intWeeks == 28) {
                    intWeeks = 30;
                } else if (intWeeks == 32) {
                    intWeeks = 35;
                } else if (intWeeks == 36) {
                    intWeeks = 40;
                } else if (intWeeks == 40) {
                    intWeeks = 44;
                } else if (intWeeks == 44) {
                    intWeeks = 48;
                }
            }
            if (numberOfWeeks.contains("Year")) {
                intWeeks = 52;
            }

            if (MainActivity.getWeightToLoseUnit().equals("kg")) {
                finalValue = BMR - (7700 * weightToLose / intWeeks / 7);
            }
            if (MainActivity.getWeightToLoseUnit().equals("lbs")) {
                finalValue = BMR - (3500 * weightToLose / intWeeks / 7);
            }

            Double weightLostPerWeek = 0.0;
            if (MainActivity.getWeightToLoseUnit().equals("kg")) {
                weightLostPerWeek = ((BMR * 7) - (finalValue * 7)) / 7700;
            }
            if (MainActivity.getWeightToLoseUnit().equals("lbs")) {
                weightLostPerWeek = ((BMR * 7) - (finalValue * 7)) / 3500;
            }

            if (MainActivity.getGender().equals("male")) {
                if (MainActivity.getHeightUnit().equals("cm") && MainActivity.getWeightUnit().equals("kg")) {
                    scale = 66 + (13.7 * (MainActivity.getWeight() - weightLostPerWeek)) + (5 * MainActivity.getHeight()) - (6.8 * MainActivity.getAge());
                    if (MainActivity.getActivityLevel().equals("Very Light")) {
                        scale *= 1.2;
                        scale = BMR - scale;
                    } else if (MainActivity.getActivityLevel().equals("Light")) {
                        scale *= 1.375;
                        scale = BMR - scale;
                    } else if (MainActivity.getActivityLevel().equals("Moderate")) {
                        scale *= 1.55;
                        scale = BMR - scale;
                    } else if (MainActivity.getActivityLevel().equals("Heavy")) {
                        scale *= 1.725;
                        scale = BMR - scale;
                    }
                }
                if (MainActivity.getHeightUnit().equals("ft") && MainActivity.getWeightUnit().equals("lbs")) {
                    scale = 66 + (6.23 * (MainActivity.getWeight() - weightLostPerWeek)) + (12.7 * (MainActivity.getHeight() * 12 + MainActivity.getHeightInches())) - (6.8 * MainActivity.getAge());
                    if (MainActivity.getActivityLevel().equals("Very Light")) {
                        scale *= 1.2;
                        scale = BMR - scale;
                    } else if (MainActivity.getActivityLevel().equals("Light")) {
                        scale *= 1.375;
                        scale = BMR - scale;
                    } else if (MainActivity.getActivityLevel().equals("Moderate")) {
                        scale *= 1.55;
                        scale = BMR - scale;
                    } else if (MainActivity.getActivityLevel().equals("Heavy")) {
                        scale *= 1.725;
                        scale = BMR - scale;
                    }
                }
            }
            if (MainActivity.getGender().equals("female")) {
                if (MainActivity.getHeightUnit().equals("cm") && MainActivity.getWeightUnit().equals("kg")) {
                    scale = 655 + (9.6 * (MainActivity.getWeight() - weightLostPerWeek)) + (1.8 * MainActivity.getHeight()) - (4.7 * MainActivity.getAge());
                    if (MainActivity.getActivityLevel().equals("Very Light")) {
                        scale *= 1.2;
                        scale = BMR - scale;
                    } else if (MainActivity.getActivityLevel().equals("Light")) {
                        scale *= 1.375;
                        scale = BMR - scale;
                    } else if (MainActivity.getActivityLevel().equals("Moderate")) {
                        scale *= 1.55;
                        scale = BMR - scale;
                    } else if (MainActivity.getActivityLevel().equals("Heavy")) {
                        scale *= 1.725;
                        scale = BMR - scale;
                    }
                }
                if (MainActivity.getHeightUnit().equals("ft") && MainActivity.getWeightUnit().equals("lbs")) {
                    scale = 655 + (4.35 * (MainActivity.getWeight() - weightLostPerWeek)) + (4.7 * (MainActivity.getHeight() * 12 + MainActivity.getHeightInches())) - (4.7 * MainActivity.getAge());
                    if (MainActivity.getActivityLevel().equals("Very Light")) {
                        scale *= 1.2;
                        scale = BMR - scale;
                    } else if (MainActivity.getActivityLevel().equals("Light")) {
                        scale *= 1.375;
                        scale = BMR - scale;
                    } else if (MainActivity.getActivityLevel().equals("Moderate")) {
                        scale *= 1.55;
                        scale = BMR - scale;
                    } else if (MainActivity.getActivityLevel().equals("Heavy")) {
                        scale *= 1.725;
                        scale = BMR - scale;
                    }
                }
            }

            final ListView weekList = (ListView) findViewById(R.id.WeekListView);
            final List<String> weeks = new ArrayList<String>();

            weekList.setClickable(false);

            if (numberOfWeeks.contains("Week")) {
                Double y = round(finalValue - scale, 0);
                Double z = round(finalValue, 0);
                String zNew = z.toString().substring(0, z.toString().length() - 2);
                String yNew = y.toString().substring(0, y.toString().length() - 2);
                for (int i = 0; i < intWeeks; i++) {
                    if (i == 0) {
                        if(MainActivity.getGender().equals("male")&&z<1600&&z>0){
                            weeks.add(i + 1 + "                                      " + zNew+"*");
                        }else if(MainActivity.getGender().equals("female")&&z<1200&&z>0) {
                            weeks.add(i + 1 + "                                      " + zNew+"*");
                        }else if(z<0){
                            weeks.add(i + 1 + "                                      " + zNew+"**");
                        }else{
                            weeks.add(i + 1 + "                                      " + zNew);
                        }
                    } else {
                        if(MainActivity.getGender().equals("male")&&y<1600&&y>0){
                            weeks.add(i + 1 + "                                      " + yNew+"*");
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }else if(MainActivity.getGender().equals("female")&&y<1200&&y>0) {
                            weeks.add(i + 1 + "                                      " + yNew+"*");
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }else if(y<0){
                            weeks.add(i + 1 + "                                      " + yNew+"**");
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }else{
                            weeks.add(i + 1 + "                                      " + yNew);
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }

                    }
                }
            }
            if (numberOfWeeks.contains("Month")) {
                Double y = round(finalValue - scale, 0);
                Double z = round(finalValue, 0);
                String zNew = z.toString().substring(0, z.toString().length() - 2);
                String yNew = y.toString().substring(0, y.toString().length() - 2);
                int x = 1;
                for (int j = 0; j < intWeeks; j++) {
                    if (x == 1) {
                        if(MainActivity.getGender().equals("male")&&z<1600&&z>0){
                            weeks.add("0"+x+"                                      " + zNew+"*");
                            x++;
                        }else if(MainActivity.getGender().equals("female")&&z<1200&&z>0) {
                            weeks.add("0"+x+"                                      " + zNew+"*");
                            x++;
                        }else if(z<0){
                            weeks.add("0"+x+"                                      " + zNew+"**");
                            x++;
                        }else{
                            weeks.add("0"+x+"                                      " + zNew);
                            x++;
                        }
                    } else if (x < 10) {
                        if(MainActivity.getGender().equals("male")&&y<1600&&y>0){
                            weeks.add("0"+x+"                                      " + yNew+"*");
                            x++;
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }else if(MainActivity.getGender().equals("female")&&y<1200&&y>0) {
                            weeks.add("0"+x+"                                      " + yNew+"*");
                            x++;
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }else if(y<0){
                            weeks.add("0"+x+"                                      " + yNew+"**");
                            x++;
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }else{
                            weeks.add("0"+x+"                                      " + yNew);
                            x++;
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }
                    } else {
                        if(MainActivity.getGender().equals("male")&&y<1600&&y>0){
                            weeks.add(x+"                                      " + yNew+"*");
                            x++;
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }else if(MainActivity.getGender().equals("female")&&y<1200&&y>0) {
                            weeks.add(x+"                                      " + yNew+"*");
                            x++;
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }else if(y<0){
                            weeks.add(x+"                                      " + yNew+"**");
                            x++;
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }else{
                            weeks.add(x+"                                      " + yNew);
                            x++;
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }
                    }
                }

            }
            if (numberOfWeeks.contains("Year")) {
                Double y = round(finalValue - scale, 0);
                Double z = round(finalValue, 0);
                String zNew = z.toString().substring(0, z.toString().length() - 2);
                String yNew = y.toString().substring(0, y.toString().length() - 2);
                for (int i = 1; i <= 52; i++) {
                    if (i == 1) {
                        if(MainActivity.getGender().equals("male")&&z<1600&&z>0){
                            weeks.add("0"+i + "                                      " + zNew+"*");
                        }else if(MainActivity.getGender().equals("female")&&z<1200&&z>0) {
                            weeks.add("0"+i + "                                      " + zNew+"*");
                        }else if(z<0){
                            weeks.add("0"+i + "                                      " + zNew+"**");
                        }else{
                            weeks.add("0"+i + "                                      " + zNew);
                        }
                    } else if (i < 10) {
                        if(MainActivity.getGender().equals("male")&&y<1600&&y>0){
                            weeks.add("0"+i + "                                      " + yNew+"*");
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }else if(MainActivity.getGender().equals("female")&&y<1200&&y>0) {
                            weeks.add("0"+i + "                                      " + yNew+"*");
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }else if(y<0){
                            weeks.add("0"+i + "                                      " + yNew+"**");
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }else{
                            weeks.add("0"+i + "                                      " + yNew);
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }
                    } else {
                        if(MainActivity.getGender().equals("male")&&y<1600&&y>0){
                            weeks.add(i + "                                      " + yNew+"*");
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }else if(MainActivity.getGender().equals("female")&&y<1200&&y>0) {
                            weeks.add(i + "                                      " + yNew+"*");
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }else if(y<0){
                            weeks.add(i + "                                      " + yNew+"**");
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }else{
                            weeks.add(i + "                                      " + yNew);
                            y = round(y - scale, 0);
                            yNew = y.toString().substring(0, y.toString().length() - 2);
                        }
                    }
                }
            }


            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, weeks);
            weekList.setAdapter(arrayAdapter);

        } catch (NumberFormatException | NullPointerException e) {

            CharSequence text = "Make sure all fields are filled.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(SecondActivity.this, text, duration);
            toast.show();

            finish();
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
