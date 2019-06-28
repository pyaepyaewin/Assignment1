package com.example.assignment1

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    val SAVE_PROFILE = 2
    val REQUEST_IMAGE_CAPTURE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSearch.setOnClickListener {
            var search=editTextSearch.text.toString()

            openWebPage(search)

        }

        imageButtonCamera.setOnClickListener {

            val intent = Intent(Intent.ACTION_GET_CONTENT)

            intent.type = "image/*"

            if (intent.resolveActivity(packageManager) != null) {
                        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
                    }
                }
        imageButtonPhone.setOnClickListener {
            if(!editPhoneNumber.text.toString().equals("")) {
                Toast.makeText(this,"Phone Call",Toast.LENGTH_LONG).show()
                callPhNumber()
            }
            else
            {
                Toast.makeText(this,
                    "Phone Number is invalid",Toast.LENGTH_LONG).show()
            }
        }

            }
    fun callPhNumber()
        {
            try {
                if (Build.VERSION.SDK_INT > 22) {
                    if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), 2)
                        return
                    }

                    val callIntent = Intent(Intent.ACTION_CALL)
                    callIntent.data = Uri.parse("tel:"+editPhoneNumber.text.trim())
                    startActivity(callIntent)

                } else {
                    val callIntent = Intent(Intent.ACTION_CALL)
                    callIntent.data = Uri.parse("tel:"+editPhoneNumber.text.trim())
                    startActivity(callIntent)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        }

        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if(requestCode == 2 )
            {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    callPhNumber()
                }
                else
                {
                    Log.e("error","Permission is granted")
                }
            }
        }

    companion object
    {

        fun newIntent(context: Context):Intent
        {
            val intent=Intent(context,MainActivity::class.java)
            return intent
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

            val thumbnail:Uri= data!!.data!!
            Glide.with(this).load(thumbnail).into(imageViewCircle)

        }


    fun openWebPage(url: String) {
        val intents = Intent(Intent.ACTION_WEB_SEARCH).apply {
            putExtra(SearchManager.QUERY,url)

        }
        startActivity(intents)
    }

}
