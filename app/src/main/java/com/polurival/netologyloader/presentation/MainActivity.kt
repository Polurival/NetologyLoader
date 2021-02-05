package com.polurival.netologyloader.presentation

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.polurival.netologyloader.R
import com.polurival.netologyloader.databinding.ActivityMainBinding
import com.polurival.netologyloader.utils.NetologyItemDecorator
import com.polurival.netologyloader.utils.Status
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter

    val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setupTitle()
        setupSubjects()
        setupObserver()
        mainViewModel.requestSubjects()
    }

    private fun setupTitle() {
        val title = getString(R.string.learning_items_title)

        val spannableStringBuilder = SpannableStringBuilder(title)
        val colorSpan = ForegroundColorSpan(resources.getColor(R.color.teal_700))
        spannableStringBuilder.setSpan(
            colorSpan,
            title.split(" ")[0].length,
            title.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        binding.titleTextView.text = spannableStringBuilder
    }

    private fun setupSubjects() {
        mainAdapter = MainAdapter(arrayListOf())
        binding.learningItemsList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(NetologyItemDecorator(context))
            adapter = mainAdapter
        }
    }

    private fun setupObserver() {
        mainViewModel.getUsers().observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    binding.titleTextView.visibility = View.GONE
                    binding.learningItemsList.visibility = View.GONE
                    binding.spinner.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.titleTextView.visibility = View.VISIBLE
                    binding.learningItemsList.visibility = View.VISIBLE
                    binding.spinner.visibility = View.GONE

                    mainAdapter.addData(it.data)
                }
                Status.ERROR -> {
                    binding.titleTextView.visibility = View.GONE
                    binding.learningItemsList.visibility = View.GONE
                    binding.spinner.visibility = View.GONE

                    // todo make it single live event
                    Toast.makeText(
                        this,
                        String.format(getString(R.string.some_error), it.message), Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }
}