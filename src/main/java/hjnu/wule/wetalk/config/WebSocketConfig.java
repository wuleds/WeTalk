package hjnu.wule.wetalk.config;

//汉江师范学院 数计学院 吴乐创建于2022/12/26 14:57:36

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig
{
    /**注入ServerEndpointExporter的bean对象，自动注册使用@ServerEndpoint注解的bean*/
    @Bean
    public ServerEndpointExporter serverEndpointExporter()
    {
        return new ServerEndpointExporter();
    }
}