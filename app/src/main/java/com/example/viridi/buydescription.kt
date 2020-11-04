import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class Buydescription : AppCompatActivity() {
    lateinit var id : String
    lateinit var img :ImageView
    lateinit var name:TextView
    lateinit var phone:TextView
    lateinit var address:TextView
    lateinit var area:TextView
    lateinit var price:TextView
    lateinit var s_type:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buydescription)
        img = findViewById(R.id.img)
        name =findViewById(R.id.name)
        phone=findViewById(R.id.phone)
        address =findViewById(R.id.address)
        area =findViewById(R.id.area)
        price =findViewById(R.id.price)
        s_type =findViewById(R.id.s_type)

        if( intent != null)
        {
            id= intent.getStringExtra("id").toString()
        }

    }
}
