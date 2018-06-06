package com.ivan.translucentbar.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ivan.translucentbar.R

/*
* @author liuwei
* @email 13040839537@163.com
* create at 2018/6/5
* description: 
*/
class UserCenterFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view=inflater.inflate(R.layout.fg_user_center,container,false)
        return view
    }
}