package com.lawrene.falcon.aniekeme;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalenderFragment extends Fragment {

    View mView;
    CalendarView calendarView;
    List<TimeTableDaily> timeTableDailyList;
    TimeTableDailyAdapter timeTableDailyAdapter;
    ListView timeTableListView;
    DatabaseAccess databaseAccess;
    TextView textView;
    List<Courses> courses = new ArrayList<>();
    List<String> daysList = new ArrayList<>();
    String dayOfWeek = "";

    public CalenderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_calender, container, false);
        // Inflate the layout for this fragment

        databaseAccess = DatabaseAccess.getInstance(getContext());
        databaseAccess.open();


        calendarView = (CalendarView) mView.findViewById(R.id.main_calender);
        timeTableListView = mView.findViewById(R.id.courses_on_time_table);
        textView = mView.findViewById(R.id.emptyy);

        timeTableDailyList = new ArrayList<>();


        long currentDate = System.currentTimeMillis();

        String displayDate = new SimpleDateFormat().format(currentDate);

        StringTokenizer stringTokenizer = new StringTokenizer(displayDate);

        String fullDate = stringTokenizer.nextToken();
        StringTokenizer secondTokenizer = new StringTokenizer(fullDate, "/");
        secondTokenizer.nextToken();

        loadCourses(getDayOfWeek(Integer.parseInt(secondTokenizer.nextToken())));

        timeTableListView.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);

        timeTableDailyAdapter = new TimeTableDailyAdapter(getContext(), timeTableDailyList);
        timeTableListView.setAdapter(timeTableDailyAdapter);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int date) {
//                Toast.makeText(getContext(),date+ "/"+month+"/"+year,Toast.LENGTH_LONG).show();
//                Toast.makeText(getContext(), "" + getDayOfWeek(date) , Toast.LENGTH_SHORT).show();
                loadCourses(getDayOfWeek(date));
            }
        });

        return mView;
    }

    private void loadCourses(String day) {
        textView.setVisibility(View.GONE);
        timeTableListView.setVisibility(View.VISIBLE);

        courses = databaseAccess.getCourses();

        if (day.equals("Monday")) {
            timeTableDailyList = new ArrayList<>();
            timeTableDailyList.add(new TimeTableDaily("Process Control Engineering", "CPE521", "Dr. Eddie Akpan", "10am - 12pm"));
            timeTableDailyList.add(new TimeTableDaily("Advanced Computer Graphics", "CPE524", "M. EMmanuel Udoiwod", "2pm - 4pm"));

            for (int j = 0; j < courses.size(); j++) {
                String day1 = courses.get(j).getDay1();
                String day2 = courses.get(j).getDay2();
//
                if (!(day1 == null) && day1.equals("Monday")) {
                    timeTableDailyList.add(new TimeTableDaily(courses.get(j).getTitle(), courses.get(j).getCode(), courses.get(j).getLecturer(), courses.get(j).getTime1()));
                }
                else if (!(day2 == null) && day2.equals("Monday")) {
                        timeTableDailyList.add(new TimeTableDaily(courses.get(j).getTitle(), courses.get(j).getCode(), courses.get(j).getLecturer(), courses.get(j).getTime2()));
                }

            }

            timeTableDailyAdapter = new TimeTableDailyAdapter(getContext(), timeTableDailyList);
            timeTableListView.setAdapter(timeTableDailyAdapter);
        } else if (day.equals("Tuesday")) {
            timeTableDailyList = new ArrayList<>();
            timeTableDailyList.add(new TimeTableDaily("Computer Assisted Applications", "CPE523", "Mr. Anieke", "8am - 10am"));

            for (int j = 0; j < courses.size(); j++) {
                String day1 = courses.get(j).getDay1();
                String day2 = courses.get(j).getDay2();
//
                if (!(day1 == null) && day1.equals("Tuesday")) {
                    timeTableDailyList.add(new TimeTableDaily(courses.get(j).getTitle(), courses.get(j).getCode(), courses.get(j).getLecturer(), courses.get(j).getTime1()));
                }
                else if (!(day2 == null) && day2.equals("Tuesday")) {
                    timeTableDailyList.add(new TimeTableDaily(courses.get(j).getTitle(), courses.get(j).getCode(), courses.get(j).getLecturer(), courses.get(j).getTime2()));
                }

            }

            timeTableDailyAdapter = new TimeTableDailyAdapter(getContext(), timeTableDailyList);
            timeTableListView.setAdapter(timeTableDailyAdapter);
        } else if (day.equals("Wednesday")) {
            timeTableDailyList = new ArrayList<>();
            timeTableDailyList.add(new TimeTableDaily("Software Engineering II", "CPE522", "Mr. Bliss Stephen", "8am - 10am"));


            for (int j = 0; j < courses.size(); j++) {
                String day1 = courses.get(j).getDay1();
                String day2 = courses.get(j).getDay2();
//
                if (!(day1 == null) && day1.equals("Wednesday")) {
                    timeTableDailyList.add(new TimeTableDaily(courses.get(j).getTitle(), courses.get(j).getCode(), courses.get(j).getLecturer(), courses.get(j).getTime1()));
                }
                else if (!(day2 == null) && day2.equals("Wednesday")) {
                    timeTableDailyList.add(new TimeTableDaily(courses.get(j).getTitle(), courses.get(j).getCode(), courses.get(j).getLecturer(), courses.get(j).getTime2()));
                }

            }
            timeTableDailyAdapter = new TimeTableDailyAdapter(getContext(), timeTableDailyList);
            timeTableListView.setAdapter(timeTableDailyAdapter);
        } else if (day.equals("Thursday")) {
            timeTableListView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        } else if (day.equals("Friday")) {
            timeTableDailyList = new ArrayList<>();
            timeTableDailyList.add(new TimeTableDaily("Computer Assisted Applications", "ELE541", "Mr Emmanuel Ogunbemi", "2pm - 4pm"));


            for (int j = 0; j < courses.size(); j++) {
                String day1 = courses.get(j).getDay1();
                String day2 = courses.get(j).getDay2();
//
                if (!(day1 == null) && day1.equals("Friday")) {
                    timeTableDailyList.add(new TimeTableDaily(courses.get(j).getTitle(), courses.get(j).getCode(), courses.get(j).getLecturer(), courses.get(j).getTime1()));
                }
                else if (!(day2 == null) && day2.equals("Friday")) {
                    timeTableDailyList.add(new TimeTableDaily(courses.get(j).getTitle(), courses.get(j).getCode(), courses.get(j).getLecturer(), courses.get(j).getTime2()));
                }

            }

            timeTableDailyAdapter = new TimeTableDailyAdapter(getContext(), timeTableDailyList);
            timeTableListView.setAdapter(timeTableDailyAdapter);
        } else {
            Toast.makeText(getContext(), "This is weekend you need to enjoy yourself", Toast.LENGTH_LONG).show();
        }
    }

    public String getDayOfWeek(int date) {
        daysList.add("Sunday");
        daysList.add("Monday");
        daysList.add("Tuesday");
        daysList.add("Wednesday");
        daysList.add("Thursday");
        daysList.add("Friday");
        daysList.add("Saturday");

        if (date == 1) {
            dayOfWeek = daysList.get(3);
        }

        if (date == 2) {
            dayOfWeek = daysList.get(4);
        }

        if (date == 3) {
            dayOfWeek = daysList.get(5);
        }

        if (date == 4) {
            dayOfWeek = daysList.get(6);
        }

        if (date == 5 || date == 12 || date == 19 || date == 26) {
            dayOfWeek = daysList.get(0);
        }

        if (date == 6 || date == 13 || date == 20 || date == 27) {
            dayOfWeek = daysList.get(1);
        }

        if (date == 7 || date == 14 || date == 21 || date == 28) {
            dayOfWeek = daysList.get(2);
        }
        if (date == 8 || date == 15 || date == 22 || date == 29) {
            dayOfWeek = daysList.get(3);
        }
        if (date == 9 || date == 16 || date == 23 || date == 30) {
            dayOfWeek = daysList.get(4);
        }
        if (date == 10 || date == 17 || date == 24 || date == 31) {
            dayOfWeek = daysList.get(5);
        }
        if (date == 31 || date == 18 || date == 25) {
            dayOfWeek = daysList.get(6);
        }
        return dayOfWeek;
    }
}
