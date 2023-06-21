package com.chency.spring.event;

import java.util.Observable;
import java.util.Observer;

/**
 * {@link Observer} 示例
 *
 * @author cchangy
 * @date 2023/06/18 21:37
 */
public class ObserverDemo {

    public static void main(String[] args) {
        Observable observable = new EventObservable();
        observable.addObserver(new EventObserver());

        observable.notifyObservers("java event...");
    }

    static class EventObservable extends Observable {

        @Override
        public void setChanged() {
            super.setChanged();
        }

        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(arg);
            clearChanged();
        }
    }

    static class EventObserver implements Observer {

        @Override
        public void update(Observable observable, Object message) {
            System.out.println("On Event: " + message);
        }
    }
}
