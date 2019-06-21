package neo.vn.lottery.model;

import neo.vn.lottery.api.GiauDauEntity;

public class GiaiDau {
    public String id;
    public String name;

    public static GiaiDau convertFromEntity(GiauDauEntity entity) {
        GiaiDau giaiDau = new GiaiDau();
        giaiDau.id = entity.id;
        giaiDau.name = entity.tenGiai;
        return giaiDau;
    }
}
