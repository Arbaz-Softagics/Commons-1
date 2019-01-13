package com.arbazmateen.commons

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)

//        val dataList: List<String> = resources.getStringArray(R.array.data_list).toList()

        startActivity(Intent(this, DialogExampleActivity::class.java))


    }
}
