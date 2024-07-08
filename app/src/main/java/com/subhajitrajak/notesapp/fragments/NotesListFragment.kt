package com.subhajitrajak.notesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.subhajitrajak.notesapp.NotesAdapter
import com.subhajitrajak.notesapp.NotesDatabaseHelper
import com.subhajitrajak.notesapp.R
import com.subhajitrajak.notesapp.databinding.FragmentNotesListBinding


class NotesListFragment : Fragment() {
    private lateinit var binding: FragmentNotesListBinding
    private lateinit var navController: NavController
    private lateinit var db: NotesDatabaseHelper
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        db = NotesDatabaseHelper(requireContext())
        notesAdapter = NotesAdapter(db.getAllNotes(), requireContext())
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = notesAdapter

        binding.addBtn.setOnClickListener {
            navController.navigate(R.id.action_notesListFragment_to_addEditNoteFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }

    companion object {

    }
}