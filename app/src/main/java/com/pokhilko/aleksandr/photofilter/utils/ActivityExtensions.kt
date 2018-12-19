package com.pokhilko.aleksandr.photofilter.utils

import android.support.v4.app.FragmentActivity
import android.widget.Toast

/**
 * Created by Aleksandr Pokhilko on 13.12.2018
 */
/**
 * Shows a [Toast] on the UI thread.
 *
 * @param text The message to show
 */
fun FragmentActivity.showToast(text: String) {
    runOnUiThread { Toast.makeText(this, text, Toast.LENGTH_SHORT).show() }
}