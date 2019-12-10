package projects.example.newfoodtimer;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.media.CamcorderProfile.get;

public class Selection extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        expandableListTitle.get(groupPosition) + " List Expanded.",
//                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        expandableListTitle.get(groupPosition) + " List Collapsed.",
//                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                Intent intent = new Intent(Selection.this,MainActivity.class);

                String title_ramen = getResources().getString(R.string.ramen);
                String title_spa = getResources().getString(R.string.spaghetti);
                String title_udon = getResources().getString(R.string.udon);
                String title_plain = getResources().getString(R.string.plainnoo);
                String title_glass = getResources().getString(R.string.glassnoo);
                String title_noodlesoup = getResources().getString(R.string.noodlesoup);
                String title_beansp = getResources().getString(R.string.beansprouts);
                String title_mungbean = getResources().getString(R.string.mungbeansprouts);
                String title_spi = getResources().getString(R.string.spinach);
                String title_hard = getResources().getString(R.string.hardboiled);
                String title_soft = getResources().getString(R.string.softboiled);


                if (groupPosition == 0 && childPosition == 0) {
                    intent.putExtra("key",4f);
                    intent.putExtra("text_title", title_ramen);
              }

                if (groupPosition == 0 && childPosition == 1) {
                    intent.putExtra("key",8f);
                    intent.putExtra("text_title", title_spa);
                }

                if (groupPosition == 0 && childPosition == 2) {
                    intent.putExtra("key",20f);
                    intent.putExtra("text_title", title_udon);
                }

                if (groupPosition == 0 && childPosition == 3) {
                    intent.putExtra("key",3f);
                    intent.putExtra("text_title", title_plain);
                }


                if (groupPosition == 0 && childPosition == 4) {
                    intent.putExtra("key",6f);
                    intent.putExtra("text_title", title_glass);
                }

                if (groupPosition == 0 && childPosition == 5) {
                    intent.putExtra("key",6.5f);
                    intent.putExtra("text_title", title_noodlesoup);
                }


                if (groupPosition == 1 && childPosition == 0) {
                    intent.putExtra("key", 10f);
                    intent.putExtra("text_title", title_hard);
                }

                if (groupPosition == 1 && childPosition == 1) {
                    intent.putExtra("key",7f);
                    intent.putExtra("text_title", title_soft);

                }

                if (groupPosition == 2 && childPosition == 0) {
                    intent.putExtra("key",4f);
                    intent.putExtra("text_title", title_beansp);

                }

                if (groupPosition == 2 && childPosition == 1) {
                    intent.putExtra("key",3f);
                    intent.putExtra("text_title", title_mungbean);

                }

                if (groupPosition == 2 && childPosition == 2) {
                    intent.putExtra("key",0.17f);
                    intent.putExtra("text_title", title_spi);

                }


                startActivity(intent);

                return true;


            }
        });
    }

}