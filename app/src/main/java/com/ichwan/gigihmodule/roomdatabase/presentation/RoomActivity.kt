package com.ichwan.gigihmodule.roomdatabase.presentation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ichwan.gigihmodule.R
import com.ichwan.gigihmodule.databinding.ActivityRoomBinding
import com.ichwan.gigihmodule.roomdatabase.data.Word
import com.ichwan.gigihmodule.roomdatabase.di.WordViewModel
import com.ichwan.gigihmodule.roomdatabase.di.WordViewModelFactory
import com.ichwan.gigihmodule.roomdatabase.di.WordsApplication

class RoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding

    private val adapter = WordListAdapter()
    private val wordViewModel: WordViewModel by viewModels{
        WordViewModelFactory((application as WordsApplication).repository)
    }

    private val newWordActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let {
                    val word = Word(it)
                    wordViewModel.insert(word)
                }
            } else {
                Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@RoomActivity)
        }

        wordViewModel.allWords.observe(this) { words ->
            words?.let { adapter.submitList(it) }
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this@RoomActivity, NewWordActivity::class.java)
            newWordActivityLauncher.launch(intent)
        }
    }
}