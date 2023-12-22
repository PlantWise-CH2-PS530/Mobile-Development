package com.dicoding.plantwiseapp.ui.profile

import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.dicoding.plantwiseapp.R
import de.hdodenhof.circleimageview.CircleImageView


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel by viewModels<ProfileViewModel> {
        ProfileViewModelFactory.getInstance(requireContext())
    }

    private val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                viewModel.saveProfileImage(it.toString())
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val imageProfile: CircleImageView = view.findViewById(R.id.imageProfile)
        val editProfileImageIcon: ImageView = view.findViewById(R.id.editProfileImageIcon)
        val editNameIcon: ImageView = view.findViewById(R.id.editNameIcon)
        val textUsername: TextView = view.findViewById(R.id.textUsername)

        viewModel.getProfile().observe(viewLifecycleOwner) { profile ->
            profile?.let {
                textUsername.text = it.username
                Glide.with(requireContext()).load(it.avatarUrl).into(imageProfile)
            }
        }

        editProfileImageIcon.setOnClickListener {
            getImageContent.launch("image/*")
        }

        editNameIcon.setOnClickListener {
            showEditUsernameDialog(viewModel.getUsernameFromPrefs())
        }
        return view
    }

    private fun showEditUsernameDialog(savedUsername: String) {
        val builder = AlertDialog.Builder(requireContext())
        val editText = EditText(requireContext())
        editText.setText(savedUsername)
        builder.setTitle("Edit Username")
            .setView(editText)
            .setPositiveButton("Save") { _, _ ->
                val newUsername = editText.text.toString()
                viewModel.saveUsername(newUsername)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }
}