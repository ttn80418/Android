package tw.tcnr01.example01;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;//原RecyclerView.Adapter 因監聽改成ExampleAdapter(java)
    RecyclerView.LayoutManager mLayoutManager;
    //---以下宣告從舊java貼來的-----
    LinearLayout sliderDots;
    private int dotCounts;
    private ImageView[] dots;
    private Integer[]img= {
            R.drawable.image01,R.drawable.image02,R.drawable.image03,
            R.drawable.image04,R.drawable.image05,R.drawable.image06,R.drawable.image07,R.drawable.image08};
    private String[] date_Array,event_Array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date_Array=getResources().getStringArray(R.array.event_date);
        event_Array=getResources().getStringArray(R.array.event_content);
        //************可用巨集做(將從資料庫抓來的資料丟入個別的陣列後,再做巨集,        所以資料的命名最好有規則性  ex:   img01,img02.........)
        ArrayList<ExampleItem> exampleList = new ArrayList<>();
//        exampleList.add(new ExampleItem(R.drawable.ic_android,"Line 1","Line 2"));
//        exampleList.add(new ExampleItem(R.drawable.ic_audio,"Line 3","Line 4"));
//        exampleList.add(new ExampleItem(R.drawable.ic_sun,"Line 5","Line 6"));
        ///exampleList.add(new ExampleItem(b[0], shop_Array[0], shop_introduce_array[0]));
        for(int i=0;i<date_Array.length;i++){
            exampleList.add(new ExampleItem(img[i], date_Array[i], event_Array[i]));
        }


      //*********************************

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);//"true"使recyclerview不會因item的多寡而改變大小
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter =  new ExampleAdapter(exampleList);



        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
//---------監聽方法-------
//        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Intent i = new Intent();
//
//                i.setClass(MainActivity.this,News01.class);
//                i.putExtra("date",date_Array[position]);//將日期欄位intent到new01
//                startActivity(i);
//                MainActivity.this.finish();
//
//                i.setClass(MainActivity.this,News01.class);
//                i.putExtra("title",event_Array[position]);//
//                startActivity(i);
//                MainActivity.this.finish();
//            }
//        });

        //-------取得螢幕解析度-------------
        int size = img.length; //找出需放幾張圖
        int length = 100; //縮圖的寬度
        DisplayMetrics dm = new DisplayMetrics(); //找出使用者手機的寬高
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density*0.8); //整個水平選單的寬度
        int itemWidth = (int) (length * density*0.8); //每個縮圖佔的寬度

//        for(int i=0;i<dotCounts;i++) {
//
//            dots[i] = new ImageView(this);
//            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.show_active));//幻燈片相關
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            params.setMargins(8, 2, 8, 0);
//
//            sliderDots.addView(dots[i], params);
//        }
//        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.show));//幻燈片相關
    }
}
