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
public class Main1 {

    final static String actorsystemname = "MySystem";
    final static String hostname = "141.2.38.37";
    final static String port = "2552";//"1099";
    final static String deployActor = "/greeter";
    final static String actorpath = "user" + deployActor;
    
    final static String configString = "    akka {\n"
                + "      actor {\n"
                + "        provider = \"akka.remote.RemoteActorRefProvider\"\n"
                + "      }\n"
                + "      deployment {\n"
                //                + "            default {\n"
                //                + "                remote = \""+actorpath+"\"\n" 
                //                + "            }\n"
                + "             " + deployActor + " {\n"
                + "                 remote = \"akka://" + actorsystemname + "@" + hostname + ":" + port +"\"\n"
                + "             }"
                + "      }\n"
                + "    }";

    public static void main(String[] args) {

        Config config = ConfigFactory.parseString(configString);

        ActorSystem system = ActorSystem.create(actorsystemname, config);

    }
}

/*  JAVA EXAMPLE FROM: http://akka.io/#remoting


    // ------------------------------
    // config on all machines
    akka {
    actor {
    provider = akka.remote.RemoteActorRefProvider
    deployment {
    /greeter {
    remote = akka://MySystem@machine1:2552
    }
    }
    }
    }
     
    // ------------------------------
    // define the greeting actor and the greeting message
    public class Greeting implements Serializable {
    public final String who;
    public Greeting(String who) { this.who = who; }
    }
     
    public class GreetingActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
     
    public void onReceive(Object message) throws Exception {
    if (message instanceof Greeting)
    log.info("Hello " + ((Greeting) message).who);
    }
    }
     
    // ------------------------------
    // on machine 1: empty system, target for deployment from machine 2
    ActorSystem system = ActorSystem.create("MySystem");
     
    // ------------------------------
    // on machine 2: Remote Deployment - deploying on machine1
    ActorSystem system = ActorSystem.create("MySystem");
    ActorRef greeter = system.actorOf(new Props(GreetingActor.class), "greeter");
     
    // ------------------------------
    // on machine 3: Remote Lookup (logical home of “greeter” is machine2, remote deployment is transparent)
    ActorSystem system = ActorSystem.create("MySystem");
    ActorRef greeter = system.actorFor("MySystem@machine2:2552/user/greeter");
    greeter.tell(new Greeting("Sonny Rollins"));


*/
