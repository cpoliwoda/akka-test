/**
 *  Copyright (C) 2009-2012 Typesafe Inc. <http://www.typesafe.com>
 */
package test.calculator.remote;

//#imports
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.kernel.Bootable;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
//#imports

//#setup
public class JLookupApplication implements Bootable {

    private ActorSystem system;
    private ActorRef actor;
    private ActorRef remoteActor;

    public JLookupApplication() {
//    system = ActorSystem.create("LookupApplication", ConfigFactory.load()
//        .getConfig("remotelookup"));

        Config config = ConfigFactory.parseString(Configs.getRemoteLookup());
        system = ActorSystem.create("LookupApplication", config);

        actor = system.actorOf(new Props(JLookupActor.class));
        remoteActor = system.actorFor(
                "akka://CalculatorApplication@127.0.0.1:2552/user/simpleCalculator");
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
// #setup
