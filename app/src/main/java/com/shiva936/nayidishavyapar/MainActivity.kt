package com.shiva936.nayidishavyapar

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.shiva936.nayidishavyapar.ui.theme.NayiDishaVyaparTheme

class MainActivity : ComponentActivity() {
    val auth :FirebaseAuth = FirebaseAuth.getInstance()
    val database :FirebaseDatabase = FirebaseDatabase.getInstance()
    val userref = database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth.signInWithEmailAndPassword("cs22b057@iittp.ac.in","Not my password").addOnCompleteListener {task ->
        if (task.isSuccessful){
            Toast.makeText(applicationContext, auth.currentUser?.uid  ,Toast.LENGTH_SHORT).show()
            println("User ID: ${auth.currentUser?.uid}")
        }
        else{
           // Toast.makeText(applicationContext,task.exception?.toString(),Toast.LENGTH_SHORT).show()
        }
        }
        //Toast.makeText(applicationContext,auth.currentUser.toString(),Toast.LENGTH_SHORT).show()
       retrieveData()
        val testRef:DatabaseReference = database.reference.child("Data")

//        testRef.setValue("Hello, Firebase!")
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    println("Database write successful")
//                } else {
//                    println("Database write failed: ${task.exception?.message}")
//                }
//            }
        enableEdgeToEdge()
        setContent {
            NayiDishaVyaparTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

    }
    fun retrieveData(){
        userref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) println("gi")
                val value = snapshot.child("Data").value
                println("${value.toString()}")

                //Toast.makeText(applicationContext,"Created",Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                println("***********************")
                //Toast.makeText(applicationContext,error.toString(),Toast.LENGTH_SHORT).show()
            }
        })
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NayiDishaVyaparTheme {
        Greeting("Android")
    }
}