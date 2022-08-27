package demo.service;

import org.springframework.context.annotation.Bean;
import org.h2.tools.Server;
public class DatabaseConfiguration  {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseaServer() throws Exception {
        return Server.createTcpServer(
                "-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
    }

}
