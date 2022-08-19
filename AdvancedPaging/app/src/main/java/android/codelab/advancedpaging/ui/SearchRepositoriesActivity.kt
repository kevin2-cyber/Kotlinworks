package android.codelab.advancedpaging.ui


import android.codelab.advancedpaging.databinding.ActivitySearchRepositoriesBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SearchRepositoriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchRepositoriesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}