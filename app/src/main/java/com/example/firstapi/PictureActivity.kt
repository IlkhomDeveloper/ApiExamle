package com.example.firstapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_picture.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureActivity : AppCompatActivity() {
    private var adapter = PicturePageAdapter()
    private var userPhone = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
        pictureRecycle.adapter = adapter
        pictureRecycle.layoutManager = GridLayoutManager(this,3)
        val api = ApiClient.retrofit.create(UserApi::class.java)
        userPhone = intent.extras?.getString("PIC").toString()

        api.getPics(userPhone).enqueue(object : Callback<ResponseData<List<Pics>>>{
            override fun onResponse(
                call: Call<ResponseData<List<Pics>>>,
                response: Response<ResponseData<List<Pics>>>
            ) {
                val ls = response.body()
                if (response.isSuccessful && ls != null){
                    adapter.submitList(ls.data!!)
                }
            }

            override fun onFailure(call: Call<ResponseData<List<Pics>>>, t: Throwable) {
                Toast.makeText(this@PictureActivity,"Failure",Toast.LENGTH_LONG).show()
            }
        })
    }
}