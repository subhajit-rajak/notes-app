package com.subhajitrajak.notesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.subhajitrajak.notesapp.NotesAdapter
import com.subhajitrajak.notesapp.NotesDatabaseHelper
import com.subhajitrajak.notesapp.R
import com.subhajitrajak.notesapp.databinding.FragmentNotesListBinding


class NotesListFragment : Fragment() {
    private lateinit var binding: FragmentNotesListBinding
    private lateinit var navController: NavController
    private lateinit var db: NotesDatabaseHelper
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        auth = FirebaseAuth.getInstance()
        db = NotesDatabaseHelper(requireContext())
        notesAdapter = NotesAdapter(db.getAllNotes(auth.currentUser?.uid!!), auth.currentUser?.uid!!, requireContext())
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = notesAdapter

        binding.userName.text = auth.currentUser?.displayName.toString()

        binding.addBtn.setOnClickListener {
            navController.navigate(R.id.action_notesListFragment_to_addEditNoteFragment)
        }

        binding.logoutBtn.setOnClickListener {
            auth.signOut()
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
            val go = GoogleSignIn.getClient(requireContext(), gso)
            go.signOut()
            navController.popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData()
    }
}