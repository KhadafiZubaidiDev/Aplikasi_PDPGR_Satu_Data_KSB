package sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.MainActivity;
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.R;
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.base.BaseActivity;
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.slide.The_Slide_Items_Model_Class;
import sumbawa.barat.aplikasidatasektoralberbasisgotongroyong.slide.The_Slide_items_Pager_Adapter;

public class MenuUtamaActivity extends BaseActivity {

    private List<The_Slide_Items_Model_Class> listItems;
    private ViewPager page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        this.setTitle("Menu Utama");

        //Penanganan carousel
        page = findViewById(R.id.my_pager) ;
        TabLayout tabLayout = findViewById(R.id.my_tablayout);

        listItems = new ArrayList<>() ;
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.image1,"Pulau Kenawa"));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.image2,"Barapan Kerbau"));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.image3,"Desa Mantar"));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.image4,"Rarak Ronges"));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.image5,"Pantai Kertasari"));
        listItems.add(new The_Slide_Items_Model_Class(R.drawable.image6,"Pantai Jelenga"));

        The_Slide_items_Pager_Adapter itemsPager_adapter = new The_Slide_items_Pager_Adapter(this, listItems);
        page.setAdapter(itemsPager_adapter);

        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new The_slide_timer(),2000,3000);
        tabLayout.setupWithViewPager(page,true);
        //Akhir penanganan carousel

        //Penanganan even klik pada cardview btn1, btn2, btn3, btn4, btn5 dan btn6
        CardView btn1,btn2,btn3,btn4;
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent keActivityBerita = new Intent(MenuUtamaActivity.this, BeritaActivity.class);
                startActivity(keActivityBerita);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent keActivityKecamatan = new Intent(MenuUtamaActivity.this, KecamatanActivity.class);
                startActivity(keActivityKecamatan);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent keActivityEnsiklopedia = new Intent(MenuUtamaActivity.this, EnsiklopediaActivity.class);
                startActivity(keActivityEnsiklopedia);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent keActivityPuskesmas = new Intent(MenuUtamaActivity.this, PuskesmasActivity.class);
                startActivity(keActivityPuskesmas);
            }
        });
    }

    public class The_slide_timer extends TimerTask {
        @Override
        public void run() {
            MenuUtamaActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (page.getCurrentItem()< listItems.size()-1) {
                        page.setCurrentItem(page.getCurrentItem()+1);
                    }
                    else
                        page.setCurrentItem(0);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pilihan_ke_main_activity, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.menu_home){
            startActivity(new Intent(this, MainActivity.class));
        }
        return true;
    }
}