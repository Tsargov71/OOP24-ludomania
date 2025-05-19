package database.core;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import database.core.api.DBManager;
import database.schemas.api.Entry;

public final class LudomaniaDBManager implements DBManager {
    
    private static final String SEP = File.separator;
    private static final String DB_DIRECTORY_NAME = "resources";
    private static final LudomaniaDBManager MANAGER = new LudomaniaDBManager();
    
    private LudomaniaDBManager() {}
    
    public static LudomaniaDBManager getManager() {
        return MANAGER;
    }
    
    @Override
    public <T extends Entry> boolean write(T entry, String filename) {
        boolean result;
        
        try {
            File file = this.findDBFile(filename);
            
            ObjectMapper objectMapper = new ObjectMapper();
            
            Optional<List<Entry>> list = this.readAll(filename);
            List<Entry> entries = list.isEmpty() ? Arrays.asList() : list.get();
            
            entries.removeIf(e -> e.getIdentifier() == entry.getIdentifier());
            entries.add(entry);
            
            objectMapper.writeValue(file, entries);
            
            System.out.println("List of users written to " + filename);
            
            result = true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            result = false;
        }
        
        return result;
    }
    
    @Override
    public boolean delete(Entry entry, final String filename) {
        boolean result;        
        File file;
        try {
            file = this.findDBFile(filename);
            
            ObjectMapper objectMapper = new ObjectMapper();
            
            Optional<List<Entry>> list = this.readAll(filename);
            List<Entry> entries = list.isEmpty() ? Arrays.asList() : list.get();
            
            entries.removeIf(null);
            
            objectMapper.writeValue(file, entries);
            
            System.out.println("List of users written to " + filename);
            
            result = true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            result = false;
        }
        
        return result;
    }
    
    @Override
    public <T extends Entry> Optional<T> read(T entry, final String filename) {
        Optional<T> result = Optional.empty();
        try {
            File file = this.findDBFile(filename);
            ObjectMapper objectMapper = new ObjectMapper();
            
            // Read the JSON array into a List of User objects
            List<T> entries = objectMapper.readValue(file, new TypeReference<List<Entry>>() {});
            
            result = entries.stream().filter(u -> u.getIdentifier() == entry.getIdentifier()).findFirst();
            if (result.isPresent()) {
                System.out.println(result);
            }
            
            System.out.println("List of Users:");
            for (Entry user : entries) {
                System.out.println(user);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return result;
    }
    
    @Override
    public <T extends Entry> Optional<List<T>> readAll(String filename) {
        Optional<List<T>> result = Optional.empty();
        try {
            File file = this.findDBFile(filename);
            ObjectMapper objectMapper = new ObjectMapper();
            
            // Read the JSON array into a List of User objects
            result = objectMapper.readValue(file, new TypeReference<List<T>>() {});            
            
            System.out.println("List of Users:");
            for (Entry user : result.get()) {
                System.out.println(user);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return result;
    }
    
    @Override
    public boolean exists(Entry entry, String filename) {
        boolean result = false;
        try {
            File file = this.findDBFile(filename);
            ObjectMapper objectMapper = new ObjectMapper();
            
            // Read the JSON array into a List of User objects
            List<Entry> entries = objectMapper.readValue(file, new TypeReference<List<Entry>>() {});
            
            result = entries.stream().anyMatch(e -> e.getIdentifier() == entry.getIdentifier());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            result = false;
        }

        return result;
    }
    
    private File findDBFile(final String filename) throws Exception {
        final File currentDir = new File(System.getProperty("user.dir"));        
        File resources = new File(currentDir.getParent() + SEP + DB_DIRECTORY_NAME);
        
        if (resources.isDirectory()) {
            File dbFile = new File(resources.getPath() + SEP + filename);
            
            if (dbFile.exists()) {
                return dbFile;
            } else {
                if (!this.createDBFile(filename, dbFile)) {
                    throw new Exception("cannot create db file:" + filename);
                }
            }
        } else {
            if (!this.createDBDirectory(DB_DIRECTORY_NAME, resources)) {
                throw new Exception("cannot create db directory");
            }
        }
        
        return this.findDBFile(filename);
    }
    
    private boolean createDBFile(final String filename, File directory) {
        try {
            return new File(directory.getPath() + SEP + filename).createNewFile();
        } catch(IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    private boolean createDBDirectory(String dbDirectoryName, File directory) {
        try {
            final File currentDir = new File(System.getProperty("user.dir"));
            return new File(currentDir.getParent() + SEP + DB_DIRECTORY_NAME).mkdir();
        } catch(SecurityException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }    
}
