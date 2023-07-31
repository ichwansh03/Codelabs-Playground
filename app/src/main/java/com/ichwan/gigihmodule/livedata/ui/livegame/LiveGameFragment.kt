package com.ichwan.gigihmodule.livedata.ui.livegame

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ichwan.gigihmodule.R
import com.ichwan.gigihmodule.databinding.FragmentLiveGameBinding
import com.ichwan.gigihmodule.retrofit.OverviewActivity

class LiveGameFragment : Fragment() {

    private lateinit var binding: FragmentLiveGameBinding

    private val viewModel: LiveGameViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_live_game, container, false)
        Log.d("GameFragment", "GameFragment created or re-created!")
        Log.d("GameFragment", "Word: ${viewModel.currentScrambledWord} " +
                "Score: ${viewModel.score} WordCount: ${viewModel.currentWordCount}")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            gameViewModel = viewModel
            maxNoOfWords = MAX_NO_OF_WORDS
            lifecycleOwner = viewLifecycleOwner

            submit.setOnClickListener { onSubmitWord() }
            skip.setOnClickListener { onSkipWord() }
            test.setOnClickListener { startActivity(Intent(requireContext(), OverviewActivity::class.java)) }
        }
    }

    private fun onSubmitWord() {
        val playerWord = binding.textInputEditText.text.toString()

        if (viewModel.isUserWordCorrect(playerWord)){
            setErrorTextField(false)
            if (!viewModel.nextWord()){
                showFinalScoreDialog()
            }
        } else {
            setErrorTextField(true)
        }
    }

    private fun onSkipWord() {
        if (viewModel.nextWord()) {
            setErrorTextField(false)
        } else {
            showFinalScoreDialog()
        }
    }

    private fun showFinalScoreDialog(){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.congratulations))
            .setMessage(getString(R.string.you_scored, viewModel.score.value))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.exit)) { _, _, ->
                exitGame()
            }
            .setPositiveButton(getString(R.string.play_again)) {_, _, ->
                restartGame()
            }
            .show()
    }

    private fun restartGame(){
        viewModel.reinitializedData()
        setErrorTextField(false)
    }

    private fun exitGame(){
        activity?.finish()
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("GameFragment", "GameFragment destroyed!")
    }

    private fun setErrorTextField(value: Boolean) {
        if (value) {
            binding.textField.isErrorEnabled = true
            binding.textField.error = getString(R.string.try_again)
        } else {
            binding.textField.isErrorEnabled = false
            binding.textInputEditText.text = null
        }
    }

}