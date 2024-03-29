package com.example.cmd.api;

import com.example.cmd.request.InfoUpdateRequest;
import com.example.cmd.request.PostRequest;
import com.example.cmd.request.SignInRequest;
import com.example.cmd.request.SignUpRequest;
import com.example.cmd.response.MyInfoResponse;
import com.example.cmd.response.NoticeResponse;
import com.example.cmd.response.SignInResponse;
import com.example.cmd.response.StudentInfoResponse;
import com.example.cmd.response.SubjectResponse;
import com.example.cmd.response.TimetableResponse;
import com.example.cmd.response.UserInfoResponse;
import com.example.cmd.response.UserPostResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServerApi {

    // 회원가입
    @POST("signup/{secretKey}")
    Call<Void> signUp(
        @Path("secretKey") String secretKey,
        @Body SignUpRequest signupRequest
    );

    // 로그인
    @POST("signin")
    Call<SignInResponse> signIn(
            @Body SignInRequest signInRequest
    );

    // 시간표 조회
    @GET("user/timetable/{day}")
    Call<TimetableResponse> timetable(
        @Header("Authorization") String accessToken,
        @Path("day") String day
    );

    // 유저 정보 리스트 조회
    @GET("user/Info")
    Call<List<StudentInfoResponse>> studentInfo(
        @Header("Authorization") String accessToken
    );

    // 공지사항 리스트 조회
    @GET("user/noticeBoard")
    Call<List<NoticeResponse>> notice(
        @Header("Authorization") String accessToken
    );

    // 유저 상세 정보 조회
    @GET("user/info/{number}")
    Call<UserInfoResponse> userInfo(
        @Header("Authorization") String accessToken,
        @Path("number") String number
    );

    // 내 정보 조회
    @GET("user/myInfo")
    Call<MyInfoResponse> myInfo(
        @Header("Authorization") String accessToken
    );

    // 내 정보 업데이트
    @PUT("user/myInfoUpdate")
    Call<Void> infoUpdate(
        @Header("Authorization") String accessToken,
        @Body InfoUpdateRequest infoUpdateRequest
    );

    // 수업 상세 정보 조회
    @GET("user/subject/{subjectNumber}")
    Call<SubjectResponse> subject(
            @Header("Authorization") String accessToken,
            @Path("subjectNumber") int subjectNumber
    );

    // 유저 게시글 조회
    @GET("user/post")
    Call<List<UserPostResponse>> userPost(
            @Header("Authorization") String accessToken
    );

    // 유저 글 게시
    @POST("user/post")
    Call<Void> post(
            @Header("Authorization") String accessToken,
            @Body PostRequest postRequest
    );

}
