package com.nomads.ethanolmixcalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.nomads.ethanolmixcalculator.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var _calculator: EthanolMixCalculator? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        // TODO temp code - should populate with default calculator values if nothing to pull from DB
        _calculator = EthanolMixCalculator(Volume(12.4, Volume.UoM.Gallons), 0.1, 0.75, 0.3, 0.2, 0.1)
        binding.viewModel = FirstFragmentViewModel(_calculator!!)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }

        binding.buttonCalculate.setOnClickListener { view ->
            val result = _calculator!!.calculateMix()

            // TODO set pump gas and pump e85 values on view model here to update UI

            Snackbar.make(view, "Perform calculation", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}