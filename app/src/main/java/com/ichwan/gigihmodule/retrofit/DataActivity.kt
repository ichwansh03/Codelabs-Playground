package com.ichwan.gigihmodule.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ichwan.gigihmodule.databinding.ActivityDataBinding
import retrofit2.Call
import retrofit2.Response

class DataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataBinding
    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = DataAdapter(this@DataActivity, arrayListOf())

        binding.rvData.adapter = adapter
        binding.rvData.setHasFixedSize(true)

        remoteSetUsers()

    }

    private fun remoteSetUsers(){
        ApiClient.apiServices.getUsers().enqueue(object : retrofit2.Callback<ArrayList<ResponseModel>>{
            override fun onResponse(
                call: Call<ArrayList<ResponseModel>>,
                response: Response<ArrayList<ResponseModel>>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    data?.let { setDataToAdapter(it) }
                    Toast.makeText(applicationContext, "Berhasil menampilkan data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<ResponseModel>>, t: Throwable) {
                Log.d("error ", t.stackTraceToString())
            }

        })
    }

    fun setDataToAdapter(data: ArrayList<ResponseModel>){
        adapter.setData(data)
    }
}