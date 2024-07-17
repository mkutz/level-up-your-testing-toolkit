package io.github.mkutz.qac.approvaltesting

import com.fasterxml.jackson.databind.DeserializationFeature.*
import com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS
import com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.approvaltests.JsonApprovals
import org.junit.jupiter.api.Test

class ApprovalTest {

    private val jsonMapper = JsonMapper
        .builder()
//        .disable(WRITE_DATES_AS_TIMESTAMPS)
//        .enable(READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
//        .enable(ACCEPT_CASE_INSENSITIVE_ENUMS)
//        .enable(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
//        .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
//        .addModule(JavaTimeModule())
//        .addModule(Jdk8Module())
        .addModule(kotlinModule())
        .build()

    @Test
    fun approvalTest() {
        JsonApprovals.verifyJson(jsonMapper.writeValueAsString(Order()))
    }
}
