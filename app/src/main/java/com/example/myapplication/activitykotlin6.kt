package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_community2.*

class activitykotlin6 : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val myDataset: Array<String>
       get() { //에러나서 아무 내용 없이 추가했어요..
            TODO()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community2)

        // recyclerview
        viewManager = LinearLayoutManager(this)
        viewAdapter = adapter(myDataset)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter



        // 뒤로 가기 버튼
        back1.setOnClickListener {
            Log.d("click","click")
//            // 화면 6에서 뒤로 가기 버튼을 눌렀을 때 홈으로 넘어가야 함.
//            // 이을 때 아래의 MainActivity를 진짜 메인 부분의 이름으로 바꿔 줘야 함 !
//            val six_to_home = Intent(this@activitykotlin6, MainActivity ::class.java)
            val intent5 = Intent(this@activitykotlin6, MainActivity::class.java)
            startActivity(intent5)

        }

        // 글 쓰기 버튼
        writereviewbutton.setOnClickListener {
//            //화면 6에서 글쓰기 버튼을 누르면 와이어프레임에 있는 화면 7번으로 넘어가야 함
//            // 이것도 mainactivity 부분 나중에 화면 7번 이름으로 바꿔 줘야 함.
//            val six_to_seven = Intent(this@activitykotlin6, MainActivity ::class.java)
            val intent6 = Intent(this@activitykotlin6, MainActivity2::class.java)
            startActivity(intent6)
        }


//        // 하단 메뉴에서 홈 버튼 눌렀을 때
//            bottom_home.setOnClickListener {
//                // 화면 6에서 하단 탭의 홈을 눌렀을 때, 홈으로 넘어가야함. MainActivity를 홈 부분의 이름으로 바꿔주기
//                val six_to_home = Intent(this@activitykotlin6, MainActivity ::class.java)
//            }
//
//        // 하단 메뉴에서 커뮤니티 버튼 눌렀을 때
//            bottom_communitybutton.setOnClickListener {
//                // 화면 6에서 하단 탭의 커뮤니티 버튼 눌렀을 때, 새로고침(?) 되어야 함.
//                // 새로고침인지 뭔지 기능을 정확히 모르겠어서, 일단 화면 6으로 넘어가게 했습니다.
//                val six_to_six = Intent(this@activitykotlin6, activitykotlin6 ::class.java)
//            }
    }

}
}