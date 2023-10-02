package com.summer.passwordcompose.repositories

import com.summer.passwordcompose.beans.FileBean
import java.io.InputStream

interface FileRepository {

    //region File methods
    suspend fun exportFile(
        fileBean: FileBean,
        appName: String,
        fileName: String,
        key: String
    ): String?

    suspend fun importFile(inputStream: InputStream, key: String): FileBean?
    //endregion
}