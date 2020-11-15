package com.gitpub.raiffts.ui.new_order.view.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gitpub.raiffts.ui.new_order.NewOrderFragment
import com.gitpub.raiffts.ui.new_order.view.PickStudentFragment

class LyublyuFragmentPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    var onCreateFragment: ((Fragment) -> Unit)? = null

    private val fragmentSupplier: (Int) -> Fragment = {
        when (it) {
            0 -> PickStudentFragment()
            1 -> NewOrderFragment()
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = fragmentSupplier(position)
        onCreateFragment?.invoke(fragment)
        return fragment
    }
}