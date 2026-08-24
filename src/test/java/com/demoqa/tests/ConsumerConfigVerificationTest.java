package com.demoqa.tests;

import com.automation.config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ConsumerConfigVerificationTest {

    @Test
    public void testConsumerProjectLoadsItsOwnConfigProperties() {
        // Verify consumer project properties
        String provider = ConfigReader.get("ai.healing.provider");
        Assert.assertNotNull(provider, "ai.healing.provider should be loaded from consumer config.properties");
        Assert.assertEquals(provider, "hybrid");

        String geminiModel = ConfigReader.get("ai.gemini.model");
        Assert.assertEquals(geminiModel, "gemini-3.6-flash");

        String openaiModel = ConfigReader.get("ai.openai.model");
        Assert.assertEquals(openaiModel, "gpt-4o-mini");

        String ollamaUrl = ConfigReader.get("ai.ollama.base.url");
        Assert.assertEquals(ollamaUrl, "http://localhost:11434");
    }
}
