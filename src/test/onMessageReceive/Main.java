/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.onMessageReceive;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class Main {

    public static void main(String[] args) {
        
        ActorSystem system = ActorSystem.create("MySystem");
        
        ActorRef actorRef1 = system.actorOf(
                new Props(MyUntypedActor.class), "MyUntypedActor1");
        
        ActorRef actorRef2 = system.actorOf(
                new Props(MyUntypedActor.class), "MyUntypedActor2");
        
        actorRef2.tell(" test 123 ", actorRef1);
        
        system.shutdown();
    }
}
