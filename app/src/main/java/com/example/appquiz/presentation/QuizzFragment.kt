package com.example.appquiz.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appquiz.R
import com.example.appquiz.commons.SessionManager
import com.example.appquiz.data.model.QuestionEntity
import com.example.appquiz.databinding.FragmentQuizzBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizzFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizzFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var quizzViewModel: QuizzViewModel
    private lateinit var binding: FragmentQuizzBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizzBinding.inflate(inflater)
        binding.score.text = "Score: " + SessionManager.user.score.toString()
        quizzViewModel = ViewModelProvider(this).get(QuizzViewModel::class.java)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quizzViewModel.apply {
            context?.let { getAllQuestions(it) }
            questions.observe(viewLifecycleOwner, Observer { initQuestions(it) })
        }
    }
    fun initQuestions(questions: List<QuestionEntity>) {
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = QuestionAdapter(questions).apply {
                processAnswer = {question, answer, position -> processInput(question, answer, position)}
            }
        }
    }

    private fun processInput(question: QuestionEntity, input: String, position: Int) {
        val isCorrect: Boolean = question.answer == input
        if (isCorrect) {
            Toast.makeText(context, "Bonne réponse", Toast.LENGTH_LONG).show()
            binding.recyclerview.smoothScrollToPosition(position+1)
            SessionManager.user.score++
        } else {
            Toast.makeText(context, "Mauvaise réponse", Toast.LENGTH_LONG).show()
            SessionManager.user.score--
        }
        binding.score.text = "Score: "+ SessionManager.user.score.toString()
       context?.let {  quizzViewModel.updateUser(SessionManager.user, it) }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuizzFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuizzFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}