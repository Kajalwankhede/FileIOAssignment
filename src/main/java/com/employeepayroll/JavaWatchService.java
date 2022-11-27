package com.employeepayroll;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

public class JavaWatchService {    // watch perticular directory and recrd what happening in that directory
    public final WatchService watcher;
    public final Map<WatchKey, Path> dirWatchers;


    //Create watch service and register the given directory
    public JavaWatchService(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.dirWatchers = new HashMap<WatchKey,Path>();//creating watch key and path
        scanAndRegisterDirectories(dir);
    }

    //Register the given directory with watch service
    public void registerDirWatchers(Path dir) throws IOException{
        WatchKey key = dir.register(watcher,ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        dirWatchers.put(key, dir); //adding each as watcher
    }

    //register the given directory with watch service, with the watch service
    public void scanAndRegisterDirectories(final Path start) throws IOException {// scanning all the directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult preVisitDirectory(Path dir,
                                                     BasicFileAttributes attrs) throws IOException{// walkfile tree gos every file directory recursively so,we can register the watch
                registerDirWatchers(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

/*Process all events for key queued to watchers*/
    public boolean processEvents() {
        while(true) {
            WatchKey key;  //wait for the key signalled
            try {
                key = watcher.take();
            }catch(InterruptedException exception) {
                return false;
            }
            Path dir = dirWatchers.get(key);
            if(dir==null) continue;
            for(WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
                Path name = ((WatchEvent<Path>)event).context();
                Path child = dir.resolve(name);
                System.out.format("%s : %s\n", event.kind().name(),child); //print event

                //if directory is created then register it and its sub-directories
                if(kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    try {
                        if(Files.isDirectory(child)) scanAndRegisterDirectories(child);
                    }catch (IOException exception) {}
                }else if(kind.equals(StandardWatchEventKinds.ENTRY_DELETE)) {
                    if(Files.isDirectory(child)) dirWatchers.remove(key);
                }
            }

            boolean valid = key.reset();
            if(!valid) {
                dirWatchers.remove(key);
                if(dirWatchers.isEmpty()) break; //all directories are inaccessible
            }
        }
        return true;
    }
}
