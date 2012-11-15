/**
 *  Copyright (C) 2009-2012 Typesafe Inc. <http://www.typesafe.com>
 * https://github.com/akka/akka/tree/v2.0.3/akka-samples/akka-sample-remote
 */
package test.calculator.remote;

import java.util.Random;

public class JCreationApp {

    public static void main(String[] args) {
        JCreationApplication app = new JCreationApplication();
        System.out.println("Started Creation Application");

        Random r = new Random();
//        while (true) {
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
        
        app.shutdown();
    }
}
