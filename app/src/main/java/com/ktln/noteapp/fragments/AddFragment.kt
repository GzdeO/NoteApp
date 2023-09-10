package com.ktln.noteapp.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ktln.noteapp.R
import com.ktln.noteapp.model.Note
import com.ktln.noteapp.viewModel.NoteViewModel


import com.ktln.noteapp.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    private lateinit var mNoteViewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAddBinding.inflate(inflater,container,false)

        mNoteViewModel= ViewModelProvider(this).get(NoteViewModel::class.java)

        binding.saveBtn.setOnClickListener {
            insertDataToDatabase()
        }

        binding.backImg.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        return binding.root
    }

    private fun insertDataToDatabase() {
        val title=binding.edTitle.text.toString()
        val detail=binding.edDetail.text.toString()

        if (inputCheck(title,detail)){
            val note= Note(0,title,detail)
            mNoteViewModel.addNote(note)
            Toast.makeText(requireContext(),"Note Saved!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Pls fill all fields !",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title:String,detail:String) : Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(detail))
    }


}