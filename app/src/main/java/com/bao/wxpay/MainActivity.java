package com.bao.wxpay;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    BottomSheetBehavior bottomSheetBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.id_drawerlayout);
        initBottomSheetBehavior();
        ((EditText)findViewById(R.id.editText1)).setError("超出字符限制");
        findViewById(R.id.btnOpenBottom).setOnClickListener(btnOnClick);
    }

    private void initBottomSheetBehavior() {
        View bottom_sheet = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
        //改变底部动作条的状态
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        //设置底部动作条显示高度
        //bottomSheetBehavior.setPeekHeight(340);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_view,menu);
        return true;
    }

    private View.OnClickListener btnOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CoordinatorLayout coordinatorLayout = new CoordinatorLayout(MainActivity.this);
            Snackbar.make(v,"message",Snackbar.LENGTH_LONG)
                    .setAction("Action", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this, "xx", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
            drawerLayout.openDrawer(GravityCompat.END);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    };
}
