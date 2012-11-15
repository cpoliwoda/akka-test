/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.calculator.remote;

/*    https://github.com/akka/akka/tree/v2.0.3/akka-samples/akka-sample-remote
 
 * The Sample Explained
In order to showcase the remote capabilities of Akka 2.0 we thought a remote 
calculator could do the trick.

There are two implementations of the sample; one in Scala and one in Java. 
The explanation below is for Scala, but everything is similar in Java except 
that the class names begin with a J, e.g. JCalcApp instead of CalcApp, and that 
the Java classes reside in another package structure.

There are three actor systems used in the sample:

    CalculatorApplication : the actor system performing the number crunching
    LookupApplication : illustrates how to look up an actor on a remote node and and how communicate with that actor
    CreationApplication : illustrates how to create an actor on a remote node and how to communicate with that actor

The CalculatorApplication contains an actor, SimpleCalculatorActor, which can 
handle simple math operations such as addition and subtraction. This actor is 
looked up and used from the LookupApplication.

The CreationApplication wants to use more "advanced" mathematical operations, 
such as multiplication and division, but as the CalculatorApplication does not 
have any actor that can perform those type of calculations the CreationApplication 
has to remote deploy an actor that can (which in our case is AdvancedCalculatorActor). 
So this actor is deployed, over the network, onto the CalculatorApplication actor 
system and thereafter the CreationApplication will send messages to it.

It is important to point out that as the actor system run on different ports it 
is possible to run all three in parallel. See the next section for more 
information of how to run the sample application.
 
* Notice
The sample application is just that, i.e. a sample. Parts of it are not the way 
you would do a "real" application. Some improvements are to remove all hard coded 
addresses from the code as they reduce the flexibility of how and where the 
application can be run. We leave this to the astute reader to refine the sample 
into a real-world app. 
 */


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
