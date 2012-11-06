/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.pi.remote;

public class Work {

    private final int start;
    private final int nrOfElements;

    public Work(int start, int nrOfElements) {
        this.start = start;
        this.nrOfElements = nrOfElements;
    }

    public int getStart() {
        return start;
    }

    public int getNrOfElements() {
        return nrOfElements;
    }
}
