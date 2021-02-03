package com.example.demoproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.demoproject.posts.ui.fragment.PostListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(PostListFragment.newInstance())
    }

    // Cause making a helper file for such a small thing won't make sense
    private fun addFragment(frag: Fragment){
        val ft: FragmentTransaction = this.supportFragmentManager
        .beginTransaction()
        ft.replace(R.id.demo_placeholder, frag)
        ft.addToBackStack("home")
        ft.commit()
    }
}