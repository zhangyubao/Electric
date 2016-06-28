package com.lutai.electric.apiService;

import com.lutai.electric.entities.AlarmRecord;
import com.lutai.electric.entities.Banner;
import com.lutai.electric.entities.CommonDevice;
import com.lutai.electric.entities.Device;
import com.lutai.electric.entities.DeviceInfo;
import com.lutai.electric.entities.DeviceParam;
import com.lutai.electric.entities.Result;
import com.lutai.electric.entities.Voltage;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 网络接口类,定义所有的数据获取接口
 */
public interface InternetService {

    /**
     * 获取banner广告图片
     *
     * @return
     */
    @FormUrlEncoded
    @POST("action/image/adv/list")
    Call<Banner> getBanners(@Field("token") String token);

    /**
     * 获取首页报警信息列表
     *
     * @param token
     * @param createTim 消息的时间戳
     * @param getType   1(获取更多);2(获取最新)
     * @return
     */
    @FormUrlEncoded
    @POST("action/alarm/list")
    Call<AlarmRecord> getAlarmRecord(@Field("token") String token, @Field("createTim") String createTim, @Field("getType") int getType);

    /**
     * 获取常用电压类型
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("action/device/elecType/list")
    Call<Voltage> getCommonVoltage(@Field("token") String token);

    /**
     * 获取所有常用设备
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("action/device/default/list")
    Call<CommonDevice> getAllDevices(@Field("token") String token);

    /**
     * 获取设备列表
     *
     * @param token
     * @param deviceId
     * @return
     */
    @FormUrlEncoded
    @POST("action/device/tree")
    Call<Device> getDeviceList(@Field("token") String token, @Field("deviceId") long deviceId);

    /**
     * 获取设备详情
     *
     * @param token
     * @param deviceId
     * @return
     */
    @FormUrlEncoded
    @POST("action/device/currInfo/get")
    Call<DeviceInfo> getDeviceInfo(@Field("token") String token, @Field("deviceId") long deviceId);

    /**
     * 获取报警记录
     *
     * @param token
     * @param createTim
     * @param deviceId  不能为0
     * @param getType
     * @return
     */
    @FormUrlEncoded
    @POST("action/device/alarm/list")
    Call<AlarmRecord> getAlarmRecord(@Field("token") String token, @Field("createTim") String createTim, @Field("deviceId") long deviceId, @Field("getType") int getType);

    /**
     * 获取设备参数
     *
     * @param token
     * @param deviceId
     * @return
     */
    @FormUrlEncoded
    @POST("action/device/param/list")
    Call<DeviceParam> getDeviceParam(@Field("token") String token, @Field("deviceId") long deviceId);

    /**
     * 添加设备常用参数
     *
     * @param token
     * @param deviceId
     * @param paramId
     * @return
     */
    @FormUrlEncoded
    @POST("action/device/param/add")
    Call<Result> AddCommonParams(@Field("token") String token, @Field("deviceId") long deviceId, @Field("paramId") long paramId);

    /**
     * 删除设备常用参数
     *
     * @param token
     * @param deviceId
     * @param paramId
     * @return
     */
    @FormUrlEncoded
    @POST("action/device/param/del")
    Call<Result> deleteCommonParams(@Field("token") String token, @Field("deviceId") long deviceId, @Field("paramId") long paramId);

    /**
     * 设置为常用设备
     *
     * @param token
     * @param deviceId
     * @return
     */
    @FormUrlEncoded
    @POST("action/device/addDefault")
    Call<Result> setCommonDevice(@Field("token") String token, @Field("deviceId") long deviceId);
}

