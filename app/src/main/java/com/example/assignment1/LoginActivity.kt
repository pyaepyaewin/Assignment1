package com.example.assignment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        loginButton.setOnClickListener {
            if(editTextUserName.text.toString()=="Pyae Pyae" && editTextPassword.text.toString()=="12345")
            {
            goToMainActivity ()
            }
        else
            {
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
            }}



    }
private fun goToMainActivity()
{
    startActivity(MainActivity.newIntent(this))
}
}