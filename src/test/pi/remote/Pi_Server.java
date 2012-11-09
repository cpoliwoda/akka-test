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

        Config config = ConfigFactory.parseString(Configs.createConfig(
                Configs.serverIP, Configs.serverPort));

        // Create an Akka system
        ActorSystem system = ActorSystem.create(Configs.serverSystemName, config);

        // create the master
        ActorRef master = system.actorOf(new Props(Master.class), Configs.serverActorName);
    }
}
