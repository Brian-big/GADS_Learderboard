package com.brian_big.gadsleaderboard.ui

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.brian_big.gadsleaderboard.R
import com.brian_big.gadsleaderboard.services.FormClientInstance
import com.brian_big.gadsleaderboard.services.FormService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_submit.*
import retrofit2.Call
import retrofit2.Response

class SubmitActivity : AppCompatActivity() {

    private var fName: String? = null
    private var lName: String? = null
    private var email: String? = null
    private var link: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        setActionBar(toolBar)

        btnSubmit.setOnClickListener {
            fName = etFirstName.text.toString()
            lName = etLastName.text.toString()
            email = etEmail.text.toString()
            link = etLink.text.toString()

            if (fName!!.isNotEmpty() && lName!!.isNotEmpty()
                && email!!.isNotEmpty() && link!!.isNotEmpty()
            ){
                showConfirmationDialog()
            }
            else
                makeToast("Fill All Fields")
        }
    }
    private fun makeToast(text: String, length: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this, text, length).show()
    }
    private fun postData(fName: String, lName: String, email: String, link: String) {
        val service = FormClientInstance.retrofitInstance!!.create(FormService::class.java)
        val call = service.postData(fName, lName, email, link)
        call.enqueue(object : retrofit2.Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                prompt(R.layout.dialog_fail)
                Log.d("t", t.message!!)
            }
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    prompt(R.layout.dialog_success)
                    Log.d("response success", response.message())
                }
                else{
                    prompt(R.layout.dialog_fail)
                    Log.d("response ", response.body().toString())
                    Toast.makeText(this@SubmitActivity, response.body().toString(), Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun showConfirmationDialog() {
        val dialog = Dialog(this@SubmitActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_confirm)
        val btnCancel = dialog.findViewById<ImageButton>(R.id.btnCancel)
        val btnYes = dialog.findViewById<Button>(R.id.buttonYes)
        btnCancel.setOnClickListener{ dialog.dismiss()}
        btnYes.setOnClickListener {
            postData(fName!!, lName!!, email!!, link!!)
            dialog.dismiss()
        }
        dialog.show()

    }

    private fun prompt(layoutRes: Int){
        val dialog = Dialog(this@SubmitActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layoutRes)
        dialog.show()
    }

}