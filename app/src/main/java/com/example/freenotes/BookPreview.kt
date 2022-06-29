package com.example.freenotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.jatt.freenotes.R

class BookPreview : AppCompatActivity() {

    private lateinit var preview : WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_preview)

        preview = findViewById(R.id.webView)
        val uri : String? = intent.getStringExtra("uri")
        preview.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url="+uri)



    }
}