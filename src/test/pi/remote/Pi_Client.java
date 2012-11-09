/**
 * based on:
 * Copyright (C) 2009-2012 Typesafe Inc. <http://www.typesafe.com>
 * http://doc.akka.io/docs/akka/2.0.1/intro/getting-started-first-java.html
 */
package test.pi.remote;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Pi_Client {

    public static void main(String[] args) {
        Pi_Client pi = new Pi_Client();
        pi.calculate(4, 10000, 10000);
    }

    public void calculate(final int nrOfWorkers, final int nrOfElements, final int nrOfMessages) {
        
        Config config = ConfigFactory.parseString( Configs.createConfig(
                    Configs.serverIP, Configs.clientPort, Configs.clientSystemName, Configs.clientActorName));

        ////remote
        ActorSystem system = ActorSystem.create(Configs.clientSystemName, config);
        
        // create the result listener, which will print the result and shutdown the system
        ////local
        final ActorRef listener = system.actorOf(new Props(Listener.class), Configs.clientActorName);

        // connect to server
        // REMOTE
        //    akka://<actorsystemname>@<hostname>:<port>/<actor path>
        final ActorRef master = system.actorFor("akka://" + Configs.serverSystemName
                + "@" + Configs.serverIP + ":" + Configs.serverPort + "/user/" + Configs.serverActorName);

        // start the calculation
        master.tell(new Calculate(nrOfWorkers, nrOfElements, nrOfMessages, listener));
    }
}
