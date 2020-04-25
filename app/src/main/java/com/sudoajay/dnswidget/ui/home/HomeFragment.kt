package com.sudoajay.dnswidget.ui.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.CompoundButtonCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputLayout
import com.jaredrummler.materialspinner.MaterialSpinner
import com.sudoajay.dnswidget.R


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var root: View


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_home, container, false)

        reference()

        return root
    }

    private fun reference() {

        val dns1TextInputLayout: TextInputLayout = root.findViewById(R.id.dns1_TextInputLayout)
        val dns2TextInputLayout: TextInputLayout = root.findViewById(R.id.dns2_TextInputLayout)
        val dns3TextInputLayout: TextInputLayout = root.findViewById(R.id.dns3_TextInputLayout)
        val dns4TextInputLayout: TextInputLayout = root.findViewById(R.id.dns4_TextInputLayout)
        val materialSpinner :MaterialSpinner = root.findViewById(R.id.materialSpinner)
        val enableIpv4checkBox: CheckBox = root.findViewById(R.id.enableIpv4_checkBox)
        val enableIpv6checkBox: CheckBox = root.findViewById(R.id.enableIpv6_checkBox)
        val useDns4TextView:TextView = root.findViewById(R.id.useDns4_TextView)
        val useDns6TextView:TextView = root.findViewById(R.id.useDns6_TextView)



        materialSpinner.setItems(homeViewModel.getItemsSpinner())


        enableIpv6checkBox
            .setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    enableIpv4checkBox.isEnabled = true
                    enableIpv4checkBox.alpha = 1f
                    useDns4TextView.alpha = 1f
                    CompoundButtonCompat.setButtonTintList(
                        enableIpv4checkBox, ColorStateList.valueOf(
                            ContextCompat.getColor(requireContext(), R.color.colorPrimary)
                        )
                    )
                    dns3TextInputLayout.hint = getString(R.string.dns3_text)
                    dns2TextInputLayout.visibility = View.VISIBLE
                    dns4TextInputLayout.visibility = View.VISIBLE
                } else {
                    enableIpv4checkBox.isEnabled = false
                    enableIpv4checkBox.alpha = .5f
                    useDns4TextView.alpha = .5f
                    CompoundButtonCompat.setButtonTintList(
                        enableIpv4checkBox, ColorStateList.valueOf(
                            ContextCompat.getColor(requireContext(), R.color.unCheckedColor)
                        )
                    )
                    dns3TextInputLayout.hint = getString(R.string.dns2_text)
                    dns2TextInputLayout.visibility = View.GONE
                    dns4TextInputLayout.visibility = View.GONE
                }

            }

        enableIpv4checkBox
            .setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    enableIpv6checkBox.isEnabled = true
                    enableIpv6checkBox.alpha = 1f
                    useDns6TextView.alpha = 1f
                    CompoundButtonCompat.setButtonTintList(
                        enableIpv6checkBox, ColorStateList.valueOf(
                            ContextCompat.getColor(requireContext(), R.color.colorPrimary)
                        )
                    )
                    dns1TextInputLayout.hint = getString(R.string.dns1_text)
                    dns3TextInputLayout.hint = getString(R.string.dns3_text)
                    dns2TextInputLayout.visibility = View.VISIBLE
                    dns4TextInputLayout.visibility = View.VISIBLE
                } else {
                    enableIpv6checkBox.isEnabled = false
                    enableIpv6checkBox.alpha = .5f
                    useDns6TextView.alpha = .5f
                    CompoundButtonCompat.setButtonTintList(
                        enableIpv6checkBox, ColorStateList.valueOf(
                            ContextCompat.getColor(requireContext(), R.color.unCheckedColor)
                        )
                    )
                    dns1TextInputLayout.hint = getString(R.string.dns3_text)
                    dns3TextInputLayout.hint = getString(R.string.dns4_text)
                    dns2TextInputLayout.visibility = View.GONE
                    dns4TextInputLayout.visibility = View.GONE
                }

            }


        root.findViewById<Button>(R.id.customDns_Button).setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_open_custom_dns)
        }

        root.findViewById<Button>(R.id.dnsTest_Button).setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_open_dns_test)
        }


    }


}
