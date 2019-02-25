package com.arbazmateen.commons

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.arbazmateen.adaptors.SimpleRecyclerAdaptor
import kotlinx.android.synthetic.main.activity_example.*

class ExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)

        val list = mutableListOf("Dialogs")

        SimpleRecyclerAdaptor.Builder<String>(this)
            .setLayout(android.R.layout.simple_list_item_1)
            .setDataList(list)
            .addView(android.R.id.text1)
            .setBindViewListener { _, item, _, viewMap ->
                val title = viewMap[android.R.id.text1] as TextView
                title.text = item
            }
            .setItemClickListener { _, position ->
                when(position) {
                    0 -> {
                        startActivity(Intent(this@ExampleActivity, DialogExampleActivity::class.java))
                    }
                }
            }
            .into(recycler_view, divider = true)




    }
}
