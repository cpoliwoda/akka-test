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

public class Pi_Server {

    public static void main(String[] args) {
        System.out.println("Pi_Server START:");

//        Config config = ConfigFactory.parseString(Configs.createConfigForServer(
//                Configs.serverIP, Configs.serverPort, Configs.serverSystemName, Configs.serverActorName));

        Config config = ConfigFactory.parseString(Configs.getConfigForServer());
        System.out.println("Pi_Server ActorSystem.create():");

        // Create an Akka system
        ActorSystem system = ActorSystem.create(Configs.serverSystemName, config);

        System.out.println("Pi_Server system.actorOf(" + Configs.serverActorName + "):");

        // create the master
        ActorRef master = system.actorOf(new Props(Master.class), Configs.serverActorName);

        System.out.println("master = "+master);
        
        System.out.println("Pi_Server END");
    }
}
