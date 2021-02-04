package com.polurival.netologyloader.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polurival.netologyloader.R
import com.polurival.netologyloader.databinding.LearningItemBinding
import com.polurival.netologyloader.presentation.model.SubjectItem

/**
 * @author Польщиков Юрий on 04/02/2021
 */
class MainAdapter(private val items: ArrayList<SubjectItem>) : RecyclerView.Adapter<MainAdapter.SubjectViewHolder>() {

    class SubjectViewHolder(private val itemBinding: LearningItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: SubjectItem) {
            itemBinding.subjectTitle.text = item.subjectTitle
            itemBinding.coursesCount.text = itemView.context.resources.getQuantityString(
                R.plurals.courses_plural,
                item.courseCount,
                item.courseCount
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder =
        SubjectViewHolder(
            LearningItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    fun addData(list: List<SubjectItem>?) {
        items.addAll(list.orEmpty())
        notifyDataSetChanged()
    }
}