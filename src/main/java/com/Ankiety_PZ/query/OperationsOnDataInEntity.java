package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Ankiety;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

class OperationsOnDataInEntity<Type> extends OperationInSession {

    Boolean add(Type type){
        return modifyDataInEntity(type, true, false, false, true, null);
    }

    Boolean addWithOutTransaction(Type type, Session session){
        return modifyDataInEntity(type, true, false, false, false, session);
    }

    Boolean update(Type type){
        return modifyDataInEntity(type, false, true, false, true, null);
    }

    Boolean updateWithOutTransaction(Type type, Session session){
        return modifyDataInEntity(type, false, true, false, false, session);
    }

    Boolean delete(Type type){
        return modifyDataInEntity(type, false, false, true, true, null);
    }

    Boolean deleteWithOutTransaction(Type type, Session session){
        return modifyDataInEntity(type, false, false, true, false, session);
    }

    private Boolean modifyDataInEntity(Type object, boolean add, boolean update, boolean delete, boolean transaction, Session session){
        Boolean result = false;
        Session sessionLocal = null;
        try{
            if(session == null) {
                sessionLocal = openSession();
            }else{
                sessionLocal = session;
            }
            if(transaction) {
                super.transaction = beginTransaction(sessionLocal);
            }
            if(add){
                sessionLocal.save(object);
            }
            if(update){
                sessionLocal.update(object);
            }
            if(delete) {
                sessionLocal.delete(object);
            }
            commitTransaction(super.transaction);
            result = true;
        }catch(Exception e){
            if(transaction) {
                rollbackTransaction(super.transaction);
            }
            logException(e);
        }finally {
            if(session == null) {
                closeSession(sessionLocal);
            }
        }
        return result;
    }

    Type selectObjectSQL(String query){
        return selectObject(query, false);
    }

    Type selectObjectHQL(String query){
        return selectObject(query, true);
    }


    private Type selectObject(String query, boolean hql){
        Type result = null;
        try{
            session = openSession();
            if(hql) {
                result = (Type) session.createQuery(query).uniqueResult();
            }else{
                result = (Type) session.createSQLQuery(query).uniqueResult();
            }
        }catch(Exception e){
            logException(e);
        }finally {
            closeSession(session);
        }
        return result;
    }

    List<Type> selectListSQL(String query){
        return selectList(query, false);
    }

    List<Type> selectListHQL(String query){
        return selectList(query, true);
    }

    private List<Type> selectList(String query, boolean hql){
        List<Type> result = new ArrayList<>();
        try{
            session = openSession();
            if(hql) {
                result = (ArrayList<Type>) session.createQuery(query).list();
            }else{
                result = (ArrayList<Type>) session.createSQLQuery(query).list();
            }
        }catch(Exception e){
            logException(e);
        }finally {
            closeSession(session);
        }
        return result;
    }



}
