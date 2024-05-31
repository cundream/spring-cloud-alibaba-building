package com.lison.springcloudservice.config.seata;



import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @className: com.lison.springcloudservice.config.seata-> SeataInterceptor
 * @description:
 * @author: 李村 200900681
 * @createDate: 2024-05-30 16:12
 */
@Slf4j
public class SeataInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String xid = RootContext.getXID();
        // 获取全局事务编号
        String rpcXid = request.getHeader("TX_XID");
        log.info("xid in RootContext[{}] xid in HttpContext[{}]", xid, rpcXid);
        if (xid == null && rpcXid != null) {
            //设置全局事务编号, 应用开启一个全局事务后，RootContext会自动绑定当前事务的XID，事务结束后也会自动解绑XID。所以在应用运行的过程中可以直接调用 RootContext.getXID()方法获取全局事务的唯一标识。
            //将XID和当前进程绑定
            RootContext.bind(rpcXid);
            log.info("bind[{}] to RootContext", rpcXid);
        }
        return true;
    }
}

