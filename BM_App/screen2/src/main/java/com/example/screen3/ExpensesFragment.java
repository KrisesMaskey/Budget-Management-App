package com.example.screen3;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.widget.AdapterView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.DBHelper1;
import com.example.common.DatePickerFragment;
import com.example.common.ExpenseDB;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExpensesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExpensesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpensesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView date;
    Date dt;
    String selectedDate;
    private int REQUEST_CODE = 1;
    ArrayAdapter adapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText e;
    private OnFragmentInteractionListener mListener;

    public ExpensesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExpensesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExpensesFragment newInstance(String param1, String param2) {
        ExpensesFragment fragment = new ExpensesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);
        final DBHelper1 sql = new DBHelper1(getActivity());
        ImageView img= view.findViewById(R.id.imagea);
        ImageView im=view.findViewById(R.id.imageView);
        final ListView recyclerView= view.findViewById(R.id.recycle);
        im.setY(250);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final ArrayList<String> name =sql.all();
                ArrayList<String> data1 =sql.all1();
                final ArrayList<String> data =sql.all2();
                ArrayList<String> data2 =sql.GetUsers();
                String[] from={"line1","line2"};
                int[] to={android.R.id.text1, android.R.id.text2};
               String x= String.valueOf(data1).replaceAll("\\p{P}","");
                recyclerView.setVisibility(View.VISIBLE);
                ArrayList<Person> peopleList = new ArrayList<>();
                for(int i=0;i<data.size();i++) {
                    Person john = new Person("$"+data.get(i), data1.get(i), data2.get(i));
                    peopleList.add(john);
                }



                PersonListAdapter adapter = new PersonListAdapter(getActivity(), R.layout.listview, peopleList);

                // ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_2,android.R.id.text1,data1);

                recyclerView.setAdapter(adapter);
                recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        int x= recyclerView.getSelectedItemPosition();

                                Toast.makeText(getActivity(), "You spent it on " + name.get(i)+
                                        "", Toast.LENGTH_LONG).show();


                    }
                });


            }
        });


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.expense_popup);
                dialog.setTitle("Title");
                dialog.show();

                Button btn= dialog.findViewById(R.id.button3);
                date= dialog.findViewById(R.id.pop);
                e=dialog.findViewById(R.id.editText);
               final Spinner spn= dialog.findViewById(R.id.spinner2);
                final EditText txt= dialog.findViewById(R.id.editText3);
                ImageView img1= dialog.findViewById(R.id.imageView3);
                final java.util.Calendar c = Calendar.getInstance();
                final ExpenseDB db=new ExpenseDB(getActivity());
                final FragmentManager fm = ((AppCompatActivity)getActivity()).getSupportFragmentManager();

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if((date.getText().toString().equals(""))||(spn.getSelectedItem().toString().equals(""))||(e.getText().toString().equals(""))||(txt.getText().toString().equals(""))) {
                            Toast.makeText(getActivity(), "Enter data in all fields", Toast.LENGTH_LONG).show();

                        }else {
                            String x = date.getText().toString();
                            String[] dateParts = x.split("-");
                            String day = dateParts[0];
                            String month = dateParts[1];
                            String year = dateParts[2];
                            System.out.println(month);
                            boolean lol = sql.insertUserDetails(spn.getSelectedItem().toString(), e.getText().toString(), date.getText().toString(), txt.getText().toString());
                            boolean lol1 = db.insertUserDetails(spn.getSelectedItem().toString(), e.getText().toString(), date.getText().toString(), txt.getText().toString(), month);
                            Toast.makeText(getActivity(), "Saved", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                        /*
                        if (lol1==true){

                        }
                        else {
                            Toast.makeText(getActivity(), "Enter data in all fields", Toast.LENGTH_LONG).show();

                        }
                        */
                    }
                });
                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*AppCompatDialogFragment newFragment = new DatePickerFragment();
                        // set the targetFragment to receive the results, specifying the request code
                        newFragment.setTargetFragment(getParentFragment(), REQUEST_CODE);
                        // show the datePicker
                        newFragment.show(fm, "datePicker");
                        */
                        showDatePicker();




                    }


                });

             }
             private void showDatePicker() {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                /**
                 * Set Up Current Date Into dialog
                 */
                Calendar calender = Calendar.getInstance();
                Bundle args = new Bundle();
                args.putInt("year", calender.get(Calendar.YEAR));
                args.putInt("month", calender.get(Calendar.MONTH));
                args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
                datePickerFragment.setArguments(args);
                /**
                 * Set Call back to capture selected date
                 */
                datePickerFragment.setCallBack(ondate);
                datePickerFragment.show(getFragmentManager(), "Date Picker");
             }

             DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {

                    date.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear+1)
                            + "-" + String.valueOf(year));
                }
             };




            });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check for the results
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // get date from string
            selectedDate = data.getStringExtra("selectedDate");
            // set the value of the editText
            e.setText(selectedDate);
        }
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date1 = "month/day/year: " + month + "/" + dayOfMonth + "/" + year;
        date.setText(date1);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    public String checkDigit (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
