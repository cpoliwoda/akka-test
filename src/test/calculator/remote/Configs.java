/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.calculator.remote;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class Configs {

    public static String getCommon() {
        String result =
                "akka {\n"
                + "  actor {\n"
                + "    provider = \"akka.remote.RemoteActorRefProvider\"\n"
                + "  }\n"
                + "  remote {\n"
                + "    netty {\n"
                + "      hostname = \"127.0.0.1\"\n"
                + "    }\n"
                + "  }\n"
                + "}\n";

        return result;
    }

    public static String getCalculator() {
        String result =
//                "calculator {\n"
//                + "  include \"common\""
                 Configs.getCommon()+"\n"
                + "  akka {\n"
                + "    remote.netty.port = 2552 \n"
                + "  }\n";
//                + "}\n";

        return result;
    }

    public static String getRemoteLookup() {
        String result =
//                "calculator {\n"
//                + "  include \"common\" \n"
                 Configs.getCommon()+"\n"
                + "  akka {\n"
                + "    remote.netty.port = 2553 \n"
                + "  }\n";
//                + "}\n";

        return result;
    }

    public static String getRemoteCreation() {
        String result =
//                "remotecreation {\n"
//                + "  include \"common\" \n"
                 Configs.getCommon()+"\n"
                + "  akka {\n"
                + "    actor {\n"
                + "      deployment {\n"
                + "        /advancedCalculator {\n"
                + "          remote = \"akka://CalculatorApplication@127.0.0.1:2552\"\n"
                + "        }\n"
                + "      }\n"
                + "    }\n"
                + "    remote.netty.port = 2554 \n"
                + "  }\n";
//                + "}\n";

        return result;
    }
}
