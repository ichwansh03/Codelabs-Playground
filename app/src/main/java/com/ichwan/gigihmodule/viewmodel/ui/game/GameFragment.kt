package com.ichwan.gigihmodule.viewmodel.ui.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ichwan.gigihmodule.R
import com.ichwan.gigihmodule.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var bind: FragmentGameBinding

    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentGameBinding.inflate(inflater, container, false)
        Log.d("GameFragment", "GameFragment created or re-created!")
        Log.d("GameFragment", "Word: ${viewModel.currentScrambledWord} " +
                "Score: ${viewModel.score} WordCount: ${viewModel.currentWordCount}")
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.apply {
            submit.setOnClickListener { onSubmitWord() }
            skip.setOnClickListener { onSkipWord() }

            updateNextWordOnScreen()
            score.text = getString(R.string.score, viewModel.score)
            wordCount.text = getString(R.string.word_count, viewModel.currentWordCount, MAX_NO_OF_WORDS)
        }
    }

    private fun onSubmitWord() {
        val playerWord = bind.textInputEditText.text.toString()

        if (viewModel.isUserWordCorrect(playerWord)){
            setErrorTextField(false)
            if (viewModel.nextWord()){
                updateNextWordOnScreen()
            } else {
                showFinalScoreDialog()
            }
        } else {
            setErrorTextField(true)
        }
    }

    private fun onSkipWord() {
        if (viewModel.nextWord()) {
            setErrorTextField(false)
            updateNextWordOnScreen()
        } else {
            showFinalScoreDialog()
        }
    }

    private fun showFinalScoreDialog(){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.congratulations))
            .setMessage(getString(R.string.you_scored, viewModel.score))
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
        updateNextWordOnScreen()
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
            bind.textField.isErrorEnabled = true
            bind.textField.error = getString(R.string.try_again)
        } else {
            bind.textField.isErrorEnabled = false
            bind.textInputEditText.text = null
        }
    }

    private fun updateNextWordOnScreen() {
        bind.textViewUnscrambledWord.text = viewModel.currentScrambledWord
    }
}