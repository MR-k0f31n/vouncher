package ru.voucher.exeption;

/**
 * @author MR.k0F31n
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
