package com.pokhilko.aleksandr.photofilter.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.pokhilko.aleksandr.photofilter.R
import com.pokhilko.aleksandr.photofilter.ui.CameraFragment
import com.zhihu.matisse.Matisse


class CameraActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_PHOTO_CODE = 111
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState ?: supportFragmentManager.beginTransaction()
                .replace(R.id.container, CameraFragment.newInstance())
                .commit()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_PHOTO_CODE) {
            val returnValue = Matisse.obtainResult(data)
            Log.d("photo path:", returnValue[0].toString())
            startActivity(Intent(this, FilterActivity::class.java).apply {
                putExtra(FilterActivity.EXTRA_PHOTO_PATH, returnValue[0].toString())
            })
        }
    }
}
