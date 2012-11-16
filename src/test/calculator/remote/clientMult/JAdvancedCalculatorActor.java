/**
 *  Copyright (C) 2009-2012 Typesafe Inc. <http://www.typesafe.com>
 */
package test.calculator.remote.clientMult;

import akka.actor.UntypedActor;
import test.calculator.remote.Op;

//#actor
/**
 * This actor is send "via wire" to the server and calculations are done
 * on the server system.
 * 
 *        BUT HOW DOES THESE SENDING OF AN ACTOR WORK ?????
 * The creation of this actor on the server system is controlled by the configuration
 * file were the remote path is set.
 * 
 * If the server is not running before the client system is starting the remote
 * actor can not be created and NO communication is done. So this example do not work than.
 */
public class JAdvancedCalculatorActor extends UntypedActor {
    
  @Override
  public void onReceive(Object message) throws Exception {

    if (message instanceof Op.Multiply) {
      Op.Multiply multiply = (Op.Multiply) message;
      
      System.out.println("Calculating " + multiply.getN1() + " * " + multiply.getN2());
      
      // responds to the sender of the message with a NEW message that contains
      // the original send parameters, the result of the calculated operation
      // and a reference to the server actor (getSelf) that calculated the result
      getSender().tell( new Op.MultiplicationResult(
              multiply.getN1(), multiply.getN2(),multiply.getN1() * multiply.getN2()),
              getSelf());

    } else if (message instanceof Op.Divide) {
      Op.Divide divide = (Op.Divide) message;
      
      System.out.println("Calculating " + divide.getN1() + " / " + divide.getN2());
      
      // responds to the sender of the message with a NEW message that contains
      // the original send parameters, the result of the calculated operation
      // and a reference to the server actor (getSelf) that calculated the result
      getSender().tell(new Op.DivisionResult(
              divide.getN1(), divide.getN2(), divide.getN1() / divide.getN2()),
              getSelf());

    } else {
      unhandled(message);
    }
  }
}
// #actor
