package tw.tcnr01.I_cultur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Timer;
import java.util.TimerTask;

public class News01 extends AppCompatActivity {

    ViewPager viewPager;
    SliderViewPagerAdapter adapter;
    LinearLayout sliderDots;
    private int dotCounts;
    private ImageView[] dots;
    private  Integer [] images = {R.drawable.slider1,R.drawable.slider2,R.drawable.slider3,R.drawable.slider4,R.drawable.slider5};
    private String TAG = "tcnr24=>";
    private TextView text01,text02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent it = getIntent();
        String date = it.getStringExtra("date");//取得Event裡putExtra的date
        String str = it.getStringExtra("title");//取得Event裡putExtra的title
        Log.d(TAG,str);
        setContentView(R.layout.activity_main);
        text01=(TextView)findViewById(R.id.news_t001);
        text01.setText(date);
        text02=(TextView)findViewById(R.id.news_t002);
        text02.setText(str);


        viewPager = findViewById(R.id.viewPager);
        adapter = new SliderViewPagerAdapter(this);
        viewPager.setAdapter(adapter);


        sliderDots = findViewById(R.id.SliderDots);


        dotCounts = adapter.getCount();
        dots = new ImageView[dotCounts];

        // 設定要顯示回上一頁的按鈕
//        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setHomeButtonEnabled(true);//主键按钮能否可点击
        supportActionBar.setDisplayHomeAsUpEnabled(true);//显示返回图标



//-------取得螢幕解析度-------------
        int size = images.length; //找出需放幾張圖
        int length = 100; //縮圖的寬度
        DisplayMetrics dm = new DisplayMetrics(); //找出使用者手機的寬高
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density*0.8); //整個水平選單的寬度
        int itemWidth = (int) (length * density*0.8); //每個縮圖佔的寬度

        for(int i=0;i<dotCounts;i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.show_active));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 2, 8, 0);

            sliderDots.addView(dots[i], params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.show));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i=0;i<dotCounts;i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.show_active));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.show));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),3000,6000);


    }

    private class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            News01.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }else if(viewPager.getCurrentItem()==2){
                        viewPager.setCurrentItem(3);
                    }
                    else if(viewPager.getCurrentItem()==3){
                        viewPager.setCurrentItem(4);
                    }else{
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            Toast.makeText(this, "按下左上角返回鍵", Toast.LENGTH_SHORT).show();//先做判斷 是否←有被點擊
//        }

        switch (item.getItemId()) {
            case android.R.id.home:////主键id 一定要寫這樣
                Intent i = new Intent(this,Event.class);
                startActivity(i);
                News01.this.finish();
//               onBackPressed();//按返回图标直接回退上个界面
                return true;
            //break;
        }
        return super.onOptionsItemSelected(item);
    }

}