package neo.vn.lottery.model;

import neo.vn.lottery.api.TranDauEntity;

public class TranDau extends SportItem {
    public String tenVong;
    public String ngayDa;
    public String doiNha;
    public String banThangDoiNha;
    public String doiKhach;
    public String banThangDoiKhach;
    public String gioDa;

    public static TranDau convertFromEntity(TranDauEntity entity) {
        TranDau model = new TranDau();
        model.tenVong = entity.tenVong;
        model.ngayDa = entity.ngayDa;
        model.doiNha = entity.doiNha;
        model.banThangDoiNha = entity.banThangDoiNha;
        model.doiKhach = entity.doiKhach;
        model.banThangDoiKhach = entity.banThangDoiKhach;
        model.gioDa = entity.gioDa;
        return model;
    }
}
