package service;

import model.Permission;

public interface PermissionService {
    public void create(Permission permission);

    public void update(Permission permission);

    public void remove(Permission permission);

    public Permission findByName(String name);
}
