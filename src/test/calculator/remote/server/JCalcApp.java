/**
 *  Copyright (C) 2009-2012 Typesafe Inc. <http://www.typesafe.com>
 * https://github.com/akka/akka/tree/v2.0.3/akka-samples/akka-sample-remote
 */
package test.calculator.remote.server;

public class JCalcApp {

  public static void main(String[] args) {
    JCalculatorApplication app = new JCalculatorApplication();
    System.out.println("Started Calculator Application - waiting for messages");
  }

}
