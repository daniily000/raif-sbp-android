package com.gitpub.raiffts.ui.new_order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gitpub.raiffts.databinding.FragmentNewOrderContainerBinding
import com.gitpub.raiffts.ui.new_order.view.PickStudentFragment
import com.gitpub.raiffts.ui.new_order.view.adapters.LyublyuFragmentPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class NewOrderContainerFragment : Fragment() {

    private lateinit var binding: FragmentNewOrderContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = initializeViewBinding(inflater, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUi()
        initializeViewModel()

    }

    private fun initializeViewBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentNewOrderContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initializeUi() {
        binding.apply {
            viewpager.adapter = LyublyuFragmentPagerAdapter(this@NewOrderContainerFragment).apply {
                onCreateFragment = {
                    (it as? PickStudentFragment)?.onItemClicked = {
                        viewpager.currentItem = 1
                    }
                }
            }

            TabLayoutMediator(tabLayout, viewpager) { tab, position ->
                tab.text = getPageTitle(position)
            }.attach()
        }
    }

    private fun getPageTitle(position: Int): CharSequence =
        when (position) {
            0 -> "Текущий ученик"
            1 -> "Новый ученик"
            else -> throw IllegalArgumentException()
        }

    private fun initializeViewModel() {

    }
}