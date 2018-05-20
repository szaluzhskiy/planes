package com.softpro.planes.services.exception;

/**
 * Created by Stanislav_Zaluzhskii on 5/17/2018.
 */
public class ServiceException extends RuntimeException {
        public ServiceException(String message) {
            super(message);
        }
        public ServiceException(String message, Throwable th) {
            super(message, th);
        }
}
