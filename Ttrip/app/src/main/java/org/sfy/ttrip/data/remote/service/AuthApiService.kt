package org.sfy.ttrip.data.remote.service

import org.sfy.ttrip.data.remote.datasorce.auth.AccessTokenRequest
import org.sfy.ttrip.data.remote.datasorce.auth.AccessTokenResponse
import org.sfy.ttrip.data.remote.datasorce.auth.AuthRequest
import org.sfy.ttrip.data.remote.datasorce.auth.AuthResponse
import org.sfy.ttrip.data.remote.datasorce.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthApiService {

    @POST("/api/members")
    suspend fun requestSignUp(@Body body: AuthRequest)

    @POST("/api/members/login")
    suspend fun requestLogin(@Body body: AuthRequest): BaseResponse<AuthResponse>

    @PATCH("/api/members/reissue")
    suspend fun requestAccessToken(@Body body: AccessTokenRequest): BaseResponse<AccessTokenResponse>
}