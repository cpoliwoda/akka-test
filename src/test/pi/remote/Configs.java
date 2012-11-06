/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pi.remote;

import test.actors.remote.*;

/**
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
public class Configs {

    public static String createConfig(String ip, int port, String deploySystem, String deployActorName) {
        String result = "akka {\n"
                + "actor {\n"
                + "  provider = \"akka.remote.RemoteActorRefProvider\"\n"
                + "}\n";
        if (deploySystem != null && deployActorName != null) {
            result += "deployment { \n"
                    + "/" + deployActorName + " {\n"
                    + "remote = \"akka://" + deploySystem + "@127.0.0.1:2552\"\n"
                    + "}\n"
                    + "}\n";
        }
        result += "remote {\n"
                + "  transport = \"akka.remote.netty.NettyRemoteTransport\"\n"
                + "  netty {\n"
                + "    hostname = \"" + ip + "\"\n"
                + "    port = " + port + "\n"
                + "  }\n"
                + "}\n"
                + "}";
        
        return result;
    }
    
    public static String createConfig(String ip, int port) {
       return createConfig(ip, port, null, null);
    }
}
