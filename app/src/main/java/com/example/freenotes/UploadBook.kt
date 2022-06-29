package com.example.freenotes

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.jatt.freenotes.R
import java.io.File
import java.util.*

class UploadBook : AppCompatActivity() {

    var upload_btn: Button? = null
    var book_name: EditText? = null
    var book_des: EditText? = null
    //    var image: ImageView? = null
    var imageURI: Uri? = null
    var select: TextView? = null
    private var storage: FirebaseStorage? = null
    private var bookRef: StorageReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_book2)

        upload_btn = findViewById(R.id.upload_btn)
        book_name = findViewById(R.id.user_detail)
        book_des = findViewById(R.id.author_name)
        storage = FirebaseStorage.getInstance()
       bookRef = storage?.reference?.child("book")
        select = findViewById(R.id.upload_book)

        val actionBar = supportActionBar
        actionBar!!.title = "Upload"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        select?.setOnClickListener {
            getBook()

        }
        upload_btn?.setOnClickListener {

            AddDetailtoFirebAse()

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
        onBackPressed()
       // startActivity(Intent(this,DashBoard::class.java))
        return true
    }

    private fun getBook() {
        val gallary = Intent()
        gallary.type = "application/pdf"
        gallary.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(gallary, 111)


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == 111 && resultCode == Activity.RESULT_OK && data != null) {
            imageURI = data.data

        if(imageURI != null) {
            val string : String = "Now click upload button"
            select?.setText(string)

        }


        }
    }

    private fun AddDetailtoFirebAse() {

        if (imageURI != null) {

            val file = Uri.fromFile(File(imageURI.toString()))
            val riversRef: StorageReference = bookRef!!.child(UUID.randomUUID().toString())
            //  val ref = imageRef?.child(UUID.randomUUID().toString())
            riversRef.putFile(file).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "note uploaded fine :) ", Toast.LENGTH_SHORT).show()
                } else {
                    var error = task.exception?.message
                    Toast.makeText(applicationContext, "Error " + error, Toast.LENGTH_LONG).show()
                }
            }

        }


        }
}