package com.lvkang.wadandroid.ui

import android.app.AlertDialog
import android.app.Dialog
import androidx.compose.runtime.Composable
import com.lvkang.wadandroid.composable.SetScaffold
import com.lvkang.wadandroid.core.base.BaseComponentActivity

/**
 * @name TestActivity
 * @package com.lvkang.wadandroid.ui
 * @author 345 QQ:1831712732
 * @time 2022/05/25 22:24
 * @description
 */
class TestActivity : BaseComponentActivity() {


    @Composable
    override fun SetContent() {
        Dialog(this).window
        SetScaffold(title = "测试") {

        }
    }

}