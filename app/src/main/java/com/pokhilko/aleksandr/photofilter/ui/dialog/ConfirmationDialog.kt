package com.pokhilko.aleksandr.photofilter.ui.dialog

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.pokhilko.aleksandr.photofilter.R
import com.pokhilko.aleksandr.photofilter.utils.REQUEST_CAMERA_PERMISSION

/**
 * Created by Aleksandr Pokhilko on 13.12.2018
 */
/**
 * Shows OK/Cancel confirmation dialog about camera permission.
 */
class ConfirmationDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(activity)
                    .setMessage(R.string.request_permission)
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        parentFragment!!.requestPermissions(arrayOf(Manifest.permission.CAMERA),
                                REQUEST_CAMERA_PERMISSION)
                    }
                    .setNegativeButton(android.R.string.cancel) { _, _ ->
                        parentFragment!!.activity?.finish()
                    }
                    .create()
}