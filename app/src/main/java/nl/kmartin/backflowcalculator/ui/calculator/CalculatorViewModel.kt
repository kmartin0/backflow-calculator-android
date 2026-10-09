package nl.kmartin.backflowcalculator.ui.calculator


import nl.kmartin.backflowcalculator.base.BaseViewModel
import nl.kmartin.backflowcalculator.form.CalculatorForm

class CalculatorViewModel: BaseViewModel() {
    val calculatorForm = CalculatorForm()

    fun resetForm() {
        calculatorForm.reset()
    }

}
