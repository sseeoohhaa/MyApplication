package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_community2.*
import kotlinx.android.synthetic.main.activity_community2.back1
import kotlinx.android.synthetic.main.activity_community2.writereviewbutton

val user = FirebaseAuth.getInstance().currentUser
val community_list = ArrayList<ContentforList>()

class Community : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community2)
        community_list.clear()

        back1.setOnClickListener {
//            // 이을 때 아래의 MainActivity를 진짜 메인 부분의 이름으로 바꿔 줘야 함 !
            val intent5 = Intent(this@Community, MainActivity::class.java)
            startActivity(intent5)

        } // 뒤로가기 버튼 클릭했을 때
        writereviewbutton.setOnClickListener {
//            // 이것도 mainactivity 부분 나중에 화면 7번 이름으로 바꿔 줘야 함.
            val intent6 = Intent(this@Community, MainActivity2::class.java)
            startActivity(intent6)
        } // 글쓰기 버튼 클릭했을 때

        if (user != null) {
            val list = db.collection("test")
                .orderBy("date", Query.Direction.DESCENDING)
                .addSnapshotListener { snapshot, e ->
                    for (doc in snapshot!!) {
                        doc.getString("real_content")?.let {
                           val  get_content = it.substring(11..it.length-1)
                            val get_date = it.substring(0..10)
                            val storageRef = storage.reference.child("images").child(get_date + ".png")
                            storageRef.getBytes(4096 * 4096).addOnCompleteListener {
                                if (it.isSuccessful) {
                                    val result: Bitmap = byteArrayToBitmap(it.result!!)
                                    community_list.add(ContentforList(get_content, result))
                                }
                                val adapter = RecyclerAdapter(community_list, LayoutInflater.from(this@Community))
                                Log.d("test", "adapter 생성 완료")
                                recycler_view.adapter = adapter
                            }
                        }
                    }

                    }

                recycler_view.layoutManager = LinearLayoutManager(this@Community)
                Log.d("test", "뷰 그리기")

                }
        }
    }








class RecyclerAdapter(val itemList: ArrayList<ContentforList>, val inflater: LayoutInflater) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val print_content: TextView
        val community_photo: ImageView
        init {
            print_content = itemView.findViewById(R.id.print_Content)
            community_photo = itemView.findViewById(R.id.community_Photo)
        } // 초기값 설정
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    } // 뷰 생성

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.print_content.setText(itemList[position].db_content)
        holder.community_photo.setImageBitmap(itemList[position].db_image)
    } // 뷰 내용을 채워주는 곳

    override fun getItemCount(): Int {
        return itemList.size
    }
}

class ContentforList(val db_content: String, val db_image: Bitmap) {}

fun byteArrayToBitmap(byteArry: ByteArray): Bitmap {
    var bitmap:Bitmap?=null
    bitmap =BitmapFactory.decodeByteArray(byteArry,0,byteArry.size)
    return bitmap
} // 비트맵 변환 함수