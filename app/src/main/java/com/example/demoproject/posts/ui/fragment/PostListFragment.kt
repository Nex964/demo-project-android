package com.example.demoproject.posts.ui.fragment

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.demoproject.R
import com.example.demoproject.posts.contracts.PostListContract
import com.example.demoproject.posts.model.PostModel
import com.example.demoproject.posts.presenters.PostListPresenter
import com.example.demoproject.posts.ui.adapters.PostPagerAdapter
import com.example.demoproject.utils.toPixels
import kotlinx.android.synthetic.main.fragment_post_list.*
import java.lang.ref.WeakReference
import kotlin.math.abs

class PostListFragment : Fragment(), PostListContract.View {

    private lateinit var postAdapter: PostPagerAdapter
    private lateinit var presenter: PostListContract.Presenter

    // Variables
    private val postList: ArrayList<PostModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_list, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setupPager()
//        addListeners()

        presenter.loadPost(0)
    }

    private fun initialize(){
        presenter = PostListPresenter(WeakReference(this))
    }

    private fun setupPager(){

        post_viewpager?.setPageTransformer(true) { view, position ->
            val newPos = position - 0.7

            if(newPos > 0f){
                view.scaleX = abs(0.84f - (newPos/4f)).toFloat()
                view.scaleY = abs(0.84f - (newPos/4f)).toFloat()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.translationZ = abs(0.84f - (newPos/4f)).toFloat()
                }
            }
            else{
                view.scaleX = abs(0.84f + (newPos/4f)).toFloat()
                view.scaleY = abs(0.84f + (newPos/4f)).toFloat()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.translationZ = abs(0.84f + (newPos/4f)).toFloat()
                }
            }
        }

        post_viewpager.clipToPadding = false
        post_viewpager.setPadding((Resources.getSystem().displayMetrics.widthPixels * 0.3).toInt(), 0, (Resources.getSystem().displayMetrics.widthPixels * 0.3).toInt(), 0)

        postAdapter = PostPagerAdapter(context, postList) {
            post_viewpager.currentItem = it
        }
        post_viewpager.adapter = postAdapter
        post_viewpager.offscreenPageLimit = 5

        val dp8 = toPixels(64f, context)
        post_viewpager.pageMargin = -dp8.toInt()
    }

    // Eventually this became useless "_"
    fun addListeners(){
        post_viewpager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                postList.forEachIndexed { index, postModel ->
                    postModel.selected = false
                    if(index == position){
                        postModel.selected = true
                    }
                }
            }
        })
    }

    override fun updateData(posts: List<PostModel>) {

        // it's a thing for you, so I know that code is well checked
        // and it is checked properly by a relevant person
        // mail solution at 'firebeast@gmail.com'

        // TODO: MEMORY
        // Some memory can be saved here
        // Difficulty : Easy pizzy, lemon cheesy
        val newList = ArrayList<PostModel>()
        newList.addAll(posts)
        newList.addAll(postList)

        postList.clear()
        postList.addAll(newList.distinctBy { it.id })
        postAdapter.notifyDataSetChanged()

        // Transform fix hack
        post_viewpager.currentItem = postList.size / 2

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PostListFragment().apply {}
    }

}