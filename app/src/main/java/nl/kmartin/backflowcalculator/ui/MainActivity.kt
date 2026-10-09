package nl.kmartin.backflowcalculator.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import nl.kmartin.backflowcalculator.BuildConfig
import nl.kmartin.backflowcalculator.R
import nl.kmartin.backflowcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        initViewBinding()
        configureSystemBars()
        applyNavigationBarInsets()
    }

    private fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvVersion.text =
            getString(R.string.app_version, BuildConfig.VERSION_NAME)
    }

    private fun configureSystemBars() {
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = false
    }

    /**
     * Applies the navigation bar inset to the activity content.
     */
    private fun applyNavigationBarInsets() {
        val initialBottomPadding = binding.root.paddingBottom

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val navigationBars = insets.getInsets(
                WindowInsetsCompat.Type.navigationBars()
            )

            view.updatePadding(
                bottom = initialBottomPadding + navigationBars.bottom
            )

            insets
        }
    }
}