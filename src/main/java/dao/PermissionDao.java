package dao;

import model.Permission;

public interface PermissionDao {

    public void create(Permission permission);

    public void update(Permission permission);

    public void remove(Permission permission);

    public Permission findByName(String name);

}
