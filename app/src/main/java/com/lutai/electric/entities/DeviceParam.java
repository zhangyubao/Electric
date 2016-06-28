package com.lutai.electric.entities;

import java.util.List;

/**
 * Created by zhangYB on 2016/6/24.
 */
public class DeviceParam {

    /**
     * status : 1
     * size : 52
     * data : [{"isDefault":1,"paramDescs":"powerFactorA","paramId":1,"paramName":"A相功率因数"},{"isDefault":1,"paramDescs":"powerFactorB","paramId":2,"paramName":"B向功率因数"},{"isDefault":1,"paramDescs":"powerFactorC","paramId":3,"paramName":"C向功率因数"},{"isDefault":1,"paramDescs":"powerFactor","paramId":4,"paramName":"合相功率因数"},{"isDefault":0,"paramDescs":"activePowerA","paramId":5,"paramName":"A相有功功率"},{"isDefault":0,"paramDescs":"activePowerB","paramId":6,"paramName":"B向有功功率"},{"isDefault":0,"paramDescs":"activePowerC","paramId":7,"paramName":"C向有功功率"},{"isDefault":0,"paramDescs":"activePower","paramId":8,"paramName":"合相有功功率"},{"isDefault":0,"paramDescs":"reactivePowerA","paramId":9,"paramName":"A相无功功率"},{"isDefault":0,"paramDescs":"reactivePowerB","paramId":10,"paramName":"B相无功功率"},{"isDefault":0,"paramDescs":"reactivePowerC","paramId":11,"paramName":"C相无功功率"},{"isDefault":0,"paramDescs":"reactivePower","paramId":12,"paramName":"合相无功功率"},{"isDefault":0,"paramDescs":"apparentPowerA","paramId":13,"paramName":"A相视在功率"},{"isDefault":0,"paramDescs":"apparentPowerB","paramId":14,"paramName":"B相视在功率"},{"isDefault":0,"paramDescs":"apparentPowerC","paramId":15,"paramName":"C相视在功率"},{"isDefault":0,"paramDescs":"apparentPower","paramId":16,"paramName":"合相视在功率"},{"isDefault":0,"paramDescs":"activeEnergyA","paramId":17,"paramName":"A相有功电能"},{"isDefault":0,"paramDescs":"activeEnergyB","paramId":18,"paramName":"B相有功电能"},{"isDefault":0,"paramDescs":"activeEnergyC","paramId":19,"paramName":"C相有功电能"},{"isDefault":0,"paramDescs":"activeEnergy","paramId":20,"paramName":"合相有功电能"},{"isDefault":0,"paramDescs":"reactiveEnergyA","paramId":21,"paramName":"A相无功电能"},{"isDefault":0,"paramDescs":"reactiveEnergyB","paramId":22,"paramName":"B相无功电能"},{"isDefault":0,"paramDescs":"reactiveEnergyC","paramId":23,"paramName":"C相无功电能"},{"isDefault":0,"paramDescs":"reactiveEnergy","paramId":24,"paramName":"合相无功电能"},{"isDefault":0,"paramDescs":"ua","paramId":25,"paramName":"A相电压"},{"isDefault":0,"paramDescs":"ub","paramId":26,"paramName":"B相电压"},{"isDefault":0,"paramDescs":"uc","paramId":27,"paramName":"C相电压"},{"isDefault":0,"paramDescs":"ia","paramId":28,"paramName":"A相电流"},{"isDefault":0,"paramDescs":"ib","paramId":29,"paramName":"B电流"},{"isDefault":0,"paramDescs":"ic","paramId":30,"paramName":"C相电流"},{"isDefault":0,"paramDescs":"uab","paramId":31,"paramName":"AB线电压"},{"isDefault":0,"paramDescs":"ubc","paramId":32,"paramName":"BC线电压"},{"isDefault":0,"paramDescs":"uca","paramId":33,"paramName":"CA线电压"},{"isDefault":0,"paramDescs":"uXieboA","paramId":34,"paramName":"A相电压谐波含量"},{"isDefault":0,"paramDescs":"uXieboB","paramId":35,"paramName":"B相电压谐波含量"},{"isDefault":0,"paramDescs":"uXieboC","paramId":36,"paramName":"C相电压谐波含量"},{"isDefault":0,"paramDescs":"iXieboA","paramId":37,"paramName":"A相电流谐波含量"},{"isDefault":0,"paramDescs":"iXieboB","paramId":38,"paramName":"B相电流谐波含量"},{"isDefault":0,"paramDescs":"iXieboC","paramId":39,"paramName":"C相电流谐波含量"},{"isDefault":0,"paramDescs":"uaAngle","paramId":40,"paramName":"Ua的角度"},{"isDefault":0,"paramDescs":"ubAngle","paramId":41,"paramName":"Ub的角度"},{"isDefault":0,"paramDescs":"ucAngle","paramId":42,"paramName":"Uc的角度"},{"isDefault":0,"paramDescs":"iaAngle","paramId":43,"paramName":"Ia的角度"},{"isDefault":0,"paramDescs":"ibAngle","paramId":44,"paramName":"Ib的角度"},{"isDefault":0,"paramDescs":"icAngle","paramId":45,"paramName":"Ic的角度"},{"isDefault":0,"paramDescs":"count","paramId":46,"paramName":"电容器数量"},{"isDefault":0,"paramDescs":"总容量","paramId":47,"paramName":"totalCapacity"},{"isDefault":0,"paramDescs":"usedCapacity","paramId":48,"paramName":"已投入容量"},{"isDefault":0,"paramDescs":"temp","paramId":49,"paramName":"柜内温度"},{"isDefault":0,"paramDescs":"runningModel","paramId":50,"paramName":"运行方式"},{"isDefault":0,"paramDescs":"alarm","paramId":51,"paramName":"告警"},{"isDefault":0,"paramDescs":"commSuccessRate","paramId":52,"paramName":"通讯成功率"}]
     */

    private int status;
    private int size;
    /**
     * isDefault : 1
     * paramDescs : powerFactorA
     * paramId : 1
     * paramName : A相功率因数
     */

    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int isDefault;
        private String paramDescs;
        private int paramId;
        private String paramName;

        public int getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }

        public String getParamDescs() {
            return paramDescs;
        }

        public void setParamDescs(String paramDescs) {
            this.paramDescs = paramDescs;
        }

        public int getParamId() {
            return paramId;
        }

        public void setParamId(int paramId) {
            this.paramId = paramId;
        }

        public String getParamName() {
            return paramName;
        }

        public void setParamName(String paramName) {
            this.paramName = paramName;
        }
    }
}
