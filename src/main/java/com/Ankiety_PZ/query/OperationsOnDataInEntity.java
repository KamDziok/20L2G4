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
        Type type = null;
        try{
            session = openSession();
            query = session.createQuery(hql);
            type = (Type) query.uniqueResult();
        }catch(Exception e){
            logException(e);
        }finally {
            closeSession(session);
        }
        return type;
    }

}
