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
public class Main1 { // SERVER

//    final static String actorsystemname = "MySystem";
    final static String hostname = "127.0.0.1";
    final static String port = "2552"; //"2552";//"1099";
    final static String deployActor = "/greeter";
    final static String actorpath = "user" + deployActor;
    final static String actorName = "greeter";

    public static void main(String[] args) {

        String systemName = "sys1";

        Config config = ConfigFactory.parseString(Configs.createConfig("127.0.0.1", 2552, systemName, actorName));

        ActorSystem system = ActorSystem.create(systemName, config);
        ActorRef greeter = system.actorOf(new Props(GreetingActor.class), actorName);
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
