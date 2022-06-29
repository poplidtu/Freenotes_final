package com.example.freenotes

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jatt.freenotes.R

class Adapter(data: ArrayList<book_detail>, var context: Context?) : RecyclerView.Adapter<Adapter.ViewHolder>()
{
    var data : List<book_detail> = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layout = LayoutInflater.from(parent.context).inflate(
            R.layout.item_detail,
            parent,
            false
        )
        return ViewHolder(layout)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val bookDetail = data[position]
        holder.title.text = bookDetail.authorName
        holder.description.text = bookDetail.book_name
        holder.uri = bookDetail.link

        holder.download.setOnClickListener {

            var download = context!!.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            if(URLUtil.isValidUrl(holder.uri.toString())){
                Toast.makeText(context, "url is valid ", Toast.LENGTH_SHORT).show()

            }
            else{
                Toast.makeText(context, "url is not valid ", Toast.LENGTH_SHORT).show()
            }
            var uri = Uri.parse(holder.uri.toString())

            var getBook = DownloadManager.Request(uri)
            getBook.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            download.enqueue(getBook)
        }
        holder.preview.setOnClickListener()  {

            val intent = Intent(context, BookPreview::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("uri",holder.uri.toString())
            context!!.startActivity(intent)

        }




    }



    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item){

        var title: TextView
        var description : TextView

        var download : Button
        var preview : Button
        var uri : String ?=null


        init{

            title = item.findViewById(R.id.title)
            description = item.findViewById(R.id.description)
            download  = item.findViewById(R.id.download)
            preview = item.findViewById(R.id.preview)

        }
    }


}


