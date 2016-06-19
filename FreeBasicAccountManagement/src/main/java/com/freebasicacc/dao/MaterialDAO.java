package com.freebasicacc.dao;

import com.freebasicacc.model.Material;
import java.sql.Connection;
import java.util.List;
import java.util.Set;

public interface MaterialDAO {
    public void insertMaterial(Material material)throws Exception;
    public void updateMaterial(Material material)throws Exception;
    public List<Material> getMaterialAll()throws Exception;
    public List<Material> getMaterialByCriteria(Material material) throws Exception;
    public List<Material> getUnitByCriteria(Set<String> materialId,Connection connection) throws Exception;
    public List<Material> getMaterialByCriteria(Material material,Connection connection) throws Exception;
}