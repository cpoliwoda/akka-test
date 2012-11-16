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
        System.out.println("Pi_Client.calculate() START:");

//        Config config = ConfigFactory.parseString(Configs.createConfigForClient(
//                Configs.serverIP, Configs.clientPort));//, Configs.clientSystemName, Configs.clientActorName));

        Config config = ConfigFactory.parseString(Configs.getConfigForClientRemoteLookup());
        System.out.println("Pi_Client: ActorSystem.create()");

        ////local
        ActorSystem system = ActorSystem.create(Configs.clientSystemName, config);

        System.out.println("Pi_Client: system.actorOf(" + Configs.clientActorName + ")");

        // create the result listener, which will print the result and shutdown the system
        ////local
        final ActorRef listener = system.actorOf(new Props(Listener.class), Configs.clientActorName);

        // connect to server
        // REMOTE
        //    akka://<actorsystemname>@<hostname>:<port>/<actor path>
        String remotePathForActor = "akka://" + Configs.serverSystemName + "@"
                + Configs.serverIP + ":" + Configs.serverPort + "/user/" + Configs.serverActorName;

        System.out.println("Pi_Client: REMOTE system.actorOf( \n " + remotePathForActor + " )");

        final ActorRef master = system.actorFor(remotePathForActor);

        System.out.println("master = " + master);
        System.out.println("Pi_Client: master.tell( Calculate )");

        // start the calculation
        master.tell(new Calculate(nrOfWorkers, nrOfElements, nrOfMessages, listener));

        System.out.println("Pi_Client: master.tell( Shutdown )");

        // shutdown server after calculation
        master.tell(new Shutdown());

        System.out.println("Pi_Client.calculate() END:");
    }
}
