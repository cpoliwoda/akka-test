/**
 *  Copyright (C) 2009-2012 Typesafe Inc. <http://www.typesafe.com>
 */
package test.calculator.remote.clientMult;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.kernel.Bootable;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import test.calculator.remote.Configs;
import test.calculator.remote.InternalMsg;
import test.calculator.remote.Op;

//#setup
public class JCreationApplication implements Bootable {

    private ActorSystem system;
    private ActorRef actor;
    private ActorRef remoteActor;

    public JCreationApplication() {
        Config config = ConfigFactory.parseString(Configs.getRemoteCreation());
        system = ActorSystem.create("CreationApplication", config);

        //client actor
        actor = system.actorOf(new Props(JCreationActor.class));
        
        //client actor that seems to be created in client system,
        //BUT is created and runs on SERVER system.
        //These is controlled via CONFIG file (see Configs.getRemoteCreation() ).
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
