package com.arbazmateen.commons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arbazmateen.dialogs.SingleSelectDialog

class ExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)

//        val dataList: List<String> = resources.getStringArray(R.array.data_list).toList()

//        startActivity(Intent(this, DialogExampleActivity::class.java))

        SingleSelectDialog(this, listOf("Android", "Java", "Kotlin", "Flutter", "Xamerian")).show()

    }
}
