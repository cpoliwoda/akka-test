/**
 *  Copyright (C) 2009-2012 Typesafe Inc. <http://www.typesafe.com>
 */
package test.calculator.remote.server;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.kernel.Bootable;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import test.calculator.remote.Configs;

//#setup
public class JCalculatorApplication implements Bootable {

    private ActorSystem system;

    public JCalculatorApplication() {
        Config config = ConfigFactory.parseString(Configs.getCalculator());     
        system = ActorSystem.create("CalculatorApplication", config);

        //actor that is provided by the server
        ActorRef actor = system.actorOf(new Props(JSimpleCalculatorActor.class),
                "simpleCalculator");
    }

    @Override
    public void startup() {
    }

    @Override
    public void shutdown() {
        system.shutdown();
    }
}
// #setup
