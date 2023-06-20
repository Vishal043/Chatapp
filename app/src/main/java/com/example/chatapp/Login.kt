package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

private lateinit var editemail: EditText
private lateinit var editPassword: EditText
private lateinit var btnlogin: Button
private lateinit var btnSignup: Button
private lateinit var mAuth: FirebaseAuth
class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()
        editemail=findViewById(R.id.editemail)
        editPassword=findViewById(R.id.editpassword)
        btnlogin=findViewById(R.id.btnlogin)
       btnSignup=findViewById(R.id.btnsignup)
        btnSignup.setOnClickListener{
            val intent = Intent(this@Login,SignUp::class.java)
            startActivity(intent)
        }
        btnlogin.setOnClickListener{
            val email = editemail.text.toString()
            val password = editPassword.text.toString()
            login(email,password);
        }
    }

    private fun login(email:String,password :String){
       mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@Login,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login,"user does not exist", Toast.LENGTH_SHORT).show()
                }
            }

    }
}