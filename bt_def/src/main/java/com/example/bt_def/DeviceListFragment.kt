package com.example.bt_def

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bt_def.databinding.FragmentListBinding
import com.google.android.material.snackbar.Snackbar

class DeviceListFragment : Fragment() {

    private lateinit var itemAdapter: ItemAdapter
    private var bAdapter: BluetoothAdapter? = null
    private lateinit var binding: FragmentListBinding
    private lateinit var btLauncher: ActivityResultLauncher<Intent>
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                btLauncher.launch(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE))
            } else {
                Snackbar.make(binding.root, "Необходимо разрешение для включения Bluetooth!", Snackbar.LENGTH_LONG).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imBlueTooth.setOnClickListener{
            requestBluetoothConnectPermission()
        }
        initRcViews()
        registerBtLauncher()
        initBtAdapter()
        bluetoothState()
    }

    private fun initRcViews() = with(binding){
        rcViewPaired.layoutManager = LinearLayoutManager(requireContext())
        itemAdapter = ItemAdapter()
        rcViewPaired.adapter = itemAdapter
    }

    private fun getPairedDevices(){
        try{
            val list = ArrayList<ListItem>()
            val deviceList = bAdapter?.bondedDevices as Set<BluetoothDevice>
            deviceList.forEach{
                list.add(
                    ListItem(
                        it.name,
                        it.address
                    )
                )
            }
            binding.tvEmpty.visibility = if(list.isEmpty()) View.VISIBLE else View.GONE
            itemAdapter.submitList(list)
        } catch (e: SecurityException){

        }

    }

    private fun requestBluetoothConnectPermission() {
        val hasPermission = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.BLUETOOTH_CONNECT
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasPermission) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.BLUETOOTH_CONNECT)) {
                // Здесь показывается объяснение, почему требуется разрешение,
                // и затем вызывается запрос на разрешение.
            } else {
                requestPermissionLauncher.launch(Manifest.permission.BLUETOOTH_CONNECT)
            }
        } else {
            btLauncher.launch(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE))
        }
    }

    private fun initBtAdapter(){
        val bManager = activity?.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bAdapter = bManager.adapter
    }

    private fun bluetoothState(){
        if(bAdapter?.isEnabled == true){
            changeButtonColor(binding.imBlueTooth, android.graphics.Color.GREEN)
            getPairedDevices()
        }
    }

    private fun registerBtLauncher(){
        btLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            if(it.resultCode == Activity.RESULT_OK){
                changeButtonColor(binding.imBlueTooth, android.graphics.Color.GREEN)
                getPairedDevices()
                Snackbar.make(binding.root, "Блютуз включен!", Snackbar.LENGTH_LONG).show()
            } else{
                Snackbar.make(binding.root, "Блютуз выключен!", Snackbar.LENGTH_LONG).show()
            }
        }
    }
    // Добавьте функцию для изменения цвета кнопки, которая будет вызываться здесь.
}
