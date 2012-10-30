/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.actors.remote;

import java.io.Serializable;

/**
 *
 * @author Christian Poliwoda <christian.poliwoda@gcsc.uni-frankfurt.de>
 */
public class Greeting implements Serializable {

    public final String who;

    public Greeting(String who) {
        this.who = who;
    }
}
