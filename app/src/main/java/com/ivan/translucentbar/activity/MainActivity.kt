package com.ivan.translucentbar.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ivan.translucentbar.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        system_ui_tv.setOnClickListener(this)
        fit_system_window_tv.setOnClickListener(this)
    }



    override fun onClick(v: View) {
        when(v.id){
            R.id.system_ui_tv -> jumpToSystemUiActivity()
            R.id.fit_system_window_tv -> jumpToFitSystemWindowsActivity()
        }
    }

    /**
     * 跳转SystemUiActivity
     * */
    private fun jumpToSystemUiActivity() {
        var intent=Intent(this, SystemUiActivity::class.java)
        startActivity(intent)
    }

    /**
     * 跳转FitSystemWindowActivity
     * */
    private fun jumpToFitSystemWindowsActivity() {
        var intent=Intent(this, FitSystemWindowActivity::class.java)
        startActivity(intent)
    }



}
