package Fragments

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bt_def.Activities.BaseActivity
import com.example.bt_def.Constants.BluetoothConstans
import com.example.bt_def.bluetooth.BluetoothController
import com.example.projectink.databinding.FragmentConnectionBinding

class ConnectionFragment : Fragment() {

    private lateinit var binding: FragmentConnectionBinding
    private lateinit var bluetoothController: BluetoothController
    private lateinit var bAdapter: BluetoothAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConnectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBtAdapter()
        val preferences = activity?.getSharedPreferences(
            BluetoothConstans.PREFERENCES, Context.MODE_PRIVATE)

        val mac = preferences?.getString(BluetoothConstans.MAC, "")

        bluetoothController = BluetoothController(bAdapter)

        binding.btnBluetoothTo.setOnClickListener {
            val intent = Intent(requireActivity(), BaseActivity::class.java)
            startActivity(intent)
        }
        binding.bConnect.setOnClickListener{
            bluetoothController.connect(mac ?: "")
        }
    }

    private fun initBtAdapter(){
        val bManager = activity?.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bAdapter = bManager.adapter
    }
}