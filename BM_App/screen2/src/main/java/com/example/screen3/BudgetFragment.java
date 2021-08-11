package com.example.screen3;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.BudgetDB;
import com.example.common.DBHelper1;
import com.example.common.ExpenseDB;
import com.example.common.MySqliteOpenHelper;
import com.example.common.Priority;
import com.example.common.Recursive;
import com.example.common.addcalc;
import com.example.common.remainingbalance;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BudgetFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BudgetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BudgetFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    boolean k=true;
    public String balance;
    boolean flag=true;
    DialogFragment dialog;
    ImageView pic;
    private OnFragmentInteractionListener mListener;
    public BudgetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BudgetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BudgetFragment newInstance(String param1, String param2) {

        BudgetFragment fragment = new BudgetFragment();
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
        dialog= new DialogFragment();


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_budget, container, false);

        final MySqliteOpenHelper sql=new MySqliteOpenHelper(getActivity());
        final remainingbalance rmdb= new remainingbalance(getActivity());
        final DBHelper1 mysql= new DBHelper1(getActivity());
        ImageView toolbar = view.findViewById(R.id.pic);
        ImageView ref= view.findViewById(R.id.imageView2);
        final TextView title=  view.findViewById(R.id.tt);
        final  TextView bb= view.findViewById(R.id.textView8);
        final TextView title1= view.findViewById(R.id.textView6);
        final TextView title2= view.findViewById(R.id.textView7);
        final TextView title3= view.findViewById(R.id.textView);
        final TextView title4= view.findViewById(R.id.text);
        final TextView title5= view.findViewById(R.id.tp1);
        final TextView title6= view.findViewById(R.id.tp2);
        final TextView title7= view.findViewById(R.id.tp3);
        final BottomNavigationView b= view.findViewById(R.id.balance);
        final BottomNavigationView b1=view.findViewById(R.id.info);
        final BottomNavigationView pnl=view.findViewById(R.id.tp);
        final Priority priority= new Priority();
        final BudgetDB bd= new BudgetDB(getActivity());
        ref.setY(250);



        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sql.Delete();
                ArrayList<String> type1 =mysql.GetUsers();
                System.out.println(type1);
                ArrayList<String> price= mysql.all2();
                ArrayList<HashMap<String, String>> data =sql.all();
                ArrayList<String> d = rmdb.all();
                ArrayList<HashMap<String, String>> data1 =sql.all1();
                ArrayList<HashMap<String, String>> data2=sql.all2();
                ArrayList<HashMap<String, String>> data3 =sql.all3();
                ArrayList<HashMap<String, String>> data4 =sql.all4();
                String p= String.valueOf(data).replaceAll("\\=","");
                //String rb= String.valueOf(d).replaceAll("\\=","");
                String p1= String.valueOf(data1).replaceAll("\\=","");
                String p2= String.valueOf(data2).replaceAll("\\=","");
                String p3= String.valueOf(data3).replaceAll("\\=","");
                String p4= String.valueOf(data4).replaceAll("\\=","");
                try {
                    if ((p1 != "") && (p2 != "") && (p3 != "") && (p4 != "") && (p != "")) {
                        title.setText("Budget For Month" + "\n" + "\t" + p.replaceAll("\\p{P}", ""));
                        title.setTextSize(20);
                        title.setY(250);
                        title1.setText("Capital - " + p1.replaceAll("\\p{P}", ""));
                        title2.setText("Fixed Expense - " + p4.replaceAll("\\p{P}", ""));
                        title3.setText("Reserve - " + p3.replaceAll("\\p{P}", ""));
                        title4.setText("Investment - " + p2.replaceAll("\\p{P}", ""));
                        balance = priority.balance1(p1.replaceAll("\\p{P}", ""), p2.replaceAll("\\p{P}", ""), price, p4.replaceAll("\\p{P}", ""), d);
                        bb.setText("$ " + balance);

                        //bd.insert(balance);

                    } else {
                        Toast.makeText(getActivity(), "No Data found", Toast.LENGTH_LONG).show();
                    }
                    System.out.println(priority.type(type1));
                    ArrayList<String> s = priority.mostCommon(type1);
                    if ((type1.size() != 0)) {
                        title5.setText("1  " + String.valueOf(priority.type(type1).get(0)));
                        title6.setText("2  " + String.valueOf(priority.type(type1).get(1)));
                        title7.setText("3  " +
                                String.valueOf(priority.type(type1).get(2)));
                        b.setVisibility(View.VISIBLE);
                        b1.setVisibility(View.VISIBLE);
                        pnl.setVisibility(View.VISIBLE);
                    } else {
                        title5.setText("1  No data");
                        title6.setText("2  No data");
                        title7.setText("3  No data");
                        b.setVisibility(View.VISIBLE);
                        b1.setVisibility(View.VISIBLE);
                        pnl.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        });


        if(k==true) {
            toolbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.addincome);
                    dialog.setTitle("Title");
                    dialog.show();



                    final EditText income = dialog.findViewById(R.id.editText);
                    final EditText remarks = dialog.findViewById(R.id.editText2);
                    Button button= (Button)dialog.findViewById(R.id.button3);
                    final ImageView cross= dialog.findViewById(R.id.imageView3);



                    cross.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ArrayList<String> d = rmdb.all();
                            ArrayList<String> type1 =mysql.GetUsers();
                            ArrayList<String> price= mysql.all2();
                            ArrayList<HashMap<String, String>> data =sql.all();
                            ArrayList<HashMap<String, String>> data1 =sql.all1();
                            ArrayList<HashMap<String, String>> data2=sql.all2();
                            ArrayList<HashMap<String, String>> data3 =sql.all3();
                            ArrayList<HashMap<String, String>> data4 =sql.all4();
                            String p= String.valueOf(data).replaceAll("\\=","");
                            String p1= String.valueOf(data1).replaceAll("\\=","");
                            String p2= String.valueOf(data2).replaceAll("\\=","");
                            String p3= String.valueOf(data3).replaceAll("\\=","");
                            String p4= String.valueOf(data4).replaceAll("\\=","");
                                balance= priority.balance1(p1.replaceAll("\\p{P}", ""), p2.replaceAll("\\p{P}", ""), price, p4.replaceAll("\\p{P}", ""),d);




                            final addcalc a= new addcalc();
                            final Recursive re=new Recursive();
                            String s1= income.getText().toString();
                            String s2= remarks.getText().toString();
                            //System.out.println("Clicked"+s5+s1+s3+s2+s4);
                            if(s1.equals("")||s2.equals("")){
                                Toast.makeText(getActivity(), "Insert data in all fields", Toast.LENGTH_LONG).show();
                            }else {
                                int q = re.stringToInt(s1);
                                int kix = a.calc(q, Integer.parseInt(balance));
                                System.out.println(kix);
                                String ii = String.valueOf(s1);




                                    bd.insert(String.valueOf(kix));
                                    // sql.Delete();
                                    boolean lol = rmdb.insert(ii);
                                    //mysql.DeleteUser();


                                    if (lol == true) {
                                        income.setText("");
                                        remarks.setText("");

                                        Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_LONG).show();
                                        System.out.println("inserted");

                                        dialog.dismiss();
                                    } else {
                                        Toast.makeText(getActivity(), "Data Not Inserted", Toast.LENGTH_LONG).show();
                                    }


                            }
                        }


                    });
                }
            });

        }else {
            pic.setClickable(false);
        }

        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void display(View v){
        Button b= getView().findViewById(R.id.button3);

    }


    @Override
        public void onDetach(){
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

