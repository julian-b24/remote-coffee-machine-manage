package controller;


import com.zeroc.Ice.Current;
import servicios.AlarmaServicePrx;
import servicios.BrokerService;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;

@Setter
public class BrokerController implements BrokerService {

    private ArrayList<String> clients;
    private LinkedList<AlarmaServicePrx> serverQueue;

    public BrokerController(){
        serverQueue = new LinkedList<>();
        clients = new ArrayList<>();
    }

    @Override
    public String subscribeClient(String clientId, Current current) {
        if (!validateClient(clientId)) {
            clients.add(clientId);
            return "Successful register of client: "+ clientId;
        }else{
            return "Failed register of client: "+ clientId;
        }
    }

    @Override
    public String subscribeServer(AlarmaServicePrx server, Current current) {
        if (!serverQueue.contains(server)) {
            serverQueue.addLast(server);
            return "Successful register of server";
        }else{
            return "Failed register of server";
        }
    }

    @Override
    public AlarmaServicePrx locateServer(String clientId, Current current) {
        AlarmaServicePrx server = serverQueue.pollFirst();
        serverQueue.addLast(server);
        return server;
    }


    private boolean validateClient(String clientId) {
        if(clients.contains(clientId)){
            System.out.println("Client already registered");
            return true;
        }else{
            return false;
        }
    }

}
