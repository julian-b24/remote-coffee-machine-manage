package alarma;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AlarmaStorage {

    private Map<UUID, ReliableAlarma> storage;

    public AlarmaStorage(){
        storage = new HashMap<UUID, ReliableAlarma>();
    }

    public synchronized void saveAlarm(ReliableAlarma alarma, UUID uuid){
        System.out.println("Alarma guardada: " + uuid);
        storage.put(uuid, alarma);
    }

    public synchronized Map<UUID, ReliableAlarma> getStorage(){
        return storage;
    }
    public void deleteAlarm(String uuid){
        storage.remove(UUID.fromString(uuid));
    }

}
