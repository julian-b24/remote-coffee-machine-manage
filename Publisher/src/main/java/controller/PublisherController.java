package controller;


import com.zeroc.Ice.Current;
import lombok.Setter;
import receta.Receta;
import receta.RecetasStorage;
import servicios.PublisherService;
import servicios.SubscriberServicePrx;

import java.util.ArrayList;

@Setter
public class PublisherController implements PublisherService {

    private RecetasStorage recetasStorage;
    private ArrayList<SubscriberServicePrx> subscribers;

    @Override
    public void attach(SubscriberServicePrx subscriberProxy, Current current) {
        subscribers.add(subscriberProxy);
    }

    @Override
    public void reportChange(Receta receta, Current current) {
        recetasStorage.agregarReceta(receta);
        for (SubscriberServicePrx subscriber: subscribers) {
            subscriber.notifySub();
        }
    }

    @Override
    public Receta getUpdate(Current current) {
        return recetasStorage.obtenerNuevaReceta();
    }
}
