package com.douglasfg.FinanceAssistantBackend.dto.response;


import java.util.List;

import lombok.Data;


@Data
public class GeminiResponse {
     private List<Candidate> candidates;

    public String getFirstText() {
        if (candidates != null && !candidates.isEmpty()) {
            Candidate candidate = candidates.get(0);
            if (candidate.getContent() != null &&
                candidate.getContent().getParts() != null &&
                !candidate.getContent().getParts().isEmpty()) {
                return candidate.getContent().getParts().get(0).getText();
            }
        }
        return "Nenhuma sugestão encontrada.";
    }

    @Data
    public static class Candidate {
        private Content content;
    }

    @Data
    public static class Content {
        private List<Part> parts;
    }

    @Data
    public static class Part {
        private String text;
    }

}
