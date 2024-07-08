package com.subhajitrajak.notesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.subhajitrajak.notesapp.databinding.FragmentAddEditNoteBinding


class AddEditNoteFragment : Fragment() {
    private lateinit var binding: FragmentAddEditNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

    }
}