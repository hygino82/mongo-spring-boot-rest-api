package br.dev.hygino.exceptions;

public class TodoCollectionException extends RuntimeException {

    public TodoCollectionException(String message) {
        super(message);
    }

    public static String notFoundException(String id) {
        return String.format("Todo with id: %s not found", id);
    }

    public static String todoAlreadyExists() {
        return "Todo with given name already exists";
    }
}
