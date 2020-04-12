package dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO <T, PK extends Serializable> {

    boolean create(T newObject);
    T read(PK primaryKey);
}
