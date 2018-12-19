package com.pokhilko.aleksandr.photofilter.ui.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.pokhilko.aleksandr.photofilter.R
import com.zomato.photofilters.SampleFilters
import kotlinx.android.synthetic.main.activity_filter.*
import java.io.FileNotFoundException


/**
 * Created by Aleksandr Pokhilko on 14.12.2018
 */
class FilterActivity : AppCompatActivity() {

    companion object {

        const val EXTRA_PHOTO_PATH = "extra_photo_path"

        const val LIBRARY_NAME = "NativeImageProcessor"

        init {
            System.loadLibrary(LIBRARY_NAME)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        if (intent.extras.containsKey(EXTRA_PHOTO_PATH)) {
            val path = intent.extras.getString(EXTRA_PHOTO_PATH)
            Log.d("filters", "get path:$path")
            loadSourceImage(path)

            try {
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, Uri.parse(path))
                val sourceBitmap = bitmap.copy(bitmap.config, true)
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
                btnResetFilters.setOnClickListener {
                    loadSourceImage(path)
                }
            } catch(e: FileNotFoundException){
                e.printStackTrace()
            }

            /*Glide.with(this)
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
                            btnResetFilters.setOnClickListener {
                                Glide.with(it).load(path).into(ivImage)
                            }

                            return false
                        }
                    }
                    )
                    .submit()*/
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_share) {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, getImageUri((ivImage.drawable as BitmapDrawable).bitmap))
                type = "image/jpeg"
            }
            startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadSourceImage(path: String) {
        Glide.with(this).load(path).into(ivImage)
    }

    private fun getImageUri(bitmap: Bitmap): Uri {
        val bitmapPath = Images.Media.insertImage(contentResolver, bitmap, "title", null)
        return Uri.parse(bitmapPath)
    }
}