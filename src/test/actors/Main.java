/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.actors;

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
    
static String provider = "akka.actor.LocalActorRefProvider";

    public static void main(String[] args) {
        
        
        Config config = ConfigFactory.parseString(
                "    akka {"
                + "      actor {"
                + "        provider = \""+provider+"\""
                + "      }"
                + "    }");
        
        ActorSystem system = ActorSystem.create("MySystem",config);
        
        ActorRef greeter = system.actorOf(
                new Props(GreetingActor.class), "greeter");
        
        greeter.tell(new Greeting("Charlie Parker"));
        
        system.shutdown();
    }
}
