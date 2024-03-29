package com.example.cmd.kotlin.Main

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cmd.kotlin.Api.ApiProvider
import com.example.cmd.kotlin.Request.SignInRequest2
import com.example.cmd.kotlin.Response.SignInResponse2
import com.example.cmd.databinding.ActivitySigninBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity2 : AppCompatActivity() {

    private lateinit var binding:ActivitySigninBinding
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    companion object{
        var token: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences("User", MODE_PRIVATE)
        editor = preferences.edit()

        if(preferences.getBoolean("check", false) == true){
            binding.etId.setText(preferences.getString("id", ""))
            binding.etPw.setText(preferences.getString("pw", ""))
            binding.cbautlLogin.isChecked = true
            autosignIn()
        }

        binding.btLogin.setOnClickListener {
            signInCheck()
        }

        binding.tvRegister.setOnClickListener{
            val intent = Intent(applicationContext, SignUpActivity2::class.java)
            startActivity(intent)
        }

    }

    private fun autosignIn() {
        val id = binding.etId.text.toString()
        val pw = binding.etPw.text.toString()

        val signinrequest = SignInRequest2(id, pw)

        ApiProvider.retrofit.signIn(signinrequest).enqueue(object : Callback<SignInResponse2>{
            override fun onResponse(
                call: Call<SignInResponse2>,
                response: Response<SignInResponse2>
            ) {
                if(response.isSuccessful){
                    token = response.body()!!.accessToken

                    val intent = Intent(applicationContext, LobbyActivity2::class.java)
                    startActivity(intent)
                    finish()

                    Toast.makeText(applicationContext, "로그인 되었습니다!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SignInResponse2>, t: Throwable) {

            }

        })
    }

    private fun signInCheck() {
        val id = binding.etId.text.length
        val pw = binding.etPw.text.length

        if(id == 0 && pw != 0){
            Toast.makeText(applicationContext, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
        }else if(id != 0 && pw == 0){
            Toast.makeText(applicationContext, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
        }else if(id == 0 && pw == 0){
            Toast.makeText(applicationContext, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
        }else{
            signIn()
        }

    }

    private fun signIn() {
        val id = binding.etId.text.toString()
        val pw = binding.etPw.text.toString()

        val signinrequest = SignInRequest2(id, pw)

        ApiProvider.retrofit.signIn(signinrequest).enqueue(object : Callback<SignInResponse2>{
            override fun onResponse(
                call: Call<SignInResponse2>,
                response: Response<SignInResponse2>
            ) {
                if(response.isSuccessful){
                    token = response.body()!!.accessToken

                    if(binding.cbautlLogin.isChecked){
                        editor.putBoolean("check", true).commit()
                        editor.putString("id", id).commit()
                        editor.putString("pw", pw).commit()
                    }

                    val intent = Intent(applicationContext, LobbyActivity2::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<SignInResponse2>, t: Throwable) {

            }

        })

    }
}