package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val RC_SIGN_IN = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        community_list.clear()
        val user = FirebaseAuth.getInstance().currentUser

        ///////////////////////////////////// 아래부터는 다 제대로 합치기 전 임시 테스트용

        community.setOnClickListener {

            val intent = Intent(this@MainActivity, Community::class.java)
            startActivity(intent)

        } // 임시 커뮤니티 버튼 클릭했을 때

        login.setOnClickListener {
            login()
        } // 로그인 버튼을 클릭했을 때

        logout.setOnClickListener {
            logout()
        } // 로그아웃 버튼을 클릭했을 때
    }

    fun login() {
        val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(), RC_SIGN_IN)
    } // 로그인 함수

    fun logout() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                Log.d("test","로그아웃에 성공하였습니다!")
            }

    } // 로그아웃 함수


    override fun onStart() {
        super.onStart()
        Log.d("life_cycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life_cycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("life_cycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life_cycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life_cycle", "onDestroy")
    }

}
