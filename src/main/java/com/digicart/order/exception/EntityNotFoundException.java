package com.digicart.order.exception;

/**
 * Domain exception: Entity Not Found Exception.
 */
public class EntityNotFoundException extends RuntimeException {
    /**
     * Creates a new {@code EntityNotFoundException}.
     *
     * @param message message
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}
