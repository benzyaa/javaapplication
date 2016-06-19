/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.services;

import java.sql.Connection;
import com.freebasicacc.dao.SequenceDAOImpl;

/**
 *
 * @author benzyaa
 */
public class SequenceService {
    private static final SequenceService sequenceService = new SequenceService();
    private SequenceService(){
    }
    public static SequenceService getInstance(){
        return sequenceService;
    }
    public synchronized String nextVal(String sequenceName,Connection connection) throws Exception{
        SequenceDAOImpl sequeceDAO = SequenceDAOImpl.getInstance();
        String nextVal = sequeceDAO.nextVal(sequenceName, connection);
        SequenceDAOImpl.deleteInstance();
        return nextVal;
    }
    public synchronized void reset(String sequenceName,Connection connection) throws Exception{
        SequenceDAOImpl sequeceDAO = SequenceDAOImpl.getInstance();
        sequeceDAO.reset(sequenceName, connection);
        SequenceDAOImpl.deleteInstance();
    }
}