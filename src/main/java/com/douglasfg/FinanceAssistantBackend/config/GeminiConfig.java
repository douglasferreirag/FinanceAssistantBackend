package com.douglasfg.FinanceAssistantBackend.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class GeminiConfig {
    private String key;
    private String url;

    public GeminiConfig() {
        Dotenv dotenv = Dotenv.load();
        this.key = dotenv.get("GEMINI_API_KEY");
        this.url = dotenv.get("GEMINI_API_URL");
    }
}
