# Material_Design
Material Design(质感设计/材料设计)的主要元素组合demo,左右抽屉及底部动作条
底部动作条,使用CoordinatorLayout的BottomSheetBehavior
效果：在当前界面从下往上弹出一个界面，并且可滑动隐藏。如，支付宝支付界面


========activity_main.xml=============
<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/id_drawerlayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.design.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"
            android:padding="10dp">

            <include layout="@layout/layout_content" />
        </LinearLayout>

        <include layout="@layout/layout_bottom" />

    </android.support.design.widget.CoordinatorLayout>

    <android.support.design.widget.NavigationView
        android:id="@+id/navigation1"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        app:headerLayout="@layout/navigation_header"
        app:menu="@menu/drawer_view">

    </android.support.design.widget.NavigationView>

    <android.support.design.widget.NavigationView
        android:id="@+id/navigation2"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="end"
        app:headerLayout="@layout/navigation_header"
        app:menu="@menu/drawer_view">

    </android.support.design.widget.NavigationView>


</android.support.v4.widget.DrawerLayout>

===========layout_bottom.xml=================================
<LinearLayout android:layout_width="match_parent"
    android:layout_height="50dp"
    android:id="@+id/bottom_sheet"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:background="@color/colorPrimary"
    android:orientation="vertical"
    app:behavior_peekHeight="80dp"
    app:behavior_hideable="true"
    app:layout_behavior="android.support.design.widget.BottomSheetBehavior"
    xmlns:android="http://schemas.android.com/apk/res/android">
    <!--
        app:layout_behavior="android.support.design.widget.BottomSheetBehavior"
        主要就是该属性的引入,变成底部动作条
    app:behavior_peekHeight="80dp" //定义底部动作条的可见高度,这里正好是第一个textview的高度
    app:behavior_hideable //定义是否可以下滑隐藏底部动作条
    -->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/textView1"
        android:text="Item1"/>
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Item2"/>

</LinearLayout>

============navigation_header===============
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal">
    <ImageView
        android:id="@+id/imageview"
        android:background="@mipmap/ic_launcher"
        android:layout_width="100dp"
        android:layout_height="100dp" />

    <TextView
        android:text="账户名"
        android:layout_marginLeft="20dp"
        android:layout_marginTop="40dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</LinearLayout>
==================mainActivity.java=======
public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    BottomSheetBehavior bottomSheetBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.id_drawerlayout);
        findViewById(R.id.btn_login).setOnClickListener(btnOnClick);
        initBottomSheetBehavior();
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
