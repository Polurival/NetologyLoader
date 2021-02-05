package com.polurival.netologyloader.utils

import com.google.common.truth.Truth
import com.google.gson.Gson
import com.polurival.netologyloader.data.model.TaskResponse
import com.polurival.netologyloader.presentation.model.SubjectItem
import org.junit.Test
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

/**
 * @author Польщиков Юрий on 05/02/2021
 */
class ResponseConverterTest {

    @Test
    fun testConvert() {
        // todo move file reading to test utils
        javaClass.classLoader?.getResourceAsStream("netology.json").use { inputStream ->
            InputStreamReader(inputStream, StandardCharsets.UTF_8).use { streamReader ->

                val taskResponse = Gson().fromJson(streamReader, TaskResponse::class.java)
                val actual = ResponseConverter().convert(taskResponse)

                Truth.assertThat(actual).isEqualTo(
                    listOf(
                        SubjectItem("Маркетинг", 37),
                        SubjectItem("Бизнес и управление", 20)
                    )
                )
            }
        }
    }
}