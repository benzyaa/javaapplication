/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.util;

import com.freebasicacc.model.Entity;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author benzyaa
 */
public class SqlUtil {
    public static String addSqlTail(Entity entity){
        StringBuffer sqlTail = new StringBuffer();
         if(StringUtil.isContainText(entity.getOrderBy())){sqlTail.append(" ORDER BY "+entity.getOrderBy());
            sqlTail.append(entity.getIsDesc()?" DESC ":" ASC ");
         }
         if(entity.getRowCount()>0)sqlTail.append(String.format(" LIMIT %s,%s ",entity.getRowStart(),entity.getRowCount()));
        return sqlTail.toString();
    }
    
    public static String createInCondition(String fieldName, Object[] inCriteriaStr){
        StringBuffer inCondition = new StringBuffer();
        inCondition.append("AND " + fieldName+ " IN (");
        for(int i=0;i<inCriteriaStr.length;i++){
            inCondition.append(String.format("'%s'", String.valueOf(inCriteriaStr[i])));
            inCondition.append((i<inCriteriaStr.length-1)?",":"");
        }
        inCondition.append(")");
        return inCondition.toString();
    }
    
}
