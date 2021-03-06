package com.sudoajay.dnswidget.ui.appFilter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sudoajay.dnswidget.R
import com.sudoajay.dnswidget.databinding.LayoutMoreFilterAppBottomSheetBinding


class MoreFilterAppBottomSheet : BottomSheetDialogFragment() {

    private var isSelectedAppBottomSheetFragment: FilterAppBottomSheet.IsSelectedBottomSheetFragment? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val myDrawerView =
            layoutInflater.inflate(R.layout.layout_more_filter_app_bottom_sheet, null)
        val binding = LayoutMoreFilterAppBottomSheetBinding.inflate(
            layoutInflater,
            myDrawerView as ViewGroup,
            false
        )
        binding.bottomSheet = this

        isSelectedAppBottomSheetFragment = activity as FilterAppBottomSheet.IsSelectedBottomSheetFragment?


        return binding.root
    }

    fun setValue(key: String, value: String) {
        requireContext().getSharedPreferences("state", Context.MODE_PRIVATE).edit()
            .putString(key, value).apply()
        isSelectedAppBottomSheetFragment!!.handleDialogClose()

        dismiss()
    }



}