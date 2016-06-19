/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freebasicacc.dao;

import com.freebasicacc.model.Customer;
import java.sql.Connection;
import java.util.List;
import java.util.Set;

/**
 *
 * @author benzyaa
 */
public interface CustomerDAO {
   public void insertCustomer(Customer customer) throws Exception;
   public void updateCustomer(Customer customer) throws Exception;
   public void updateCustomer(Customer customer,Connection conn) throws Exception;
   public List<Customer> getCustomerByCriteria(Customer customer) throws Exception;
   public List<Customer> getCustomerByCriteria(Set<String> customerId,Connection connection) throws Exception;
   public List<Customer> getCustomerByCriteria(Customer customer,Connection conn)throws Exception;
   public int getAllRowCount() throws Exception;
}
