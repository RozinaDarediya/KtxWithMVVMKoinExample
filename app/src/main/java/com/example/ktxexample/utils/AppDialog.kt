package com.example.ktxexample.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import androidx.core.content.ContextCompat
import com.example.ktxexample.R

class AppDialog {

    companion object{

        /**
         *  @param showAlertDialog is called by showErrorDialog
         */
        fun showAlertWithTwoDialog(
            _context: Context, _title: String?,
            _message: String, _positiveText: String,
            _onPositiveClick: DialogInterface.OnClickListener
        ) {
            val dialog = AlertDialog.Builder(_context).create()
            dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
            if (_title != null && _title.isNotEmpty()) {
                dialog.setTitle(_title)
            } else {
                dialog.setTitle(_context.getString(R.string.app_name))
            }
            dialog.setMessage(_message)
            dialog.setButton(
                Dialog.BUTTON_POSITIVE, _positiveText,
                _onPositiveClick
            )
            dialog.setCancelable(false)
            dialog.show()
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(_context, R.color.colorBlack))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(ContextCompat.getColor(_context, R.color.colorBlack))
        }

        /**
         *  @param showAlertDialog is called by showErrorDialog
         */
        fun showAlertDialog(
            _context: Context, _title: String?,
            _message: String, _positiveText: String,
            _onPositiveClick: DialogInterface.OnClickListener
        ) {
            val dialog = AlertDialog.Builder(_context).create()
            dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
            if (_title != null && _title.isNotEmpty()) {
                dialog.setTitle(_title)
            } else {
                dialog.setTitle(_context.getString(R.string.app_name))
            }
            dialog.setMessage(_message)
            dialog.setButton(
                Dialog.BUTTON_POSITIVE, _positiveText,
                _onPositiveClick
            )
            dialog.setCancelable(false)
            dialog.show()
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(_context, R.color.colorBlack))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(ContextCompat.getColor(_context, R.color.colorBlack))
        }

    }
}