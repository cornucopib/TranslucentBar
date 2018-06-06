package com.ivan.translucentbar.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.ivan.translucentbar.R
import com.ivan.translucentbar.fragment.HomeFragment
import com.ivan.translucentbar.fragment.MyBussinessFragment
import com.ivan.translucentbar.fragment.UserCenterFragment
import com.ivan.translucentbar.translucentbar.core.statusbar.StatusBarHelper
import kotlinx.android.synthetic.main.activity_fit_system_window.*
import java.util.ArrayList

/*
* @author liuwei
* @email 13040839537@163.com
* create at 2018/6/5
* description: 
*/
class FitSystemWindowActivity : AppCompatActivity() {
    //碎片管理
    private var fragmentManager: FragmentManager? = null
    //各个模块的集合
    private val fragments = ArrayList<Fragment>()
    //各个模块的index
    val HOME_PAGE_INDEX = 0
    val MY_BUSSINESS_INDEX = 1
    val USER_CENTER_INDEX = 2
    //当前显示的模块fragment
    private var nowFragment: Fragment? = null
    //当前模块索引
    private var currentIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fit_system_window)
        initView()
        initSystemUITwo()
    }
    /**
     * 初始化View
     */
    private fun initView() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            val id = it.itemId
            var index = -1
            when (id) {
                R.id.bottom_nav_of_home_page -> index = HOME_PAGE_INDEX
                R.id.bottom_nav_of_my_bussiness -> index = MY_BUSSINESS_INDEX
                R.id.bottom_nav_of_user_center -> index = USER_CENTER_INDEX
            }
            switchContentUiFromId(index, id)
            true
        }

        fragmentManager = supportFragmentManager
        fragments.add(HomeFragment())
        fragments.add(MyBussinessFragment())
        fragments.add(UserCenterFragment())
        initSelectFragment(currentIndex)
    }

    /**
     * 全屏(下滑动不会导致状态栏显示，让应用主题内容占用状态栏)+状态栏透明
     * 最标准的透明状态栏
     * */
    private fun initSystemUITwo() {
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//            window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    /**
     * 根据id切换内容界面
     *
     * @param index
     */
    private fun switchContentUiFromId(index: Int, id: Int) {
        currentIndex = index
        switchFragment(fragments[currentIndex], currentIndex.toString() + "")
        when(index){
            0->StatusBarHelper.setStatusBarDarkMode(this)
            1->StatusBarHelper.setStatusBarLightMode(this)
            2->StatusBarHelper.setStatusBarLightMode(this)
        }
    }

    /**
     * 切换内容碎片
     *
     * @param fragment 被切换fragment
     * @param tag      fragment的标识
     */
    private fun switchFragment(fragment: Fragment?, tag: String) {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        if (null != fragment) {
            if (null != nowFragment) {
                fragmentTransaction.hide(nowFragment)
            }
            if (fragment.isAdded) {
                fragmentTransaction.show(fragment)
            } else {
                fragmentTransaction.add(R.id.main_content_fl, fragment, tag)
            }
            nowFragment = fragment
            fragmentTransaction.commit()
        }
    }

    /**
     * 显示选择的碎片
     */
    private fun initSelectFragment(index: Int) {
        var defaultId = -1
        when (index) {
            HOME_PAGE_INDEX -> defaultId = R.id.bottom_nav_of_home_page
            MY_BUSSINESS_INDEX -> defaultId = R.id.bottom_nav_of_my_bussiness
            USER_CENTER_INDEX -> defaultId = R.id.bottom_nav_of_user_center
        }
        bottomNavigationView.selectedItemId = defaultId
    }

}