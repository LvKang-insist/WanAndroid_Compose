package com.lvkang.wadandroid.core.net.resposne

import com.lvhttp.net.response.BaseResponse

/**
 * @name ResponseData
 * @package com.lvkang.wadandroid.core.net.resposne
 * @author 345 QQ:1831712732
 * @time 2022/06/09 16:08
 * @description
 */
class ResponseData<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
) : BaseResponse<T>() {

    override fun notifyData(): BaseResponse<T> {
        this._data = data
        this._code = errorCode
        this._message = errorMsg
        return this
    }
}