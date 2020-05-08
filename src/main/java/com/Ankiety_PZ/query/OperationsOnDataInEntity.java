package com.Ankiety_PZ.query;

import java.util.List;

class OperationsOnDataInEntity<Type> extends OperationInSession {

    protected Boolean modifyDataInEntity(Type object, boolean add, boolean update, boolean delete, boolean transaction){
        Boolean result = false;
        try{
            session = openSession();
            if(transaction) {
                super.transaction = beginTransaction(session);
            }
            if(add){
                session.save(object);
            }
            if(update){
                session.update(object);
            }
            if(delete) {
                session.delete(object);
            }
            commitTransaction(super.transaction);
            result = true;
        }catch(Exception e){
            if(transaction) {
                rollbackTransaction(super.transaction);
            }
            logException(e);
        }finally {
            closeSession(session);
        }
        return result;
    }

    protected Type selectObject(String hql){
        Type result = null;
        try{
            session = openSession();
            result = (Type) session.createQuery(hql).uniqueResult();
        }catch(Exception e){
            logException(e);
        }finally {
            closeSession(session);
        }
        return result;
    }

    protected List<Type> selectList(List<Type> type){
        List<Type> result = null;
        try{
            session = openSession();
            result = type;
        }catch(Exception e){
            logException(e);
        }finally {
            closeSession(session);
        }
        return result;
    }



}
