package nl.kmartin.backflowcalculator.ui.calculator

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import nl.kmartin.backflowcalculator.R
import nl.kmartin.backflowcalculator.base.BaseMVVMFragment
import nl.kmartin.backflowcalculator.databinding.FragmentCalculatorBinding
import nl.kmartin.backflowcalculator.helpers.hideKeyboard

class CalculatorFragment :
    BaseMVVMFragment<FragmentCalculatorBinding, CalculatorViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        applyStatusBarInsets()
    }

    private fun initViews() {
        binding.toolbar.title = getString(R.string.app_name)

        binding.calculatorFormLayout.etFlex.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.calculatorFormLayout.etFlex.clearFocus()
                hideKeyboard()
                true
            } else {
                false
            }
        }
    }

    private fun applyStatusBarInsets() {
        val initialHeight = binding.toolbar.layoutParams.height
        val initialPaddingTop = binding.toolbar.paddingTop

        ViewCompat.setOnApplyWindowInsetsListener(binding.toolbar) { view, windowInsets ->
            val statusBars = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars())

            view.layoutParams = view.layoutParams.apply {
                height = initialHeight + statusBars.top
            }

            view.updatePadding(top = initialPaddingTop + statusBars.top)

            windowInsets
        }

        ViewCompat.requestApplyInsets(binding.toolbar)
    }

    override fun initViewModelBinding() {
        binding.viewModel = viewModel
    }

    override fun getVMClass(): Class<CalculatorViewModel> {
        return CalculatorViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_calculator
    }
}