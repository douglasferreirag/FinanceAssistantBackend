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
import com.douglasfg.FinanceAssistantBackend.dtos.ai.ExpenseAnalyzeData;
import com.douglasfg.FinanceAssistantBackend.dtos.ai.GoalAnalyzeData;
import com.douglasfg.FinanceAssistantBackend.dtos.requests.GeminiRequest;
import com.douglasfg.FinanceAssistantBackend.dtos.responses.GeminiResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AiAnalyzeHistoryService {

    private final GeminiConfig geminiConfig;

    private final RestTemplate restTemplate =
            new RestTemplate();

    public String analyzeHistoryExpenses(
            List<ExpenseAnalyzeData> expenses,
            List<GoalAnalyzeData> goals
    ) {

        if (invalidConfiguration()) {
            return "Configuração da API Gemini ausente ou inválida.";
        }

        String prompt =
                buildAnalysisPrompt(
                        expenses,
                        goals
                );

        return sendToGemini(prompt);
    }

    private boolean invalidConfiguration() {

        return geminiConfig.getKey() == null
                || geminiConfig.getUrl() == null
                || !geminiConfig.getUrl().startsWith("http");
    }

    private String buildAnalysisPrompt(
            List<ExpenseAnalyzeData> expenses,
            List<GoalAnalyzeData> goals
    ) {

        StringBuilder sb = new StringBuilder();

        sb.append("""
                Você é um consultor financeiro especializado.

                Analise os gastos e compare-os com as metas financeiras.

                Gere um relatório contendo:

                1. Resumo geral dos gastos.
                2. Comparação entre gastos e metas.
                3. Análise individual por pessoa.
                4. Análise por categoria.
                5. Identificação de excessos.
                6. Recomendações para economia.
                7. Conclusão financeira.

                ====================================
                METAS FINANCEIRAS
                ====================================

                """);

        for (GoalAnalyzeData goal : goals) {

            sb.append("Período: ")
              .append(goal.getMonth())
              .append("/")
              .append(goal.getYear())
              .append(" | Teto definido: R$ ")
              .append(goal.getCeiling())
              .append("\n");
        }

        sb.append("""

                ====================================
                HISTÓRICO DE DESPESAS
                ====================================

                """);

        for (ExpenseAnalyzeData expense : expenses) {

            sb.append("Descrição: ")
              .append(expense.getDescription())

              .append(" | Valor: R$ ")
              .append(expense.getCost())

              .append(" | Data: ")
              .append(expense.getExpenseDate())

              .append(" | Pessoa: ")
              .append(expense.getPersonName())

              .append(" | Categoria: ")
              .append(expense.getCategoryName())

              .append("\n");
        }

        sb.append("""

                ====================================
                INSTRUÇÕES IMPORTANTES
                ====================================

                Compare os gastos com as metas do mesmo período.

                Considere o mês e o ano das metas ao analisar as datas das despesas.

                Identifique:

                - Quem mais gastou.
                - Quem menos gastou.
                - Categorias predominantes por pessoa.
                - Categorias com maior impacto financeiro.
                - Possíveis desperdícios.
                - Situações em que a meta foi ultrapassada.
                - O percentual aproximado da meta consumido.

                Finalize o relatório com sugestões práticas e acionáveis para melhorar o controle financeiro.

                """);

        return sb.toString();
    }

    private String sendToGemini(
            String prompt
    ) {

        try {

            GeminiRequest requestBody =
                    new GeminiRequest(prompt);

            HttpHeaders headers =
                    new HttpHeaders();

            headers.setContentType(
                    MediaType.APPLICATION_JSON
            );

            HttpEntity<GeminiRequest> request =
                    new HttpEntity<>(
                            requestBody,
                            headers
                    );

            String fullUrl =
                    geminiConfig.getUrl()
                    + "?key="
                    + geminiConfig.getKey();

            ResponseEntity<GeminiResponse> response =
                    restTemplate.postForEntity(
                            fullUrl,
                            request,
                            GeminiResponse.class
                    );

            if (response.getBody() == null) {
                return "A resposta do Gemini veio vazia.";
            }

            return response.getBody()
                           .getFirstText();

        } catch (RestClientException ex) {

            return "Erro ao processar resposta da IA: "
                    + ex.getMessage();
        }
    }
}