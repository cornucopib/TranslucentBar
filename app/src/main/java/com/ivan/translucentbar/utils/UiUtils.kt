package com.ivan.translucentbar.utils

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.EditText


/*
 * @author liuwei
 * @email 13040839537@163.com
 * create at 2018/4/13
 * description: UI相关工具类
 */

object UiUtils {


    /**
     * 获取状态栏高度
     */
    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId)
        }
        return result
    }

    /**
     * 获取toolbar的高度
     */
    fun getToolbarHeight(context:Context): Int {
        var actionBarHeight = 0
        val tv = TypedValue()
        if (context.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.resources.displayMetrics)
        }
        return actionBarHeight
    }



    /**
     * 获取图片资源
     */
    fun getIcons(context: Context, arraysId: Int): IntArray {
        val category_icons = context.resources.obtainTypedArray(arraysId)
        val len = category_icons.length()
        val resIds = IntArray(len)
        for (i in 0 until len) {
            resIds[i] = category_icons.getResourceId(i, 0)
        }
        category_icons.recycle()
        return resIds
    }

    /**
     * 设置margin
     *
     * @param v
     * @param l
     * @param t
     * @param r
     * @param b
     */
    fun setMargins(v: View, l: Int, t: Int, r: Int, b: Int) {
        if (v.layoutParams is ViewGroup.MarginLayoutParams) {
            val p = v.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(l, t, r, b)
            v.requestLayout()
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * Convert px to sp
     *
     * @param context
     * @param px
     * @return
     */
    fun px2sp(context: Context, px: Float): Int {
        return (pxToSp(context, px) + 0.5f).toInt()
    }

    /**
     * Convert px to sp
     *
     * @param context
     * @param px
     * @return
     */
    private fun pxToSp(context: Context, px: Float): Float {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return px / fontScale
    }

    /**
     * Convert sp to px
     *
     * @param context
     * @param sp
     * @return
     */
    fun sp2px(context: Context, sp: Float): Int {
        return (spToPx(context, sp) + 0.5f).toInt()
    }

    /**
     * Convert sp to px
     *
     * @param context
     * @param sp
     * @return
     */
    private fun spToPx(context: Context, sp: Float): Float {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return sp * fontScale
    }

    /**
     * @param srcString hint中的文字
     * @param size      hint的字体大小
     * @param editText  edittext
     */
    fun changeTextHintSize(srcString: String, size: Int, editText: EditText) {
        val s = SpannableString(srcString)
        val textSize = AbsoluteSizeSpan(size, true)
        s.setSpan(textSize, 0, s.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        editText.hint = s

    }

    /**
     * 更改edittext的hint字体大小
     *
     * @param size
     * @param editText
     */
    fun changeTextHintSize(size: Int, editText: EditText) {
        if (TextUtils.isEmpty(editText.hint)) {
            return
        }
        val hint = editText.hint.toString()
        val s = SpannableString(hint)
        val textSize = AbsoluteSizeSpan(size, true)
        s.setSpan(textSize, 0, s.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        editText.hint = s
    }


    /**
     * 对 View 设置 paddingLeft
     *
     * @param view  需要被设置的 View
     * @param value 设置的值
     */
    fun setPaddingLeft(view: View, value: Int) {
        view.setPadding(value, view.paddingTop, view.paddingRight, view.paddingBottom)
    }

    /**
     * 对 View 设置 paddingTop
     *
     * @param view  需要被设置的 View
     * @param value 设置的值
     */
    fun setPaddingTop(view: View, value: Int) {
        view.setPadding(view.paddingLeft, value, view.paddingRight, view.paddingBottom)
    }

    /**
     * 对 View 设置 paddingRight
     *
     * @param view  需要被设置的 View
     * @param value 设置的值
     */
    fun setPaddingRight(view: View, value: Int) {
        view.setPadding(view.paddingLeft, view.paddingTop, value, view.paddingBottom)
    }

    /**
     * 对 View 设置 paddingBottom
     *
     * @param view  需要被设置的 View
     * @param value 设置的值
     */
    fun setPaddingBottom(view: View, value: Int) {
        view.setPadding(view.paddingLeft, view.paddingTop, view.paddingRight, value)
    }



}
