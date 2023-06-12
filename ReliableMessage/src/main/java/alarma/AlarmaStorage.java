package alarma;

import java.util.Map;
import java.util.UUID;

public class AlarmaStorage {

    private Map<UUID, Alarma> storage;

    public void saveAlarm(Alarma alarma){
        storage.put(UUID.randomUUID(), alarma);
    }
    public void deleteAlarm(String uuid){
        storage.remove(UUID.fromString(uuid));
    }

}
