package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

private val PICK_IMAGE_REQUEST = 0
private var selectedPhotoUri: Uri?=null
val storage = FirebaseStorage.getInstance()
val db = FirebaseFirestore.getInstance()
val timestamp: String = SimpleDateFormat("MMdd_HHmmss").format(Date())

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        back_button.setOnClickListener {
            community_list.clear()
            val back_intent = Intent(this@MainActivity2, Community::class.java)
            startActivity(back_intent)
        } // 뒤로가기 버튼을 클릭했을 때

        image_upload_button.setOnClickListener {
            val intent2 = Intent(Intent.ACTION_PICK)
            intent2.type = "image/*"
            intent2.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent2, PICK_IMAGE_REQUEST)
        } // 사진 업로드 버튼을 클릭했을 때

        submit_button.setOnClickListener {
            community_list.clear()
            upload_db_content()
            upload_photo(selectedPhotoUri)

            val intent4 = Intent(this@MainActivity2, Community::class.java)
            startActivity(intent4)
        } // 완료 버튼을 클릭했을 때
    }

    fun upload_db_content() {
        val user = FirebaseAuth.getInstance().currentUser
        if(user!=null) {
            val content = write_content.getText().toString()
            val info = hashMapOf(
                "real_content" to timestamp+content,
                "date" to timestamp
            ) // 회원별 글

            db.collection("test")
                .add(info)
        }
    }

    fun upload_photo(photoUri: Uri?) {
        val storageRef = storage.reference
        val storageImageRef = storageRef.child("images")

        val file = photoUri
        val fileRef = storageImageRef.child(timestamp+".png")

            if(file != null) {
                val uploadTask = fileRef.putFile(file)
                    .addOnSuccessListener {
                        Log.d("test", " 업로드에 성공했음")
                    }
                    .addOnFailureListener {
                        Log.d("test", "업로드에 실패했음")
                    }
            }
        }

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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            selectedPhotoUri = data?.data!!
            image_preview.setImageURI(selectedPhotoUri)
        }
    }

}
