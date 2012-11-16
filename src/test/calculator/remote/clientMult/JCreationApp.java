/**
 *  Copyright (C) 2009-2012 Typesafe Inc. <http://www.typesafe.com>
 * https://github.com/akka/akka/tree/v2.0.3/akka-samples/akka-sample-remote
 */
package test.calculator.remote.clientMult;

import java.util.Random;
import test.calculator.remote.Op;

/**
 * Simulates the start of a client application, 
 * sending some request to the client and 
 * shutting down the client after work is done.
 * 
 * 
 */
public class JCreationApp {

    public static void main(String[] args) {
        //create client application
        JCreationApplication app = new JCreationApplication();
        System.out.println("Started Creation Application");
        
        generatSomeRequestForClientApp(app);
        
        app.shutdown();
    }

    /**
     * generating some requests to the client
     * 
     * @param app the client app which we want something to do
     */
    private static void generatSomeRequestForClientApp(JCreationApplication app) {
        
        Random r = new Random();
        
        for (int i = 0; i < 100; i++) {
         
            if (r.nextInt(100) % 2 == 0) {
                app.doSomething(new Op.Multiply(r.nextInt(100), r.nextInt(100)));
            } else {
                app.doSomething(new Op.Divide(r.nextInt(10000), r.nextInt(99) + 1));
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        }
    }
}
