package neo.vn.lottery.api;

import com.google.gson.annotations.SerializedName;

public class TranDauEntity {
    @SerializedName("ten_vong")
    public String tenVong;
    @SerializedName("ngay_da")
    public String ngayDa;
    @SerializedName("doi_nha")
    public String doiNha;
    @SerializedName("banthang_doinha")
    public String banThangDoiNha;
    @SerializedName("doi_khach")
    public String doiKhach;
    @SerializedName("banthang_doikhach")
    public String banThangDoiKhach;
    @SerializedName("gio_da")
    public String gioDa;
}
