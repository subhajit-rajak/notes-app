package com.subhajitrajak.notesapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class NotesAdapter(private var notes: List<Note>, private val currentUser: String, context: Context) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>()     {
    private val db: NotesDatabaseHelper = NotesDatabaseHelper(context)
    private val auth = FirebaseAuth.getInstance()
    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentVIew)
        val updateBtn: ImageView = itemView.findViewById(R.id.updateBtn)
        val deleteBtn: ImageView = itemView.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content

        holder.updateBtn.setOnClickListener {
            val navController = Navigation.findNavController(holder.itemView)
            val bundle = Bundle()
            bundle.putInt("note_id", note.id)
            navController.navigate(R.id.action_notesListFragment_to_updateNoteFragment, bundle)
        }
        holder.deleteBtn.setOnClickListener {
            db.deleteNote(note.id)
            refreshData()
        }
    }

    fun refreshData() {
        notes = db.getAllNotes(currentUser)
        notifyDataSetChanged()
    }
}