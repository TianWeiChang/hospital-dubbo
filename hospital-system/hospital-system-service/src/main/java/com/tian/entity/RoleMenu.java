package com.tian.entity;

import java.io.Serializable;

public class RoleMenu  implements Serializable {
    private Integer [] ids;
    private Integer roleid;


    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}
