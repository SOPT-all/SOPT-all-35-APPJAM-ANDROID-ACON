package com.acon.data.di

import com.acon.data.error.NetworkErrorResponse
import com.acon.data.error.RemoteError
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Singleton
    @Provides
    fun providesResponseInterceptor() : Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val response = chain.proceed(chain.request())

            if (response.isSuccessful.not()) {  // response 실패 시 실행
                val errorBody = response.body?.string()
                val errorResponse = try {
                    errorBody?.let {
                        Json.decodeFromString<NetworkErrorResponse>(it)
                    }
                } catch (e: Exception) {
                    null
                }

                throw RemoteError(
                    statusCode = response.code,
                    errorCode = errorResponse?.code ?: 0,
                    message = errorResponse?.message ?: response.message,
                    httpErrorMessage = when(response.code) {
                        400 -> "Bad Request: 잘못된 요청입니다."
                        401 -> "Unauthorized: 인증되지 않은 사용자입니다."
                        403 -> "Forbidden: 접근 권한이 없습니다."
                        404 -> "Not Found: 요청한 리소스를 찾을 수 없습니다."
                        in 500 until 600 -> "Internal Server Error: 서버 내부 오류입니다."
                        else -> "Unknown Error: 알 수 없는 오류입니다."
                    },
                )
            }
            response
        }
    }
}