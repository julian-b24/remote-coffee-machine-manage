package alarma;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class AlarmaStorage {

    private Map<UUID, Alarma> storage;

    public void saveAlarm(Alarma alarma, UUID uuid){
        storage.put(uuid, alarma);
    }
    public void deleteAlarm(String uuid){
        storage.remove(UUID.fromString(uuid));
    }

}
