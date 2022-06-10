package com.lvkang.wadandroid.core.net.interceptor

import android.util.Log
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

/**
 * @name LogInterceptor
 * @package com.tidycar.carzki.remote.interceptor
 * @author 345 QQ:1831712732
 * @time 2021/01/26 11:42
 * @description 日志拦截器
 */
class LogInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val requestBody = request.body
        val requestBuffer = StringBuffer()


        val contentType = requestBody?.contentType()
        val charset: Charset =
            contentType?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8
        //请求日志
        requestBuffer.apply {
            append("{method:${request.method}} \n")
            if (requestBody != null && !bodyHasUnknownEncoding(request.headers) && !requestBody.isDuplex() && !requestBody.isOneShot()) {
                val uri = request.url.toString()
//                if (uri.contains("relevant_img") || uri.contains("upload_file") || uri.contains("procedures_img")) {
//                    append("{arguments:{文件/图片上传}\n")
//                } else {
                val buffer = Buffer()
                requestBody.writeTo(buffer)

                append("{arguments:{${buffer.readString(charset)}}}\n")
//                }
            }
        }

        val response = chain.proceed(request)
        try {
            val responseBuffer = StringBuffer()
            val responseBody = response.body!!

            responseBuffer.apply {
                append("\n------------- 网络请求 Start------------ \n")
                append("{Code:${response.code}}\n")
                append("{URL：${response.request.url}}\n")
                append("${requestBuffer}\n")

                val uri = response.request.url.toString()
                //如果是文件下载
                if (uri.contains("https://mapi.carzki.com/uploads/public") ||
                    uri.contains("https://mapi-test.carzki.com/uploads/public")
                ) {
                    append("{文件下载}\n")
                } else {
                    val source = responseBody.source()
                    source.request(Long.MAX_VALUE) // Buffer the entire body.
                    val bufferResponse = source.buffer
                    append("${formatDataFromJson(bufferResponse.clone().readString(charset))}\n")
                }
                append("------------- 网络请求 End------------\n")
            }
            Log.e("---345--->", responseBuffer.toString())
            Log.e("---345--->", request.headers.joinToString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }

    private fun bodyHasUnknownEncoding(headers: Headers): Boolean {
        val contentEncoding = headers["Content-Encoding"] ?: return false
        return !contentEncoding.equals("identity", ignoreCase = true) &&
                !contentEncoding.equals("gzip", ignoreCase = true)
    }

    private fun formatDataFromJson(response: String): String {
        try {
            if (response.startsWith("{")) {
                val jsonObject = JSONObject(response)
                return jsonObject.toString(4)
            } else if (response.startsWith("[")) {
                val jsonArray = JSONArray(response)
                return jsonArray.toString(4)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }

}