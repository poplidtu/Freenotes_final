package com.example.freenotes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freenotes.databinding.FragmentBlankBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList

class BlankFragment : Fragment() {

    lateinit var adapter : Adapter

    var items =ArrayList<book_detail>()

    var displayList = ArrayList<book_detail>()

    var list : RecyclerView?= null

    lateinit var searchView : SearchView

    lateinit var drawerLayout : DrawerLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val binding : FragmentBlankBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_blank,container,false)

        var fab_btn : FloatingActionButton = binding.fabBtn as FloatingActionButton


        items.add(book_detail("Peter Linz","An Introduction to" +"Formal Languages" + "and Automat","https://firebasestorage.googleapis.com/v0/b/booky-bfee3.appspot.com/o/An_Introduction_to_Formal_Languages_and.pdf?alt=media&token=6c2046a1-3d2f-4744-a477-205aafaae001"))
        items.add(book_detail("Bs Grewal","Engineering Mathematics ","https://firebasestorage.googleapis.com/v0/b/booky-bfee3.appspot.com/o/An_Introduction_to_Formal_Languages_and.pdf?alt=media&token=6c2046a1-3d2f-4744-a477-205aafaae001"))
        items.add(book_detail("William Stanlings","Computer Networks ","https://firebasestorage.googleapis.com/v0/b/booky-bfee3.appspot.com/o/An_Introduction_to_Formal_Languages_and.pdf?alt=media&token=6c2046a1-3d2f-4744-a477-205aafaae001"))
        items.add(book_detail("------- "," Computer Organization and Architecture ","https://firebasestorage.googleapis.com/v0/b/booky-bfee3.appspot.com/o/An_Introduction_to_Formal_Languages_and.pdf?alt=media&token=6c2046a1-3d2f-4744-a477-205aafaae001"))
        items.add(book_detail("Silberschatz,Galvin,Gagne","Operating System ","https://firebasestorage.googleapis.com/v0/b/booky-bfee3.appspot.com/o/An_Introduction_to_Formal_Languages_and.pdf?alt=media&token=6c2046a1-3d2f-4744-a477-205aafaae001"))
        items.add(book_detail("--------- ","Digital Logic  ","https://firebasestorage.googleapis.com/v0/b/booky-bfee3.appspot.com/o/An_Introduction_to_Formal_Languages_and.pdf?alt=media&token=6c2046a1-3d2f-4744-a477-205aafaae001"))
        items.add(book_detail("William Stanlings","Computer Organization and Architecture  ","https://firebasestorage.googleapis.com/v0/b/booky-bfee3.appspot.com/o/An_Introduction_to_Formal_Languages_and.pdf?alt=media&token=6c2046a1-3d2f-4744-a477-205aafaae001"))
        items.add(book_detail("--------","Data structure ","https://firebasestorage.googleapis.com/v0/b/booky-bfee3.appspot.com/o/An_Introduction_to_Formal_Languages_and.pdf?alt=media&token=6c2046a1-3d2f-4744-a477-205aafaae001"))
        items.add(book_detail("-------- ","Operating_System_Concepts,_8th_Edition ","https://firebasestorage.googleapis.com/v0/b/booky-bfee3.appspot.com/o/An_Introduction_to_Formal_Languages_and.pdf?alt=media&token=6c2046a1-3d2f-4744-a477-205aafaae001"))
        items.add(book_detail("------- ","database system concepts-6th edition","https://firebasestorage.googleapis.com/v0/b/booky-bfee3.appspot.com/o/An_Introduction_to_Formal_Languages_and.pdf?alt=media&token=6c2046a1-3d2f-4744-a477-205aafaae001"))
        items.add(book_detail("M. Morris mano"," digital-logic-and-computer-design-by-m-morris-mano-2nd-edition","https://firebasestorage.googleapis.com/v0/b/booky-bfee3.appspot.com/o/An_Introduction_to_Formal_Languages_and.pdf?alt=media&token=6c2046a1-3d2f-4744-a477-205aafaae001"))
        items.add(book_detail("Godrich","Data structure ","https://firebasestorage.googleapis.com/v0/b/booky-bfee3.appspot.com/o/An_Introduction_to_Formal_Languages_and.pdf?alt=media&token=6c2046a1-3d2f-4744-a477-205aafaae001"))

        displayList.addAll(items)


        list = binding.bookList


        adapter = Adapter(displayList,context) // data
        list?.layoutManager = LinearLayoutManager(context)
        list?.adapter = adapter    // shows the data in the recycler view

        fab_btn.setOnClickListener {
            val intent : Intent = Intent(activity,UploadBook::class.java)
            activity?.startActivity(intent)
        }

        setHasOptionsMenu(true)

        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu,inflater: MenuInflater) {

            super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menus,menu)

        val search = menu!!.findItem(R.id.search)


        searchView = search.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if(newText != null){
                    displayList.clear()
                    val search =newText.toLowerCase(Locale.getDefault())
                    items.forEach{
                        if(it.authorName.toLowerCase(Locale.getDefault()).contains(search)){

                            displayList.add(it)

                        }
                    }

                    list?.adapter!!.notifyDataSetChanged()



                }
                else{
                    displayList.clear()
                    displayList.addAll(items)

                    list?.adapter!!.notifyDataSetChanged()

                }

                return true
            }


        })





    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.share_app->{

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)



            }
            R.id.rate_us->{
               activity?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$parentFragment")))

            }

            R.id.about_us->{


            }


        }

        return super.onOptionsItemSelected(item)
    }



}


