/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.util;

import com.freebasicacc.main.Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author benzyaa
 */
public class FileUtil {
    public static String getCurrentJarPath(){
        try {
            URI path=Main.class.getProtectionDomain().getCodeSource().getLocation().toURI();
             String path2 = path.getRawPath();
             path2=path2.substring(1);
             String[] separatedFilPath = path2.split("/");
             return path2.replaceAll(separatedFilPath[separatedFilPath.length-1], "");
        } catch (URISyntaxException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
        //return "C:/TIPMETEE/";
    }
    
    public static boolean isHandedByAnotherProcess(File file) throws FileNotFoundException, IOException{
       FileLock lock = null;
       FileChannel channel = null;
        try {
              channel = new RandomAccessFile(file, "rw").getChannel();
              lock = channel.lock();
              lock = channel.tryLock();
            return false;
        } catch (OverlappingFileLockException e) {
            e.printStackTrace();
            // File is open by someone else
            return true;
          } finally {
            lock.release();
        }
    }   
}