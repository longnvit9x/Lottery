package neo.vn.lottery.api;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface APIService {

    @GET("web/kqsx.jsp")
    Observable<LotteryEntity> getLottery(@Query("ten_mien") String tenMien, @Query("ngay_thang") String ngaythang);

    @GET("web/list_giadau.jsp")
    Observable<ArrayList<GiauDauEntity>> getGiaiDau(@Query("ten_giaidau") String tenGiaiDau);

    @GET("web/list_trandau.jsp")
    Observable<ArrayList<TranDauEntity>> getListTranDau(@Query("id_giadau") String idGiaiDau);
}
