package com.brian_big.gadsleaderboard.ui

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        setActionBar(toolBar)

        btnSubmit.setOnClickListener {
            val fName: String = etFirstName.text.toString()
            val lName: String = etLastName.text.toString()
            val email: String = etEmail.text.toString()
            val link: String = etLink.text.toString()

            if (fName.isNotEmpty() && lName.isNotEmpty()
                && email.isNotEmpty() && link.isNotEmpty()
            ){
//                val builder: AlertDialog.Builder? = this.let {
//                    AlertDialog.Builder(it)
//                }
//                builder?.apply {
//                    setMessage(R.string.sure)
//                    setPositiveButton(R.string.yes,
//                    DialogInterface.OnClickListener { _, _ ->
//                        postData(fName, lName, email, link)
//                    })
//                    setNegativeButtonIcon(getDrawable(R.drawable.ic_round_cancel_24))
//                }
//                val dialog = builder?.create()
//                dialog?.show()
                if (showConfirmationDialog() == 1){
                    postData(fName, lName, email, link)
                }

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
        call.enqueue(object : retrofit2.Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                prompt(getDrawable(R.drawable.ic_baseline_warning_24))
            }
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful)
                    prompt(getDrawable(R.drawable.ic_baseline_check_circle_24))
                else
                    prompt(getDrawable(R.drawable.ic_baseline_warning_24))
            }


        })
    }

    private fun showConfirmationDialog() : Int{
        var i: Int = -1
        val dialog = Dialog(this@SubmitActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_confirm)
        val btnCancel = dialog.findViewById<ImageButton>(R.id.btnCancel)
        val btnYes = dialog.findViewById<Button>(R.id.buttonYes)
        btnCancel.setOnClickListener{ dialog.dismiss()}
        btnYes.setOnClickListener {
            i = 1
            dialog.dismiss()
        }
        dialog.show()
        return i
    }

    private fun prompt(icon: Drawable?){
        val builder = this@SubmitActivity.let {
            AlertDialog.Builder(it)
        }
        builder.apply { setIcon(icon) }
    }

}