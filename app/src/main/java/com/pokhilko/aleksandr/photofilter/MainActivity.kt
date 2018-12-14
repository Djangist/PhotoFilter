package com.pokhilko.aleksandr.photofilter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.zhihu.matisse.Matisse


class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_PHOTO_CODE = 111
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState ?: supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainActivityFragment.newInstance())
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
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
