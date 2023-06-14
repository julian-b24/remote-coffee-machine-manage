package alarma;

import lombok.Getter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AlarmaStorage {
    private static final String STORAGE_FILE = "alarma_storage.txt";
    private Map<UUID, ReliableAlarma> storage;

    public AlarmaStorage(){
        storage = loadStorageFromFile();
    }

    public void saveStorageToFile() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(STORAGE_FILE);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(storage);
        } catch (IOException e) {
            System.err.println("Error saving alarma storage to file: " + e.getMessage());
        }
    }

    private Map<UUID, ReliableAlarma> loadStorageFromFile() {
        Map<UUID, ReliableAlarma> loadedStorage = new HashMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(STORAGE_FILE);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            Object object = objectInputStream.readObject();
            if (object instanceof Map) {
                loadedStorage = (Map<UUID, ReliableAlarma>) object;
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous storage found. Starting with empty storage.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading alarma storage from file: " + e.getMessage());
        }

        return loadedStorage;
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
