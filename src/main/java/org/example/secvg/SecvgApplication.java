package org.example.secvg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Objects;

@SpringBootApplication
public class SecvgApplication {

    public static void main(String[] args) {
        //SpringApplication.run(SecvgApplication.class, args);

        if (args.length == 0) {
            SpringApplication app = new SpringApplication(SecvgApplication.class);
            /*
            try {
                MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
                ObjectName objectName = new ObjectName("org.springframework.boot:type=Admin,name=SpringApplication");
                if (mBeanServer.isRegistered(objectName)) {
                    mBeanServer.unregisterMBean(objectName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            */
            //app.setAdditionalProfiles("mainapp");
            //logActiveProfiles(app);
            app.run(args);
        } else if (Objects.equals(args[0], "createhashes")) {
            SpringApplication app = new SpringApplication(CreateHashes.class);
            app.setWebApplicationType(WebApplicationType.NONE);
            //logActiveProfiles(app);
            app.run(args);

        }
    }

}
