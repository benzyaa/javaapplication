/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.util;

import java.util.Collection;

/**
 *
 * @author benzyaa
 */
public class CollectionUtil {
    public static boolean isHasElement(Collection collection){
        return !(collection == null || collection.isEmpty() || collection.size() == 0);
    }
}