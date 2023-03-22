package com.snick.pdf_reader_translator.presentation.files

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.snick.pdf_reader_translator.R
import com.snick.pdf_reader_translator.databinding.FilesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilesFragment : Fragment() {

    private var _binding: FilesFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FilesViewModel>()
    private val requestStoragePermissionLauncher = registerForActivityResult(
        RequestPermission(),
        ::onGotStoragePermissionResult
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FilesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FilesAdapter(onFileClick = {
            val direction = FilesFragmentDirections.actionFilesFragmentToDetailsFragment(it.absolutePath)
            findNavController().navigate(direction)
        })
        binding.filesAdapter.adapter = adapter
        binding.filesAdapter.layoutManager = GridLayoutManager(requireContext(), 3)
        requestStoragePermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)

        viewModel.pdfFiles.observe(viewLifecycleOwner) {
            adapter.setupAdapter(it)
        }


    }


    private fun onGotStoragePermissionResult(granted: Boolean) {
        if (granted) {
            val file = Environment.getExternalStorageDirectory()
            viewModel.getPdfFiles(file)
        } else {
            if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                askUserForOpeningAppSettings()
            } else {
                requestStoragePermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }


    private fun askUserForOpeningAppSettings() {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", requireActivity().packageName, null)
        )
        if (requireActivity().packageManager.resolveActivity(
                appSettingsIntent,
                PackageManager.MATCH_DEFAULT_ONLY
            ) != null
        ) {

            AlertDialog.Builder(requireContext())
                .setTitle(R.string.permission_denied)
                .setMessage(R.string.permission_denied_forever_message)
                .setPositiveButton(R.string.open) { _, _ ->
                    startActivity(appSettingsIntent)
                }
                .create()
                .show()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}