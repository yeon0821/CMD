package com.example.cmd.kotlin.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cmd.kotlin.Api.ApiProvider
import com.example.cmd.kotlin.Main.SignInActivity2
import com.example.cmd.kotlin.Recyclerview.StudentAdapter2
import com.example.cmd.kotlin.Response.StudentInfoResponse2
import com.example.cmd.databinding.FragmentStudentinfoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentInfoFragment2 : Fragment() {

    private lateinit var binding: FragmentStudentinfoBinding
    private lateinit var adapter2: StudentAdapter2
    private lateinit var list: ArrayList<StudentInfoResponse2>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStudentinfoBinding.inflate(inflater, container, false)

        list = ArrayList()

        binding.studentrecyclerview.layoutManager = GridLayoutManager(activity, 3)
        binding.studentrecyclerview.adapter = StudentAdapter2(list)

        list.add(StudentInfoResponse2("selifhs", "seilhf"))

        ApiProvider.retrofit.studentinfo(SignInActivity2.token).enqueue(object : Callback<List<StudentInfoResponse2>> {
                override fun onResponse(
                    call: Call<List<StudentInfoResponse2>>,
                    response: Response<List<StudentInfoResponse2>>
                ) {
                    if (response.isSuccessful) {
                        list.addAll(response.body()!!)
                        adapter2.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<List<StudentInfoResponse2>>, t: Throwable) {

                }

            })

        return binding.root
    }
}