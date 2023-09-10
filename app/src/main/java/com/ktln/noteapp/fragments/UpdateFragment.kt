package com.ktln.noteapp.fragments

import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ktln.noteapp.R
import com.ktln.noteapp.databinding.FragmentListBinding
import com.ktln.noteapp.databinding.FragmentUpdateBinding
import com.ktln.noteapp.model.Note
import com.ktln.noteapp.viewModel.NoteViewModel


class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mNoteViewModel:NoteViewModel
    private lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentUpdateBinding.inflate(inflater,container,false)

        mNoteViewModel= ViewModelProvider(this)[NoteViewModel::class.java]

        binding.updateEdTitle.setText(args.currentNote.title)
        binding.updateEdDetail.setText(args.currentNote.detail)

        binding.updateBtn.setOnClickListener {
            updateItem()
        }

        binding.deleteImg.setOnClickListener {
                deleteItem()
        }

        binding.backImg.setOnClickListener {
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }


        return binding.root
    }

    private fun deleteItem() {
        val builder=AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mNoteViewModel.deleteNote(args.currentNote)
            Toast.makeText(requireContext(),"Deleted ${args.currentNote.title}",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        builder.setNegativeButton("No"){_,_->

        }
        builder.setTitle("Delete ${args.currentNote.title}?")
        builder.setMessage("Are u sure ${args.currentNote.title}?")
        builder.create().show()
    }

    private fun updateItem(){
        val title=binding.updateEdTitle.text.toString()
        val detail=binding.updateEdDetail.text.toString()

        if(inputCheck(title,detail)){
            val updatedNote=Note(args.currentNote.id,title,detail)
            mNoteViewModel.updateNote(updatedNote)
            Toast.makeText(requireContext(),"Note Updated!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Pls fill all fields!",Toast.LENGTH_LONG).show()
        }
    }


    private fun inputCheck(title:String,detail:String) : Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(detail))
    }

}