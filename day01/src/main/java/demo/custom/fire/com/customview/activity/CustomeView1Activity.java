package demo.custom.fire.com.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;

import demo.custom.fire.com.customview.widget.CustomView1;
import demo.custom.fire.com.customview.R;

/*
 *  项目名：  CustomView 
 *  包名：    demo.custom.fire.com.customview.utils
 *  文件名:   CustomeView1Activity
 *  创建者:   lsy
 *  创建时间:  2016/12/2 16:28
 *  描述：    TODO
 */
public class CustomeView1Activity extends Activity {

    private CustomView1 customView1;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);

        customView1 = (CustomView1) findViewById(R.id.custome);
        seekBar = (SeekBar) findViewById(R.id.seekBar);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                customView1.setRadius(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
