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
                "akka {"
                + "  actor {"
                + "    provider = \"akka.remote.RemoteActorRefProvider\""
                + "  }"
                + "  remote {"
                + "    netty {"
                + "      hostname = \"127.0.0.1\""
                + "    }"
                + "  }"
                + "}";

        return result;
    }

    public static String getCalculator() {
        String result =
                "calculator {"
                + "  include \"common\""
                + "  akka {"
                + "    remote.netty.port = 2552"
                + "  }"
                + "}";

        return result;
    }

    public static String getRemoteLookup() {
        String result =
                "calculator {"
                + "  include \"common\""
                + "  akka {"
                + "    remote.netty.port = 2553"
                + "  }"
                + "}";

        return result;
    }

    public static String getRemoteCreation() {
        String result =
                "remotecreation {"
                + "  include \"common\""
                + "  akka {"
                + "    actor {"
                + "      deployment {"
                + "        /advancedCalculator {"
                + "          remote = \"akka://CalculatorApplication@127.0.0.1:2552\""
                + "        }"
                + "      }"
                + "    }"
                + "    remote.netty.port = 2554"
                + "  }"
                + "}";

        return result;
    }
}
