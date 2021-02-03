package com.example.demoproject.posts.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.demoproject.R
import com.example.demoproject.posts.model.PostModel

class PostPagerAdapter(private val context: Context?, private val list: List<Any>, private val onClick: (index: Int) -> Unit): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)

        val layout = inflater?.inflate(R.layout.fragment_post, container, false) as ViewGroup

        val item = list[position] as PostModel

        bind(item, layout)

        layout.setOnClickListener {
            onClick(position)
        }

        container.addView(layout)
        return layout
    }

    fun bind(item: PostModel, view: View){
        view.findViewById<TextView>(R.id.id).text = item.id
        view.findViewById<TextView>(R.id.userId).text = item.userId
        view.findViewById<TextView>(R.id.name).text = item.title

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageWidth(position: Int): Float {
        return 1f
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}