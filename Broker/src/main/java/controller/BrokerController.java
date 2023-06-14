package controller;


import com.zeroc.Ice.Current;
import servicios.AlarmaServicePrx;
import servicios.BrokerService;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Setter
public class BrokerController implements BrokerService {

    private Map<String, AlarmaServicePrx> serverMap;
    private ArrayList<String> clients;
    private LinkedList<AlarmaServicePrx> serverQueue;

    public BrokerController(){
        serverMap = new HashMap<>();
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
    public String subscribeServer(String serverId, AlarmaServicePrx server, Current current) {
        if (!validateRegister(serverMap, serverId)) {
            serverMap.put(serverId, server);
            serverQueue.addLast(server);
            return "Successful register of server: "+ serverId;
        }else{
            return "Failed register of server: "+ serverId;
        }
    }

    @Override
    public AlarmaServicePrx locateServer(String clientId, Current current) {
        AlarmaServicePrx serverId = serverQueue.pollFirst();
        serverQueue.addLast(serverId);
        return serverId;
    }


    private boolean validateClient(String clientId) {
        if(clients.contains(clientId)){
            System.out.println("Client already registered");
            return true;
        }else{
            return false;
        }
    }


    private boolean validateRegister(Map map, String serverId) {
        if(map.containsKey(serverId)){
            System.out.println("Already registered");
            return true;
        }else{
            return false;
        }
    }


}
