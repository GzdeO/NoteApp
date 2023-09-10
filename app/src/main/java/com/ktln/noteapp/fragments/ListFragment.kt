package com.ktln.noteapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ktln.noteapp.R
import com.ktln.noteapp.adapter.ListAdapter
import com.ktln.noteapp.viewModel.NoteViewModel
import com.ktln.noteapp.databinding.FragmentListBinding


class ListFragment : Fragment() {
    private lateinit var binding:FragmentListBinding

    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding= FragmentListBinding.inflate(inflater,container,false)


        val adapter=ListAdapter(requireContext())
        val recyclerView=binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager=GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)//LinearLayoutManager(requireContext())


        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        mNoteViewModel.readAllData.observe(viewLifecycleOwner, Observer {note->
            adapter.setData(note)
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        binding.deleteAll.setOnClickListener {
            deleteAllNotes()
        }
         return binding.root

    }

    private fun deleteAllNotes() {
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mNoteViewModel.deleteAllNotes()
            Toast.makeText(requireContext(),"Deleted everything", Toast.LENGTH_LONG).show()
        }

        builder.setNegativeButton("No"){_,_->

        }
        builder.setTitle("Delete everything ?")
        builder.setMessage("Are u sure you want to delete everything?")
        builder.create().show()
    }


}