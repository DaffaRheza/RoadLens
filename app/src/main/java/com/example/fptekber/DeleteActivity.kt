 package com.example.fptekber

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fptekber.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

 class DeleteActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener {
            val vehicleNumber = binding.deleteVehicleNumber.text.toString()
            if (vehicleNumber.isNotEmpty()){
                deleteData(vehicleNumber)
            } else  {
                Toast.makeText(this, "Please enter vehicle number", Toast.LENGTH_SHORT).show()
            }
        }

    }

     private fun deleteData(vehicleNumber: String){
         databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
         databaseReference.child(vehicleNumber).removeValue().addOnSuccessListener {
             binding.deleteVehicleNumber.text.clear()
             Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
         }.addOnFailureListener{
             Toast.makeText(this, "Unable to Delete", Toast.LENGTH_SHORT).show()
         }
     }

}