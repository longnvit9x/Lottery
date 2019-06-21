package neo.vn.lottery.model;

import neo.vn.lottery.api.LotteryEntity;

public class Lottery {
    public String tenMien;
    public String ngayThang;
    public String dacBiet;
    public String nhat;
    public String nhi1;
    public String nhi2;
    public String ba1;
    public String ba2;
    public String ba3;
    public String ba4;
    public String ba5;
    public String ba6;
    public String tu1;
    public String tu2;
    public String tu3;
    public String tu4;
    public String nam1;
    public String nam2;
    public String nam3;
    public String nam4;
    public String nam5;
    public String nam6;
    public String sau1;
    public String sau2;
    public String sau3;
    public String bay1;
    public String bay2;
    public String bay3;
    public String bay4;

    public static Lottery convertFromEntity(LotteryEntity entity) {
        Lottery model = new Lottery();
        model.tenMien = entity.tenMien;
        model.ngayThang = entity.ngayThang;
        model.dacBiet = entity.dacBiet;
        model.nhat = entity.nhat;
        model.nhi1 = entity.nhi1;
        model.nhi2 = entity.nhi2;
        model.ba1 = entity.ba1;
        model.ba2 = entity.ba2;
        model.ba3 = entity.ba3;
        model.ba4 = entity.ba4;
        model.ba5 = entity.ba5;
        model.ba6 = entity.ba6;
        model.tu1 = entity.tu1;
        model.tu2 = entity.tu2;
        model.tu3 = entity.tu3;
        model.tu4 = entity.tu4;
        model.nam1 = entity.nam1;
        model.nam2 = entity.nam2;
        model.nam3 = entity.nam3;
        model.nam4 = entity.nam4;
        model.nam5 = entity.nam5;
        model.nam6 = entity.nam6;
        model.sau1 = entity.sau1;
        model.sau2 = entity.sau2;
        model.sau3 = entity.sau3;
        model.bay1 = entity.bay1;
        model.bay2 = entity.bay2;
        model.bay3 = entity.bay3;
        model.bay4= entity.bay4;
        return model;
    }
}
