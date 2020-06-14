package com.zzpj.backend.exceptions;

public class WarehouseException extends AppBaseException {

    public static final String WAREHOUSE_ALREADY_EXIST = "warehouse already exist";

    public WarehouseException(String message) {
        super(message);
    }

    public WarehouseException(String message, Throwable e) {
        super(message, e);
    }

    public static WarehouseException createWarehouseAlreadyExistException() {
        return new WarehouseException(WAREHOUSE_ALREADY_EXIST);
    }
}
