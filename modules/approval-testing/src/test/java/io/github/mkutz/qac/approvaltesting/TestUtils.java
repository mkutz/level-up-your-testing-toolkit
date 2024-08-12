package io.github.mkutz.qac.approvaltesting;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class TestUtils {

    public static JsonMapper jsonMapper =
            JsonMapper.builder().addModule(new JavaTimeModule()).build();
}
