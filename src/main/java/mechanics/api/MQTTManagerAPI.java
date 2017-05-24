package mechanics.api;

import mechanics.system.constant.AssembledEquipments;
import mechanics.system.mqtt.MQTTConnector;
import mechanics.system.mqtt.PayloadGPV;
import mechanics.system.mqtt.PayloadVPV;
import ru.yandex.qatools.allure.annotations.Step;


/**
 * Created by user on 21.04.2017.
 */
public class MQTTManagerAPI extends MQTTConnector {
    @Step("Sending payload to VPV equipment")
    public void triggerVPVAbnormalVibration(){
        String topic = AssembledEquipments.equipmentVpvData;
        String payload = PayloadVPV.newBuilder().setAbnormalVibrationMain(666).setId(AssembledEquipments.equipmentVpv).initialize();
        mqttPublish(topic, payload);
    }

    @Step("Sending payload to VPV equipment")
    public void triggerVPVAbort(){
        String topic = AssembledEquipments.equipmentVpvData;
        String payload = PayloadVPV.newBuilder().setMaxDistanceMain(666).setId(AssembledEquipments.equipmentVpv).initialize();
        mqttPublish(topic, payload);
    }


    public void triggerVPVDisconected(){
        String topic = "";
        String payload = "{\"expiration\":"+(System.currentTimeMillis()/1000-600)+"}";
        mqttPublish(topic, payload);
    }

    public void triggerVPVConnected(){
        String topic = "";
        String payload = "{\"expiration\":"+(System.currentTimeMillis()/1000+120)+"}";
        mqttPublish(topic, payload);
    }
    @Step("Sending payload to VPV equipment")
    public void triggerVPVAlarmCount(){
        String topic =  AssembledEquipments.equipmentVpvData;
        String payload =PayloadVPV.newBuilder().setId(AssembledEquipments.equipmentVpv).setMaxDistanceMain(80).initialize();
        String payloadNormal = PayloadVPV.newBuilder().setId(AssembledEquipments.equipmentVpv).initialize();        mqttPublish(topic, payloadNormal);
        mqttPublish(topic, payloadNormal);
        mqttPublish(topic, payload);
        mqttPublish(topic, payloadNormal);
    }
    @Step("Sending payload to VPV equipment")
    public void triggerVPVAbortCount(){
        String topic = AssembledEquipments.equipmentVpvData;
        String payload =PayloadVPV.newBuilder().setId(AssembledEquipments.equipmentVpv).setMaxDistanceMain(170).initialize();
        String payloadNormal = PayloadVPV.newBuilder().setId(AssembledEquipments.equipmentVpv).initialize();
        mqttPublish(topic, payloadNormal);
        mqttPublish(topic, payload);
        mqttPublish(topic, payloadNormal);
    }

    public void triggerGPVLong(){
        String topic = AssembledEquipments.equipmentGpvData;
        String payload = PayloadGPV.newBuilder().setDistance(666).setMultiDataStreamId(AssembledEquipments.equipmentGpvMultiDatastreamId).setAlarm(true).initialize();
        for (int i = 0; i<5; i++){
            mqttPublish(topic, payload);
        }
    }

    public void triggerGPV(){
        String topic = AssembledEquipments.equipmentGpvData;
        String payload = PayloadGPV.newBuilder().setDistance(666).setMultiDataStreamId(AssembledEquipments.equipmentGpvMultiDatastreamId).setAlarm(true).initialize();
        mqttPublish(topic, payload);
    }

    public void readVPVD11(){
        String topic = AssembledEquipments.equipmentVpvData;
        mqttSubscribe(59000, topic);

    }

    public void readGPV(){
        String topic = AssembledEquipments.equipmentGpvData;
        mqttSubscribe(59000, topic);
    }
}
