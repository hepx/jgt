package org.hepx.jgt.common.exception;

/**
 * JGT统一异常类
 * @author: Koala
 * @Date: 14-8-21 下午6:26
 * @Version: 1.0
 */
public class JgtException extends RuntimeException {

    public JgtException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public JgtException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public JgtException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected JgtException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
