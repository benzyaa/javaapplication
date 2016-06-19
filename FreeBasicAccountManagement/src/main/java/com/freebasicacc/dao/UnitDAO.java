/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.dao;

import com.freebasicacc.model.Unit;
import java.sql.Connection;
import java.util.List;
import java.util.Set;

/**
 *
 * @author benzyaa
 */
public interface UnitDAO {
    public void insertUnit(Unit unit) throws Exception;
    public void updateUnit(Unit unit) throws Exception;
    public List<Unit> getUnitAll() throws Exception;
    public List<Unit> getUnitByCriteria(Unit unit) throws Exception;
    public List<Unit> getUnitByCriteria(Set<String> unitSet,Connection connection) throws Exception;
    public List<Unit> getUnitByCriteria(Unit unit,Connection connection) throws Exception;
}
