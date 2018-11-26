package com.myrepublic.numbermanage.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;   
  
/**
 * @date 2018/11/25
 * @desc Generic DAO
 */

public interface GenericDao<T extends Serializable, PK extends Serializable>   
{   
   
    public T get(PK id);   
    
    public List<T> loadAll();   
   
    public void update(T entity);   
   
    public void save(T entity);   
    
    public void saveOrUpdate(T entity);   
   
    public void saveOrUpdate(Collection<T> entities);   
   
    public void delete(T entity);   
   
    public void deleteByKey(PK id);   
   
    public void deleteAll(Collection<T> entities);   
    
    public List<T> find(String queryString, Object[] values);   
    
    public List<T> find(String queryString, Object value);   
    	 
  
  
}