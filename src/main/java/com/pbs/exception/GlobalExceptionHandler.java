package com.pbs.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * 自定义全局异常处理
 * https://www.cnblogs.com/xuwujing/p/10933082.html
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public  ResultBody bizExceptionHandler(HttpServletRequest req, BizException e){
        logger.error("发生业务异常！原因是：{}",e.getErrorMsg());
        return ResultBody.error(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, NullPointerException e){
        logger.error("发生空指针异常！原因是:",e);
        return ResultBody.error(CommonEnum.BODY_NOT_MATCH);
    }
    /**
     * 处理class not found 的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =ClassNotFoundException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, ClassNotFoundException e){
        logger.error("发生Java类找不到异常！原因是:",e);
        return ResultBody.error(CommonEnum.CLASS_NOT_MATCH);
    }
    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("未知异常！原因是:",e);
        if(e instanceof SQLException){
            SQLException sqle = (SQLException)e;
            return ResultBody.error(sqle.getErrorCode()+"",sqle.getMessage());
        }
        if(e instanceof UncategorizedSQLException ||e instanceof SQLException){
            UncategorizedSQLException usqle = (UncategorizedSQLException)e;
            SQLException sqle = usqle.getSQLException();
            return ResultBody.error(sqle.getErrorCode()+"",sqle.getMessage());
        }
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR);
    }
}
