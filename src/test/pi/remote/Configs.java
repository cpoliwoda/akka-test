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

    //server
    public static final String serverIP = "127.0.0.1";
    public static final int serverPort = 2552;
    public static final String serverSystemName = "PiSys1";
    public static final String serverActorName = "Master";
    //client
    public static final String clientSystemName = "PiSys2";
    public static final int clientPort = 2553;
    public static final String clientActorName = "Master";

    public static String createConfig(String ip, int port, String deploySystem, String deployActorName) {
        String result = "akka {\n"
                + "actor {\n"
                + "  provider = \"akka.remote.RemoteActorRefProvider\"\n"
                + "}\n";
        if (deploySystem != null && deployActorName != null) {
            result += "deployment { \n"
                    + "/" + deployActorName + " {\n"
                    + "remote = \"akka://" + deploySystem + "@" + ip + ":" + port + "\"\n"
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
