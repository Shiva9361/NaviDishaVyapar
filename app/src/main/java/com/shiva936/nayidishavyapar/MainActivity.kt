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
import com.shiva936.nayidishavyapar.ui.theme.NayiDishaVyaparTheme

class MainActivity : ComponentActivity() {
    val auth :FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth.createUserWithEmailAndPassword("cs22b057@iittp.ac.in","Not my password").addOnCompleteListener {task ->
        if (task.isSuccessful){
            Toast.makeText(applicationContext,"Created",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(applicationContext,task.exception?.toString(),Toast.LENGTH_SHORT).show()
        }
        }
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