package database.core;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.json.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import database.core.api.DBManager;
import database.schemas.api.Entry;


public class LudomaniaDBManager implements DBManager {
    
    private static final String SEP = File.separator;
    private static final LudomaniaDBManager manager = new LudomaniaDBManager();
    
    private LudomaniaDBManager() {}
    
    public static LudomaniaDBManager getManager() {
        return manager;
    }
    
    @Override
    public boolean insert(Entry entry, String filename) {
        File file = this.findDBFile(filename);
        this.fooWrite(entry, file);

        return true;
    }
    
    @Override
    public boolean update(Entry entry, String filename) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
    @Override
    public boolean delete(Entry entry, String filename) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
    @Override
    public <E> E read(E entry, String filename) {
        File file = this.findDBFile(filename);
        this.fooRead(file);

        

        return null;
    }

    private void fooRead(File file) {
        try (final DataInputStream input = new DataInputStream(new FileInputStream(file))) {
            int c = 0;
            while ((c = input.read()) != -1) {
                System.out.println(c);
            }
            
            String out = input.readUTF();
            System.out.println(out);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(file);
            String usr = jsonNode.get("username").asText();
            String pwd = jsonNode.get("password").asText();
            System.out.println("Name: " + usr);
            System.out.println("Age: " + pwd);


            // file name is File.json

            // Object o = new JsonParser().parse(new FileReader(file));

            // JSONObject j = (JSONObject) o;

            // String Name = (String) j.get(“Name”);

            // String College = (String ) j.get(“College”);



            // System.out.println(“Name :” + Name);

            // System.out.println(“College :” +College);



        } catch (IOException ioEx) {

        }
    }

    private void fooWrite(Entry entry, File file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            JSONObject obj = entry.toJson();
            System.out.println(obj);            

            objectMapper.writeValue(file, obj);
        } catch (IOException ioEx) {

        }
    }
    
    private boolean unlockFile(File file) {
        return file.setWritable(true);
    }
    
    private boolean lockFile(File file) {
        return file.setWritable(false);
    }
    
    private File findDBFile(String filename) {
        final File currentDir = new File(System.getProperty("user.dir"));        
        File resources = new File(currentDir.getPath() + SEP + "src" + SEP + "main" + SEP + "java" + SEP + "database" + SEP + "resources");
        
        if (resources.isDirectory()) {
            File dbFile = new File(resources.getPath() + SEP + filename);
            
            if (dbFile.exists()) {
                return dbFile;
            } else {
                this.createDBFile(filename, dbFile);
            }
        } else {
            this.createDBDirectory("resources", resources);
        }

        return this.findDBFile(filename);
    }
    
    private boolean createDBFile(String filename, File directory) {
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
            return new File(currentDir.getParent() + SEP + "resources").mkdir();
        } catch(SecurityException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
    
}
