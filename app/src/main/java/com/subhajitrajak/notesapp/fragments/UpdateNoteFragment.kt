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
import com.subhajitrajak.notesapp.databinding.FragmentUpdateNoteBinding

class UpdateNoteFragment : Fragment() {
    private lateinit var binding: FragmentUpdateNoteBinding
    private lateinit var navController: NavController
    private lateinit var db: NotesDatabaseHelper
    private var noteId: Int = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        db = NotesDatabaseHelper(requireContext())

        noteId = arguments?.getInt("note_id", -1) ?: -1
        if (noteId == -1) {
            navController.popBackStack()
            return
        }

        val note = db.getNoteById(noteId)
        binding.editTitle.setText(note.title)
        binding.editDescription.setText(note.content)

        binding.saveBtn.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val content = binding.editDescription.text.toString()
            val updatedNote = Note(noteId, title, content)
            db.updateNote(updatedNote)
            navController.popBackStack()
            Toast.makeText(requireContext(), "Changes saved", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }
}