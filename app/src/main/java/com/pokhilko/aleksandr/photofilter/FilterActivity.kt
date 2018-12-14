package com.pokhilko.aleksandr.photofilter

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.zomato.photofilters.SampleFilters
import kotlinx.android.synthetic.main.activity_filter.*

/**
 * Created by Aleksandr Pokhilko on 14.12.2018
 */
class FilterActivity: AppCompatActivity() {

    companion object {

        init {
            System.loadLibrary("NativeImageProcessor")
        }

        const val EXTRA_PHOTO_PATH = "extra_photo_path"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        if(intent.extras.containsKey(EXTRA_PHOTO_PATH)){
            val path = intent.extras.getString(EXTRA_PHOTO_PATH)
            ivImage.setImageURI(Uri.parse(path))
            Glide.with(this).asBitmap().load(Uri.parse(path)).into(
                    object: SimpleTarget<Bitmap>() {
                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                            btnFilter1.setOnClickListener {
                                val filter = SampleFilters.getBlueMessFilter()
                                ivImage.setImageBitmap(filter.processFilter(resource))
                            }

                            btnFilter2.setOnClickListener {
                                val filter = SampleFilters.getStarLitFilter()
                                ivImage.setImageBitmap(filter.processFilter(resource))
                            }

                            btnFilter3.setOnClickListener {
                                val filter = SampleFilters.getLimeStutterFilter()
                                ivImage.setImageBitmap(filter.processFilter(resource))
                            }
                        }
                    }

        )
        }
    }

}