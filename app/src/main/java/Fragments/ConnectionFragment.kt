package Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bt_def.Activities.BaseActivity
import com.example.projectink.databinding.FragmentConnectionBinding

class ConnectionFragment : Fragment() {

    private lateinit var binding: FragmentConnectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConnectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBluetoothTo.setOnClickListener {
            val intent = Intent(requireActivity(), BaseActivity::class.java)
            startActivity(intent)
        }
    }
}