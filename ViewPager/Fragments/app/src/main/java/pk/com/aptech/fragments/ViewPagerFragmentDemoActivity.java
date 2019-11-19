package pk.com.aptech.fragments;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerFragmentDemoActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager=findViewById(R.id.pager);

        pager.setAdapter(new SampleAdapter(getSupportFragmentManager()));
    }
}
