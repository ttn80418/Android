package tw.tcnr01.I_cultur;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Event extends ListActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Intent intent = new Intent();
    private Integer[]img= {
            R.drawable.slider1,R.drawable.slider2,R.drawable.slider3,
            R.drawable.slider4,R.drawable.slider5,R.drawable.slider6,R.drawable.slider7,R.drawable.slider8
    };
    private String[] listResource;
    private String[] list1Resource;
    private ArrayList<Map<String, Object>> mList;
    private TextView ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
        // 設定要顯示回上一頁的按鈕



        setupViewCompoent();
    }



    private void setupViewCompoent() {
        //ans = (TextView) findViewById(R.id.event_t001);//選擇欄位
        listResource = getResources().getStringArray(R.array.event_date);//日期的欄位
        list1Resource=getResources().getStringArray(R.array.event_content);//內容的欄位
        mList = new ArrayList<Map<String, Object>>();//<>裡面屬於彈性陣列

        for (int i = 0; i < listResource.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();//重新定義一個陣列，屬於動態陣列。用put將資料放入

            item.put("imgView", img[i]);//(將每個圖都丟入
            item.put("txtView", listResource[i]);
            item.put("txtView1", list1Resource[i]);//
            mList.add(item);

        }
        SimpleAdapter adapter = new SimpleAdapter(this, mList,
                R.layout.event_item,
                new String[]{"imgView", "txtView","txtView1"},
                new int[]{R.id.imgView,R.id.event_t002,R.id.event_t003});//專長的擺放位置

        setListAdapter(adapter);
        //----------------------------------------------------------------
        ListView listview = getListView();
        listview.setTextFilterEnabled(true);
        listview.setOnItemClickListener(listviewOnItemClkLis);

        //------------抓出各個手機的瑩幕尺寸，並設定他們的寬高，若↓沒設 用不同手機看會跑掉!!!!!!!
        DisplayMetrics dm = new DisplayMetrics();//找出使用候手機的寬高
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;//density:密度

        int newscrollheight = dm.heightPixels * 75 / 100;
        listview.getLayoutParams().height=newscrollheight;

    }

    AdapterView.OnItemClickListener listviewOnItemClkLis = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
            //ans.setText(listResource[position]+" \n"+list1Resource[position]);//按到會出現對應內容
            Intent i = new Intent();

            i.setClass(Event.this,News01.class);
            i.putExtra("date",listResource[position]);//將日期欄位intent到new01
            startActivity(i);
            Event.this.finish();

            i.setClass(Event.this,News01.class);
            i.putExtra("title",list1Resource[position]);//將title欄位intent到new01
            startActivity(i);
            Event.this.finish();

        }



    };
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}