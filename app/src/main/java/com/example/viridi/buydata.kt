import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Buydata : AppCompatActivity() {
    val data : item_data = item_data("11/11/2001/730","Jute")
    lateinit var recyclerdata: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter: DataRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_data)

        recyclerdata = findViewById(R.id.recyclerData)
        layoutManager = LinearLayoutManager(this@Buydata)


        recyclerAdapter= DataRecyclerAdapter(this@Buydata as Context, data  )
        recyclerdata.adapter=recyclerAdapter
        recyclerdata.layoutManager=layoutManager
        recyclerdata.addItemDecoration(
            DividerItemDecoration(
                recyclerdata.context,
                (layoutManager as LinearLayoutManager).orientation
            )
        )
    }
}
