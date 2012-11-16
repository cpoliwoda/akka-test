/**
 *  Copyright (C) 2009-2012 Typesafe Inc. <http://www.typesafe.com>
 */
package test.calculator.remote.clientAdd;

//#imports
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.kernel.Bootable;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import test.calculator.remote.Configs;
import test.calculator.remote.InternalMsg;
import test.calculator.remote.Op;
//#imports

//#setup
public class JLookupApplication implements Bootable {

    private ActorSystem system;
    private ActorRef actor;
    private ActorRef remoteActor;

    public JLookupApplication() {
        Config config = ConfigFactory.parseString(Configs.getRemoteLookup());
        system = ActorSystem.create("LookupApplication", config);

        //client actor
        actor = system.actorOf(new Props(JLookupActor.class));
        
        //server actor to interact with
        remoteActor = system.actorFor(
                "akka://CalculatorApplication@127.0.0.1:2552/user/simpleCalculator");
    }

    /**
     * Calling this method if you want the client something to do.
     * The client actor instead redirects the request to the server actor.
     * 
     * @param mathOp the mathematic operation that should be calculated
     */
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
