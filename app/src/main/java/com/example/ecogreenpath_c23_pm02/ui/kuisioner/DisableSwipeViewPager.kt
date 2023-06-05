package com.example.ecogreenpath_c23_pm02.ui.kuisioner

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class DisableSwipeViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        // Mengabaikan peristiwa onTouchEvent untuk mencegah penggeseran halaman
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        // Mengabaikan peristiwa onInterceptTouchEvent untuk mencegah penggeseran halaman
        return false
    }
}
