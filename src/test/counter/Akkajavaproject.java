/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.counter;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.Future;
import akka.dispatch.OnSuccess;
import akka.pattern.Patterns;


public class Akkajavaproject {
    
    public static void main(String... args) {
        ActorSystem system = ActorSystem.create("Akkajavaproject");

        ActorRef counter = system.actorOf(new Props(Counter.class));

        counter.tell("tick");
        counter.tell("tick");
        counter.tell("tick");

        Future future = Patterns.ask(counter, "get", 5000);

        future.onSuccess(new OnSuccess<Integer>() {
            @Override
            public void onSuccess(Integer count) {
                System.out.println("Count is " + count);
            }
        });

        system.shutdown();
    }

}
