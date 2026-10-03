package com.douglasfg.FinanceAssistantBackend.dto.request;

import java.util.List;

public class GeminiRequest {
    private List<Content> contents;

    public GeminiRequest(String prompt) {
        this.contents = List.of(new Content(prompt));
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    // Conteúdo principal
    public static class Content {
        private List<Part> parts;

        public Content(String text) {
            this.parts = List.of(new Part(text));
        }

        public List<Part> getParts() {
            return parts;
        }

        public void setParts(List<Part> parts) {
            this.parts = parts;
        }
    }

    // Texto do prompt
    public static class Part {
        private String text;

        public Part(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
    
}
