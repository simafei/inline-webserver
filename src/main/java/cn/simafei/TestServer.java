package cn.simafei;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by fengpj on 2016/6/12.
 * @author fengpj(simafei)
 * 直接使用Jetty(没有集成SpringMVC)的内嵌WebServer的例子
 */
public class TestServer {
    public static void main(String args[]) {
        try {
            // 进行服务器配置
            Server server = new Server(8888);
            ContextHandler context = new ContextHandler();
            // 设置搜索的URL地址
            context.setContextPath("/search");
            context.setResourceBase(".");
            context.setClassLoader(Thread.currentThread().getContextClassLoader());
            server.setHandler(context);
            context.setHandler(new HelloHandler());
            // 启动服务器
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class HelloHandler extends AbstractHandler {

        public void handle(String target, Request baseRequest,
                           HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException {

            String query = request.getParameter("query");
            // String name = request.getParameter("name");

            String result = "welcome to my server.";
            if (null != query && query.equals("hello")) {
                result = query + ", " + result;
            }

            // 将服务器处理后的结果返回给调用URL的客户端
            print(baseRequest, response, result);
        }

        /**
         * <pre>
         * @param baseRequest
         * @param response
         * @param result 需要返回给客户的结果
         * @throws IOException
         * 将结果 result 返回给客户
         * </pre>
         */
        private void print(Request baseRequest, HttpServletResponse response,
                           String result) throws IOException {
            response.setContentType("text/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
            response.getWriter().println(result);
        }
    }
}
