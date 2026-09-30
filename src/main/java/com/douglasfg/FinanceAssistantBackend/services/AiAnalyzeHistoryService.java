package com.douglasfg.FinanceAssistantBackend.services;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.douglasfg.FinanceAssistantBackend.config.GeminiConfig;
import com.douglasfg.FinanceAssistantBackend.dto.request.GeminiRequest;
import com.douglasfg.FinanceAssistantBackend.dto.response.GeminiResponse;
import com.douglasfg.FinanceAssistantBackend.entities.Expense;
import com.douglasfg.FinanceAssistantBackend.entities.Category;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AiAnalyzeHistoryService {

    private final GeminiConfig geminiConfig;
    private final RestTemplate restTemplate = new RestTemplate();

    public String analyzeHistoryExpenses(List<Expense> expenses) {
        String apiKey = geminiConfig.getKey();
        String apiUrl = geminiConfig.getUrl();

        if (apiKey == null || apiUrl == null || !apiUrl.startsWith("http")) {
            return "Configuração da API Gemini ausente ou inválida.";
        }

        // Monta o prompt com os dados reais
        StringBuilder sb = new StringBuilder();
        sb.append("Você é um consultor financeiro. Analise o histórico completo de despesas abaixo e sugira melhorias para reduzir gastos nos próximos meses:\n\n");

        for (Expense e : expenses) {
            sb.append("- Descrição: ").append(e.getDescription())
              .append(" | Valor: R$ ").append(e.getCost())
              .append(" | Data: ").append(e.getExpenseDate());

            // Pessoa associada
            if (e.getPerson() != null) {
                sb.append(" | Pessoa: ").append(e.getPerson().getName());
            }

            // Categorias associadas
            if (e.getCategories() != null && !e.getCategories().isEmpty()) {
                sb.append(" | Categorias: ");
                for (Category c : e.getCategories()) {
                    sb.append(c.getName()).append(", ");
                }
                // remove última vírgula
                sb.setLength(sb.length() - 2);
            }

            // Meta associada
            if (e.getGoal() != null) {
                sb.append(" | Meta: ").append(e.getGoal().getMonth())
                  .append("/").append(e.getGoal().getYear())
                  .append(" (Teto: R$ ").append(e.getGoal().getCeiling()).append(")");
            }

            sb.append("\n");
        }

        String prompt = sb.toString();

        GeminiRequest requestBody = new GeminiRequest(prompt);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<GeminiRequest> request = new HttpEntity<>(requestBody, headers);
        String fullUrl = apiUrl + "?key=" + apiKey;

        try {
            ResponseEntity<GeminiResponse> response =
                restTemplate.postForEntity(fullUrl, request, GeminiResponse.class);

            return response.getBody() != null ? response.getBody().getFirstText()
                                              : "A resposta da API Gemini veio vazia.";
        } catch (RestClientException e) {
            return "Erro ao processar resposta do Gemini: " + e.getMessage();
        }
    }
}
