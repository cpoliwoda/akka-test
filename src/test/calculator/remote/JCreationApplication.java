/**
 *  Copyright (C) 2009-2012 Typesafe Inc. <http://www.typesafe.com>
 */
package test.calculator.remote;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.kernel.Bootable;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

//#setup
public class JCreationApplication implements Bootable {

    private ActorSystem system;
    private ActorRef actor;
    private ActorRef remoteActor;

    public JCreationApplication() {
//    system = ActorSystem.create("CreationApplication", ConfigFactory.load()
//        .getConfig("remotecreation"));

        Config config = ConfigFactory.parseString(Configs.getRemoteCreation());
        system = ActorSystem.create("CreationApplication", config);

        actor = system.actorOf(new Props(JCreationActor.class));
        remoteActor = system.actorOf(new Props(JAdvancedCalculatorActor.class),
                "advancedCalculator");
    }

    public void doSomething(Op.MathOp mathOp) {
        actor.tell(new InternalMsg.MathOpMsg(remoteActor, mathOp), null);
    }

    @Override
    public void startup() {
    }

    @Override
    public void shutdown() {
        system.shutdown();
    }
}
//#setup
