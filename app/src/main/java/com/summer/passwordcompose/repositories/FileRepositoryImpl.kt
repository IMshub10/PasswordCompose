package com.summer.passwordcompose.repositories

import android.os.Environment
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.summer.passwordcompose.beans.FileBean
import com.summer.passwordcompose.utils.AESEncryption
import com.summer.passwordcompose.utils.AppUtils.toFileBean
import com.summer.passwordcompose.utils.AppUtils.toJSON
import com.summer.passwordcompose.utils.EncryptionUtils
import com.summer.passwordcompose.utils.EncryptionUtils.toCharArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.util.Base64
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor() : FileRepository {

    override suspend fun exportFile(
        fileBean: FileBean,
        appName: String,
        fileName: String,
        key: String
    ): String? {
        var savedFileAbsolutePath: String? = null
        withContext(Dispatchers.IO) {
            try {
                val fileBeanJson = fileBean.toJSON()
                Log.d("TestingJson", fileBeanJson)
                val ivGen = EncryptionUtils.generateRandomBytes(16) //IV

                Log.d("TestingJson", "KeyGen $key")
                Log.d("TestingJson", "IVGen ${Base64.getEncoder().encodeToString(ivGen)}")
                val aesEncryption = AESEncryption(
                    key.toCharArray(), ivGen.toCharArray()
                )
                val encryptedString = aesEncryption.encrypt(fileBeanJson)
                Log.d("TestingJson", "Encrypted $encryptedString")

                val path =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).path + File.separator + appName
                File(path).mkdirs()
                val file = File(path, "${fileName}.txt")
                Log.d("path", path)
                if (file.exists()) file.delete()

                val fileStream = FileOutputStream(file)
                fileStream.bufferedWriter().use {
                    it.write(ivGen.toCharArray())
                    it.write("\n")
                    it.write(encryptedString)
                }
                savedFileAbsolutePath = file.absolutePath
                fileStream.flush()
                fileStream.close()

            } catch (e: Exception) {
                FirebaseCrashlytics.getInstance().recordException(e)
                e.printStackTrace()
            }
        }
        return savedFileAbsolutePath
    }

    override suspend fun importFile(inputStream: InputStream, key: String): FileBean? {
        var fileBean: FileBean? = null
        try {
            withContext(Dispatchers.IO) {
                val fileStream = BufferedReader(InputStreamReader(inputStream))
                val iiv = fileStream.readLine()
                Log.d("iv", String(iiv.toByteArray()))
                val decryptedString = AESEncryption(
                    key.toCharArray(), iiv.toCharArray()
                ).decryptToString(
                    StringBuffer().apply {
                        fileStream.forEachLine {
                            append(it)
                        }
                    }.toString()
                )
                Log.d("TestingJson", "Decrypted $decryptedString")
                fileStream.close()
                fileBean = decryptedString.toFileBean()
            }
        } catch (e: Exception) {
            FirebaseCrashlytics.getInstance().recordException(e)
        }
        return fileBean
    }
}