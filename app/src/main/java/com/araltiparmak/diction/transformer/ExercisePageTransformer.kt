package com.araltiparmak.diction.transformer

import android.content.res.Resources
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import com.araltiparmak.diction.R

object ExercisePageTransformer {

    fun apply(
        viewPager: ViewPager2,
        resources: Resources
    ) {
        with(viewPager) {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
        }

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)

        viewPager.setPageTransformer { page, position ->
            val vPager = page.parent.parent as ViewPager2
            val offset = position * -(2 * pageMarginPx + offsetPx)
            if (vPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(vPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.translationX = -offset
                } else {
                    page.translationX = offset
                }
            } else {
                page.translationY = offset
            }
        }
    }
}