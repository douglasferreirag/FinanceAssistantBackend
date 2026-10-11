package com.douglasfg.FinanceAssistantBackend.exceptions;

/**
 * Exceção customizada para indicar que um recurso não foi encontrado.
 * Pode ser usada em Services e Controllers para sinalizar erros 404.
 */

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}