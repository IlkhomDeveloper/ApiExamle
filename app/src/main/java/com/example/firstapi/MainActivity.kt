package com.example.firstapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val api = RequestManager.retrofit.create(UserApi::class.java)
    private var adapter = MainPageAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycle.adapter = adapter
        recycle.layoutManager = LinearLayoutManager(this)
        method()
        adapter.setOnClick {
            movePicActivity(it.tel_raqami)
        }
    }


    private fun movePicActivity(userPhone:String){
        var intent = Intent(this, PictureActivity::class.java)
        intent.putExtra("PIC",userPhone)
        startActivity(intent)
    }

    fun method()
    {
        api.getUsers().enqueue(object : Callback<ResponseData<List<Item>>>{
            override fun onFailure(call: Call<ResponseData<List<Item>>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"onFailure",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ResponseData<List<Item>>>, response: Response<ResponseData<List<Item>>>) {
                adapter.submitList(response.body()?.data!!)
            }
        }
        )
    }
}