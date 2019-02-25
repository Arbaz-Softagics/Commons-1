package com.arbazmateen.commons

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.arbazmateen.dialogs.*
import kotlinx.android.synthetic.main.activity_dialog_example.*

class DialogExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_example)

        info_btn.setOnClickListener {

            infoDialog(
                this,   // context
                "Information",  // title
                "Some informational message",   // message
                true    // cancelable on back press or touch anywhere on the screen
            ).setPositiveButton("OK") { dialog, _ ->
                // Ok button action
                dialog.dismiss()
            }.setNegativeButton("Close") { dialog, _ ->
                // close button action
                dialog.dismiss()
            }.show()

        }

        warning_btn.setOnClickListener {

            warningDialog(
                this,   // context
                "Warning",  // title
                "Some warning message",   // message
                true    // cancelable on back press or touch anywhere on the screen
            ).setPositiveButton("OK") { dialog, _ ->
                // Ok button action
                dialog.dismiss()
            }.setNegativeButton("Close") { dialog, _ ->
                // close button action
                dialog.dismiss()
            }.show()

        }

        error_btn.setOnClickListener {

            errorDialog(
                this,   // context
                "Error",  // title
                "Some error message",   // message
                true    // cancelable on back press or touch anywhere on the screen
            ).setPositiveButton("OK") { dialog, _ ->
                // Ok button action
                dialog.dismiss()
            }.setNegativeButton("Close") { dialog, _ ->
                // close button action
                dialog.dismiss()
            }.show()

        }

        detele_btn.setOnClickListener {

            confirmDelete(
                this,   // context
                "Confirm Delete",  // title
                "Some confirmation message",   // message
                true    // cancelable on back press or touch anywhere on the screen
            ).setPositiveButton("Delete") { dialog, _ ->
                // Ok button action
                dialog.dismiss()
            }.setNegativeButton("Close") { dialog, _ ->
                // close button action
                dialog.dismiss()
            }.show()
            .getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_light))

        }

        wait_btn.setOnClickListener {

            waitDialog(
                this,   // context
                "Loading, Please wait...",  // message
                true    // cancelable on back press or touch anywhere on the screen
            ).show()

        }

        single_selection_btn.setOnClickListener {
//            val list = listOf("Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread",
//                "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "KitKat",
//                "Lollipop", "Marshmallow", "Nougat", "Oreo", "Pie")

            val dataList: List<String> = resources.getStringArray(R.array.data_list).toList()

            SingleSelectDialog(this, dataList)
                .setItemClickListener { _, data, _ ->
                    Toast.makeText(this, data, Toast.LENGTH_LONG).show()
                }
                .show()
        }

        multi_selection_btn.setOnClickListener {

        }

    }
}
