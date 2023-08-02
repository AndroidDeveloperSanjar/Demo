package uz.example.demo.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.example.demo.databinding.ActivityMainBinding

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: RootViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}