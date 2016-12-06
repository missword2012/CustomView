package demo.custom.fire.com.customview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import demo.custom.fire.com.customview.R;
import demo.custom.fire.com.customview.widget.CustomView1;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLlRoot = (LinearLayout) findViewById(R.id.rl_root);

        CustomView1 customView1 = new CustomView1(this);
        mLlRoot.addView(customView1);


        new Thread(customView1).start();

    }


}
