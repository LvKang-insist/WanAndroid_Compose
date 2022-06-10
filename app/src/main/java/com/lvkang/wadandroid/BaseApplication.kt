package com.lvkang.wadandroid

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lvhttp.net.LvHttp
import com.lvhttp.net.error.ErrorKey
import com.lvhttp.net.error.ErrorValue
import com.lvkang.wadandroid.core.composable.state.PageStateConfig
import com.lvkang.wadandroid.core.net.interceptor.LogInterceptor

/**
 * @name BaseApplication
 * @package com.lvkang.wadandroid.core
 * @author 345 QQ:1831712732
 * @time 2022/06/09 10:41
 * @description
 */
class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        composePageState()
        initLvHttp()
    }

    private fun initLvHttp() {
        LvHttp.Builder()
            .setApplication(this)
            .setBaseUrl("https://www.wanandroid.com/")
            //是否开启缓存
            .isCache(false)
            //是否打印 log
            .isLog(false)
            .setCode(0)
            .addInterceptor(LogInterceptor())
            //对 Code 异常的处理，可自定义,参考 ResponseData 类
            .setErrorDispose(ErrorKey.ErrorCode, ErrorValue {
                Toast.makeText(this, "Code 错误", Toast.LENGTH_SHORT).show()
            })
            //全局异常处理，参考 Launch.kt ，可自定义异常处理，参考 ErrorKey 即可
            .setErrorDispose(ErrorKey.AllEexeption, ErrorValue {
                it.printStackTrace()
                Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show()
            })
            .build()
    }

    private fun composePageState() {
        PageStateConfig.run {
            loadingComponent {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.Center)
                )
            }
            emptyComponent {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "暂无数据")
                }
            }
            errorComponent {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = it.value.toString())
                }
            }
        }
    }
}