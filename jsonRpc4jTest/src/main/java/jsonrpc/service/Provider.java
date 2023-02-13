package jsonrpc.service;

import com.googlecode.jsonrpc4j.JsonRpcServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 提供者
 *
 * @ClassName Provider
 * @Description
 * @Author zsks
 * @Date 2021/12/6 21:07
 * @Version 1.0
 **/
public class Provider extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private JsonRpcServer rpcServer = null;

    public Provider() {
        super();
        rpcServer = new JsonRpcServer(new DemoServiceImply(), DemoService.class);
    }

    @Override
    protected void service(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        rpcServer.handle(request, response);
    }
}
