/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.actors.remote;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class Main {

    final static String actorsystemname = "MySystem";
    final static String hostname = "127.0.0.1";
    final static String port = "2552";//"1099";
    final static String deployActor = "/serviceA/retrieval";
    final static String actorpath = "user" + deployActor;

    public static void main(String[] args) {

        Config config = ConfigFactory.parseString(
                "    akka {\n"
                + "      actor {\n"
                + "        provider = \"akka.remote.RemoteActorRefProvider\"\n"
                + "      }\n"
                + "      deployment {\n"
                //                + "            default {\n"
                //                + "                remote = \""+actorpath+"\"\n" 
                //                + "            }\n"
                + "             " + deployActor + " {\n"
                + "                 remote = \"akka://" + actorsystemname + "@" + hostname + ":" + port +"/"+actorpath+ "\"\n"
                + "             }"
                + "      }\n"
                + "      remote {\n"
                + "        transport = \"akka.remote.netty.NettyRemoteTransport\"\n"
                + "        netty {\n"
                + "          hostname = \"" + hostname + "\"\n"
                + "          port = " + port + "\n"
                + "        }\n"
                + "      }\n"
                + "    }");



        //loads the default config file (reference.conf) at the classpath
        //that was created by the user
//        Config config = ConfigFactory.load("reference");

        ActorSystem system = ActorSystem.create(actorsystemname, config);

        // REMOTE
        //    akka://<actorsystemname>@<hostname>:<port>/<actor path>
//        ActorRef greeter = system.actorFor("akka://" + actorsystemname + "@" + hostname + ":" + port);//+"/"+ actorpath);

        // LOCAL
        ActorRef greeter = system.actorFor(deployActor);

//        ActorRef greeter = system.actorOf(new Props(GreetingActor.class), "greeter");

        greeter.tell(new Greeting("Charlie Parker"));

        system.shutdown();
    }
}
