package com.elewa.sampleandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elewa.sampleandroidapp.base.BaseActivity
import com.elewa.sampleandroidapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}