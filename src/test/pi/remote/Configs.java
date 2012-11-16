/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pi.remote;

import test.actors.remote.*;

/**
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class Configs {

    //server
    public static final String serverIP = "127.0.0.1";
    public static final int serverPort = 2552;
    public static final String serverSystemName = "serverSystem";
    public static final String serverActorName = "master";
    //client
    public static final String clientSystemName = "clientSystem";
    public static final int clientPort = 2553;
    public static final String clientActorName = "listener";

    public static String getCommon() {
        String result =
                "akka {\n"
                + "  actor {\n"
                + "    provider = \"akka.remote.RemoteActorRefProvider\"\n"
                + "  }\n"
                + "  remote {\n"
                + "    netty {\n"
                + "      hostname = \"" + serverIP + "\"\n"
                + "    }\n"
                + "  }\n"
                + "}\n";
        return result;
    }

    public static String getConfigForServer() {

        String result = Configs.getCommon() + "\n"
                + "  akka {\n"
                + "    remote.netty.port = " + serverPort + "\n"
                + "  }\n";
        return result;
    }
    
    /**
     * Config file for a client actor that wants to communicate with a actor 
     * that was created on a server system.
     * @return 
     */
    public static String getConfigForClientRemoteLookup() {

        String result = Configs.getCommon() + "\n"
                + "  akka {\n"
                + "    remote.netty.port = "+clientPort+ "\n"
                + "  }\n";
        return result;
    }

    
    
    
    
    
    public static String createConfigForServer(String ip, int port, String deploySystem, String deployActorName) {
        String result = "akka {\n"
                + "actor {\n"
                //                + "  provider = \"akka.actor.LocalActorRefProvider\"\n"
                + "  provider = \"akka.remote.RemoteActorRefProvider\"\n"
                + "}\n";

        result += "deployment { \n"
                + "/" + deployActorName + " {\n"
                + "remote = \"akka://" + deploySystem + "@" + ip + ":" + port + "\"\n"
                + "}\n"
                + "}\n";


        result += "remote {\n"
                + "  transport = \"akka.remote.netty.NettyRemoteTransport\"\n"
                + "  netty {\n"
                + "    hostname = \"" + ip + "\"\n"
                + "    port = " + port + "\n"
                + "  }\n"
                + "}\n";

        result += "}";

        System.out.println(" Config-Server-File-String = \n" + result);

        return result;
    }

    public static String createConfigForClient(String ip, int clientPort) {
        String result = "akka {\n"
                + "actor {\n"
                + "  provider = \"akka.remote.RemoteActorRefProvider\"\n" //master = Actor[akka://serverSystem@127.0.0.1:2552/user/master]
                // + "  provider = \"akka.actor.LocalActorRefProvider\"\n" //master = Actor[akka://clientSystem/deadLetters]
                + "}\n";

        result += "remote {\n"
                + "  transport = \"akka.remote.netty.NettyRemoteTransport\"\n"
                + "  netty {\n"
                + "    hostname = \"" + ip + "\"\n"
                + "    port = " + clientPort + "\n"
                + "  }\n"
                + "}\n";

        result += "}";

        System.out.println(" Config-Client-File-String = \n" + result);

        return result;
    }

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
