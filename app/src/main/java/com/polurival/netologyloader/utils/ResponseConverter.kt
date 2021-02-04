package com.polurival.netologyloader.utils

import com.polurival.netologyloader.data.model.TaskResponse
import com.polurival.netologyloader.presentation.model.SubjectItem

/**
 * @author Польщиков Юрий on 04/02/2021
 */
class ResponseConverter {

    fun convert(taskResponse: TaskResponse): List<SubjectItem> {
        val result = mutableListOf<SubjectItem>()

        for (data in taskResponse.data) {
            val subjectTitle = data.direction.title
            var coursesCount = 0
            for (groups in data.groups) {
                coursesCount += groups.items.size
            }
            result.add(SubjectItem(subjectTitle, coursesCount))
        }

        return result
    }
}