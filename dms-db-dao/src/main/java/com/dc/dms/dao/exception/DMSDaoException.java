package com.dc.dms.dao.exception;

/**
 * Created by SJoshi on 6/24/2015.
 */
public class DMSDaoException extends Exception{

    public DMSDaoException(final String message, final Throwable t) {
        super(message, t);
    }

    public DMSDaoException(final String message) {
        super(message);
    }

}
