package com.pokhilko.aleksandr.photofilter.ui.activity

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.pokhilko.aleksandr.photofilter.R
import com.zomato.photofilters.SampleFilters
import kotlinx.android.synthetic.main.activity_filter.*


/**
 * Created by Aleksandr Pokhilko on 14.12.2018
 */
class FilterActivity : AppCompatActivity() {

    companion object {

        init {
            System.loadLibrary("NativeImageProcessor")
        }

        const val EXTRA_PHOTO_PATH = "extra_photo_path"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        if (intent.extras.containsKey(EXTRA_PHOTO_PATH)) {
            val path = intent.extras.getString(EXTRA_PHOTO_PATH)
            Log.d("filters", "get path:$path")
            Glide.with(this).load(path).into(ivImage)
            Glide.with(this)
                    .asBitmap()
                    .load(Uri.parse(path))
                    .listener(object : RequestListener<Bitmap> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                            e?.printStackTrace()
                            Log.e("filters", e?.message)
                            return false
                        }

                        override fun onResourceReady(bitmap: Bitmap, o: Any,
                                                     target: com.bumptech.glide.request.target.Target<Bitmap>, dataSource: DataSource, b: Boolean): Boolean {

                            val sourceBitmap = Bitmap.createBitmap(bitmap)
                            btnFilter1.setOnClickListener {
                                val filter = SampleFilters.getBlueMessFilter()
                                ivImage.setImageBitmap(filter.processFilter(sourceBitmap))
                            }

                            btnFilter2.setOnClickListener {
                                val filter = SampleFilters.getStarLitFilter()
                                ivImage.setImageBitmap(filter.processFilter(sourceBitmap))
                            }

                            btnFilter3.setOnClickListener {
                                val filter = SampleFilters.getLimeStutterFilter()
                                ivImage.setImageBitmap(filter.processFilter(sourceBitmap))
                            }
                            btnFilter4.setOnClickListener {
                                val filter = SampleFilters.getAweStruckVibeFilter()
                                ivImage.setImageBitmap(filter.processFilter(sourceBitmap))
                            }
                            btnFilter5.setOnClickListener {
                                val filter = SampleFilters.getNightWhisperFilter()
                                ivImage.setImageBitmap(filter.processFilter(sourceBitmap))
                            }

                            return false
                        }
                    }
                    )
                    .submit()
            /*Glide.with(this).asBitmap().load(path).into(
                    object : SimpleTarget<Bitmap>() {
                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                            val sourceBitmap = Bitmap.createBitmap(resource)
                            btnFilter1.setOnClickListener {
                                val filter = SampleFilters.getBlueMessFilter()
                                ivImage.setImageBitmap(filter.processFilter(sourceBitmap))
                            }

                            btnFilter2.setOnClickListener {
                                val filter = SampleFilters.getStarLitFilter()
                                ivImage.setImageBitmap(filter.processFilter(sourceBitmap))
                            }

                            btnFilter3.setOnClickListener {
                                val filter = SampleFilters.getLimeStutterFilter()
                                ivImage.setImageBitmap(filter.processFilter(sourceBitmap))
                            }
                            btnFilter4.setOnClickListener {
                                val filter = SampleFilters.getAweStruckVibeFilter()
                                ivImage.setImageBitmap(filter.processFilter(sourceBitmap))
                            }
                            btnFilter5.setOnClickListener {
                                val filter = SampleFilters.getNightWhisperFilter()
                                ivImage.setImageBitmap(filter.processFilter(sourceBitmap))
                            }
                        }
                    }
            )*/
        }
    }
}