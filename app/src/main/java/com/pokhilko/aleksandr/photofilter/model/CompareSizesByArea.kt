package com.pokhilko.aleksandr.photofilter.model

import android.util.Size
import java.lang.Long.signum

/**
 * Created by Aleksandr Pokhilko on 13.12.2018
 */
/**
 * Compares two `Size`s based on their areas.
 */
internal class CompareSizesByArea : Comparator<Size> {

    // We cast here to ensure the multiplications won't overflow
    override fun compare(lhs: Size, rhs: Size) =
            signum(lhs.width.toLong() * lhs.height - rhs.width.toLong() * rhs.height)

}