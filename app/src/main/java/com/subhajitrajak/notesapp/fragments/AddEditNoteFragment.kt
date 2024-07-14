package com.subhajitrajak.notesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.subhajitrajak.notesapp.Note
import com.subhajitrajak.notesapp.NotesDatabaseHelper
import com.subhajitrajak.notesapp.databinding.FragmentAddEditNoteBinding


class AddEditNoteFragment : Fragment() {
    private lateinit var binding: FragmentAddEditNoteBinding
    private lateinit var db: NotesDatabaseHelper
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        db = NotesDatabaseHelper(requireContext())

        binding.saveBtn.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val content = binding.editDescription.text.toString()
            if (title.isEmpty() && content.isEmpty()) {
                Toast.makeText(requireContext(), "Empty note cannot be saved", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                val note = Note(0, title, content)
                db.insertNote(note)
                Toast.makeText(requireContext(), "Note saved successfully", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }
}