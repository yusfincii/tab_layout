package com.example.tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tablayout.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    // binding
    private lateinit var binding : ActivityMainBinding

    // arraylist which hold fragments
    var fragmentList  = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialize
        fragmentList = ArrayList()
        // adding
        fragmentList.add(FragmentFirst())
        fragmentList.add(FragmentSecond())
        fragmentList.add(FragmentThird())

        // creating obect from adapter class
        val adapter = Adapter(this)
        binding.viewPager.adapter = adapter

        // list which holds titles of fragments
        val titleList =  ArrayList<String>()
        titleList.add("One")
        titleList.add("Two")
        titleList.add("Three")

        // matching between viewPager and tabLayout
        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            tab.text = titleList[position]
        }.attach()

        // icon assigning
        binding.tabLayout.getTabAt(0)!!.setIcon(R.drawable.one)
        binding.tabLayout.getTabAt(1)!!.setIcon(R.drawable.two)
        binding.tabLayout.getTabAt(2)!!.setIcon(R.drawable.three)
    }

    // Adapter class
    inner class Adapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }

    }
}