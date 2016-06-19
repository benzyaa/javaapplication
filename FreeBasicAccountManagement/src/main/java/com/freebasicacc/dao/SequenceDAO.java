/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.dao;

import java.sql.Connection;

/**
 *
 * @author benzyaa
 */
public interface SequenceDAO {
    public String nextVal(String sequenceName,Connection connection) throws Exception;
    public void reset(String sequenceName,Connection connection) throws Exception;
}
