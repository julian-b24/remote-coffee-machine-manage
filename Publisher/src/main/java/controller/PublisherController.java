package controller;


import com.zeroc.Ice.Current;
import lombok.AllArgsConstructor;
import lombok.Setter;
import receta.Receta;
import receta.RecetasStorage;
import servicios.PublisherService;
import servicios.SubscriberServicePrx;

import java.util.ArrayList;

@Setter
@AllArgsConstructor
public class PublisherController implements PublisherService {
    private ArrayList<SubscriberServicePrx> subscribers;

    @Override
    public void attach(SubscriberServicePrx subscriberProxy, Current current) {
        System.out.println("Nuevo cliente conectado");
        subscribers.add(subscriberProxy);
    }

    @Override
    public void reportChange(Current current) {
        System.out.println("Cambio reportado");
        System.out.println(subscribers);
        for (SubscriberServicePrx subscriber: subscribers) {
            System.out.println("Reportando");
            subscriber.notifySub();
        }
    }

}
