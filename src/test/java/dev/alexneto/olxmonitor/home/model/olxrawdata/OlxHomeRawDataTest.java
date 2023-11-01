package dev.alexneto.olxmonitor.home.model.olxrawdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

class OlxHomeRawDataTest {


    private final URL json =  getClass().getResource("/json/home.json");

    @Test
    void testParse() throws IOException {
        new ObjectMapper().readValue(json.openStream(), OlxHomeRawData.class);
    }

}