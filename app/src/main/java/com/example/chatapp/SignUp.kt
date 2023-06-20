package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

private lateinit var edtName : EditText
private lateinit var editemail: EditText
private lateinit var editPassword: EditText
private lateinit var btnsignup: Button
private lateinit var mAuth: FirebaseAuth
private lateinit var mDbRef : DatabaseReference
class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()
        mAuth= FirebaseAuth.getInstance()
        edtName= findViewById(R.id.editename)

        editemail=findViewById(R.id.editemail)
        editPassword=findViewById(R.id.editpassword)
       btnsignup =findViewById(R.id.btnSignup)
        btnsignup.setOnClickListener{
            val name = edtName.text.toString()
            val email = editemail.text.toString()
            val password = editPassword.text.toString()

            signup(name,email,password);
        }
    }
    private fun signup(name: String, email:String,password:String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    addUserToDatabase(name,email, mAuth.currentUser?.uid!!)
                    val intent = Intent(this@SignUp,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
               Toast.makeText(this@SignUp,"some error occurred",Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun addUserToDatabase(name: String,email: String,uid:String){
  mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name, email, uid))
    }

}