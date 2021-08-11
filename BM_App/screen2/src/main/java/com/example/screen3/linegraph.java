package com.example.screen3;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.common.BudgetDB;
import com.example.common.DBHelper1;
import com.example.common.ExpenseDB;
import com.example.common.linegraph_values;
import com.example.common.montn_converter;
import com.example.common.stat_activity;
import com.example.screen3.R;

import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.view.LineChartView;


import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;


public class linegraph extends Activity {

    LineChartView lineChartView;
    String[] axisData = {"0", "", "10", "", "20", "", "30"};
    int[] yAxisData = {50, 20, 15, 30, 20, 60, 15, 40, 45, 10, 90, 18};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_linegraph);
        final BudgetDB db = new BudgetDB(linegraph.this);
        final DBHelper1 sql = new DBHelper1((this));
        final ExpenseDB edb = new ExpenseDB(this);
        final stat_activity st = new stat_activity();
        final linegraph_values ln = new linegraph_values();
        final montn_converter mt = new montn_converter();
        int max = ln.maximum(db.all1());


        ArrayList ar = ln.month(db.all());
        if (ar.size() > 2) {
            String a = ar.get(ar.size() - 1).toString();
            ArrayList<String> priceslast = edb.all2(a);
            ArrayList<String> prices1 = (edb.all2(ar.get(ar.size() - 2).toString()));
            ArrayList<String> prices2 = edb.all2(ar.get(ar.size() - 3).toString());


            // ArrayList<Integer> array= ln.yaxis(sql.all2());
            ArrayList<Integer> array = ln.yaxis(priceslast);
            ArrayList<Integer> array2 = ln.yaxis1(prices1);
            ArrayList<Integer> array3 = ln.yaxis2(prices2);

            ArrayList<Integer> arrayList= new ArrayList();
            arrayList.add(array.get(0)); //October
            arrayList.add(12);
            arrayList.add(6);
            arrayList.add(16);
            arrayList.add(10);

            ArrayList<Integer> arrayList1= new ArrayList();
            arrayList1.add(array2.get(0)); //November
            arrayList1.add(17);
            arrayList1.add(12);
            arrayList1.add(8);
            arrayList1.add(10);

            ArrayList<Integer> arrayList2= new ArrayList();
            arrayList2.add(array3.get(0));
            arrayList2.add(17);
            arrayList2.add(20);
            arrayList2.add(13);
            arrayList2.add(2);

            lineChartView = findViewById(R.id.line_chart);

            List yAxisValues = new ArrayList();
            List axisValues = new ArrayList();
            //List lol=new ArrayList();
            List v1 = new ArrayList();
            List v2 = new ArrayList();


            Line line = new Line(yAxisValues).setColor(Color.parseColor("#B22222"));
            //Line line1 = new Line(lol).setColor(Color.parseColor("#00FF00"));
            Line line2 = new Line(v1).setColor(Color.parseColor("#9C27B0"));
            Line line3 = new Line(v2).setColor(Color.parseColor("#00FF00"));


            for (int i = 0; i < axisData.length; i++) {
                axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
            }

            for (int i = 0; i <arrayList.size() ; i++) {
                yAxisValues.add(new PointValue(i, arrayList.get(i)));
            }

            for (int i = 0; i < arrayList1.size(); i++) {
                v1.add(new PointValue(i, arrayList1.get(i)));
            }
            for (int i = 0; i < arrayList2.size(); i++) {
                v2.add(new PointValue(i, arrayList2.get(i)));
            }

       /* for (int i = 0; i < yAxisData.length; i++) {
            lol.add(new PointValue(i, yAxisData[i]));
        }*/

            List lines = new ArrayList();
            lines.add(line);
            //lines.add(line1);
            lines.add(line2);
            lines.add(line3);


            LineChartData data = new LineChartData();
            data.setLines(lines);


            Axis axis = new Axis();
            axis.setValues(axisValues);
            axis.setTextSize(16);
            axis.setName("Days");
            axis.setTextColor(Color.parseColor("#03A9F4"));
            data.setAxisXBottom(axis);

            Axis yAxis = new Axis();
            yAxis.setName("Balance in '000' ");
            yAxis.setTextColor(Color.parseColor("#03A9F4"));
            yAxis.setTextSize(16);
            data.setAxisYLeft(yAxis);

            lineChartView.setLineChartData(data);
            Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
            lineChartView.setClickable(true);
            //lineChartView.setOnValueTouchListener(LineChartOnValueSelectListener touchlistener){


            //viewport.right=30;
            viewport.top = max/1000;
            viewport.bottom = 0;
            lineChartView.setMaximumViewport(viewport);
            lineChartView.setCurrentViewport(viewport);
        } else {
            Toast.makeText(this, "Data of atleast 3 months is required", Toast.LENGTH_LONG).show();

        }
    }
}