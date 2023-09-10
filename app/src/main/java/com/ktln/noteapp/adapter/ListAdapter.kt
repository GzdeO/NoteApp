package com.ktln.noteapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ktln.noteapp.model.Note
import com.ktln.noteapp.databinding.CustomRowBinding
import com.ktln.noteapp.fragments.ListFragmentDirections
import com.ktln.noteapp.viewModel.NoteViewModel


class ListAdapter(private var context: Context) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var noteList= emptyList<Note>()
    class MyViewHolder(val binding:CustomRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=CustomRowBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=noteList[position]
        holder.binding.titleTv.text=currentItem.title
        holder.binding.detailTv.text=currentItem.detail

        holder.binding.updateImg.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(note:List<Note>){
        this.noteList = note
        notifyDataSetChanged()
    }


}