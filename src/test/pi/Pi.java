/**
 * Copyright (C) 2009-2012 Typesafe Inc. <http://www.typesafe.com>
 * http://doc.akka.io/docs/akka/2.0.1/intro/getting-started-first-java.html
 */
package test.pi;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;

public class Pi {

    public static void main(String[] args) {
        Pi pi = new Pi();
        pi.calculate(4, 10000, 10000);
    }

    static class Calculate {
    }

    public void calculate(final int nrOfWorkers, final int nrOfElements, final int nrOfMessages) {
        // Create an Akka system
        ActorSystem system = ActorSystem.create("PiSystem");

        // create the result listener, which will print the result and shutdown the system
        final ActorRef listener = system.actorOf(new Props(Listener.class), "listener");

        // create the master
        ActorRef master = system.actorOf( new Props(new UntypedActorFactory() {
            @Override
            public UntypedActor create() {
                return new Master(nrOfWorkers, nrOfMessages, nrOfElements, listener);
            }
        }), "master");

        // start the calculation
        master.tell(new Calculate());

    }
}
