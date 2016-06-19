/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ObjectUtil {
	public static Object deepCopy(Object object){
			ByteArrayOutputStream bos = null;
	 		ObjectOutputStream oos = null;
 		try {
 			bos = new ByteArrayOutputStream();
 	 		oos = new ObjectOutputStream(bos);
 	 		oos.writeObject(object);
 	 		oos.flush();
 	 		oos.close();
 	 		bos.close();
 	 		byte [] byteData = bos.toByteArray();
 	 		ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
			return new ObjectInputStream(bais).readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
 		return null;
	}
}
