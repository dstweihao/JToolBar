package com.wh.jtoolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private JToolBar mJtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJtoolbar = (JToolBar) findViewById(R.id.jtoolbar);
        mJtoolbar.setOnTopbarClickListener(new JToolBar.jToolBarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(MainActivity.this, "Leftbutton", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(MainActivity.this, "RightButton", Toast.LENGTH_SHORT).show();
            }
        });
        //控制topbar上组件的状态
        mJtoolbar.setButtonVisable(mJtoolbar.LEFT_BUTTON, true);
        mJtoolbar.setButtonVisable(mJtoolbar.RIGHT_BUTTON, true);
    }
}
