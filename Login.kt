package com.example.dix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Intent
   import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth



class Login : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPasword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth=FirebaseAuth.getInstance()

        supportActionBar?.hide()

        edtEmail=findViewById(R.id.edt_email)
        edtPasword=findViewById(R.id.edt_password)
        btnLogin=findViewById(R.id.btn_Login)
        btnSignup=findViewById(R.id.btn_Signup)

        btnSignup.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener{
            val email = edtEmail.text.toString()
            val password=edtPasword.text.toString()

            login(email,password);

        }

    }
    private fun login(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent=Intent(this@Login,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(this@Login, "Пользователя не существует", Toast.LENGTH_SHORT).show()
                }
            }

    }
}