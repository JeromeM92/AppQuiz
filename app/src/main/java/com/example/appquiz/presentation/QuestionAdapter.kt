package com.example.appquiz.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appquiz.R
import com.example.appquiz.data.model.QuestionEntity

class QuestionAdapter(var questions: List<QuestionEntity>): RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    //fonction lambda
    var processAnswer: ((QuestionEntity, String, Int) -> Unit)? = null/*Unit égal void en JAVA*/

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.question)
        val reponse: EditText = itemView.findViewById(R.id.answerInput)
        val validate: TextView = itemView.findViewById(R.id.validate)
    }

    //instanciation du viewholder (définition du viewholder)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return QuestionViewHolder(view)
    }

    //Nombre d'element qu'affiche le recyclerview
    override fun getItemCount(): Int {
        return questions.size
    }

    // association du layout du viewholder à un élement de notre liste (liste de questions ici)
    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
            holder.title.text = questions[position].text
            holder.validate.setOnClickListener {
                processAnswer?.invoke(questions[position], holder.reponse.text.toString(), position) }
    }
}