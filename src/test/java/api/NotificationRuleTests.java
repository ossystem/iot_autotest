package api;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import system.email.CheckingMails;

import static system.constant.EmailINBOX.*;

/**
 * Created by user on 20.04.2017.
 */
@Listeners(ListenerAPI.class)
public class NotificationRuleTests {

    @AfterClass
    public void afterTest(){
        RequestManagerAPI requestManagerAPI = new RequestManagerAPI();
       requestManagerAPI.notificationListDeleteAll();
    }

    @Test
    public void abnormalVibrationVPV(){
        MQTTManagerAPI mqttManagerAPI = new MQTTManagerAPI();
        RequestManagerAPI requestManagerAPI = new RequestManagerAPI();
        requestManagerAPI.notificationRuleCreate(requestManagerAPI.notificationRuleCreateAbnormalVibrationVPVPumpD11());
        requestManagerAPI.checkNotificationRuleIsCreated();
        requestManagerAPI.checkNotificationRuleNotTriggered();
        mqttManagerAPI.triggerVPVD11AbnormalVibration();
        requestManagerAPI.checkNotificationRuleTriggered();
        CheckingMails checkingMails = new CheckingMails();
        checkingMails.check(SubjectNotificationRuleAbnormalVibrationsVPV.getMessage());
        requestManagerAPI.notificationRuleDelete();
    }

    @Test
    public void everyWarningVPVAbnormalVibration(){
        MQTTManagerAPI mqttManagerAPI = new MQTTManagerAPI();
        RequestManagerAPI requestManagerAPI = new RequestManagerAPI();
        requestManagerAPI.notificationRuleCreate(requestManagerAPI.notificationRuleCreateEveryWarningVPV());
        requestManagerAPI.checkNotificationRuleIsCreated();
        requestManagerAPI.checkNotificationRuleNotTriggered();
        mqttManagerAPI.triggerVPVD11AbnormalVibration();
        requestManagerAPI.checkNotificationRuleTriggered();
        CheckingMails checkingMails = new CheckingMails();
        checkingMails.check(SubjectNotificationRuleEveryWarning.getMessage());
        requestManagerAPI.notificationRuleDelete();
    }

    @Test
    public void alarmCountVPV(){
        MQTTManagerAPI mqttManagerAPI = new MQTTManagerAPI();
        RequestManagerAPI requestManagerAPI = new RequestManagerAPI();
        requestManagerAPI.notificationRuleCreate(requestManagerAPI.notificationRuleCreateAlarmCountVPV());
        requestManagerAPI.checkNotificationRuleIsCreated();
        requestManagerAPI.checkNotificationRuleNotTriggered();
        mqttManagerAPI.triggerVPVAlarmCount();
        requestManagerAPI.checkNotificationRuleTriggeredLong();
        CheckingMails checkingMails = new CheckingMails();
        checkingMails.check(SubjectNotificationRuleNumberOfAlarms.getMessage());
        requestManagerAPI.notificationRuleDelete();

    }

    @Test
    public void abortCountVPV(){
        MQTTManagerAPI mqttManagerAPI = new MQTTManagerAPI();
        RequestManagerAPI requestManagerAPI = new RequestManagerAPI();
        requestManagerAPI.notificationRuleCreate(requestManagerAPI.notificationRuleCreateAbortsCountVPV());
        requestManagerAPI.checkNotificationRuleIsCreated();
        requestManagerAPI.checkNotificationRuleNotTriggered();
        mqttManagerAPI.triggerVPVAbortCount();
        requestManagerAPI.checkNotificationRuleTriggeredLong();
        CheckingMails checkingMails = new CheckingMails();
        checkingMails.check(SubjectNotificationRuleNumberOfAborts.getMessage());
        requestManagerAPI.notificationRuleDelete();

    }


    //    @Test //не работает по тому что нет возможности симулировать GPV
    public void alarmCountGPV(){
        MQTTManagerAPI mqttManagerAPI = new MQTTManagerAPI();
        RequestManagerAPI requestManagerAPI = new RequestManagerAPI();
        requestManagerAPI.notificationRuleCreate(requestManagerAPI.notificationRuleCreateAlarmCountGPV());
        requestManagerAPI.checkNotificationRuleIsCreated();
        requestManagerAPI.checkNotificationRuleNotTriggered();
//        mqttManagerAPI.trigger
        requestManagerAPI.checkNotificationRuleTriggered();
        requestManagerAPI.notificationRuleDelete();

    }

    //    @Test//не работает принципиально
    public void disconnectedEquipmentVPV(){
        MQTTManagerAPI mqttManagerAPI = new MQTTManagerAPI();
        RequestManagerAPI requestManagerAPI = new RequestManagerAPI();
        requestManagerAPI.equipmentChangeState(requestManagerAPI.equipmentConnectVPV());
        requestManagerAPI.notificationRuleCreate(requestManagerAPI.notificationRuleCreateDisconnectedVPV());
        requestManagerAPI.checkNotificationRuleIsCreated();
        requestManagerAPI.checkNotificationRuleNotTriggered();
        mqttManagerAPI.triggerVPVConnected();
        requestManagerAPI.checkNotificationRuleNotTriggered();
        mqttManagerAPI.triggerVPVDisconected();
        requestManagerAPI.checkNotificationRuleTriggeredLong();
        requestManagerAPI.notificationRuleDelete();

    }

    //    @Test//не работает принципиально
    public void connectedEquipmentVPV(){
        MQTTManagerAPI mqttManagerAPI = new MQTTManagerAPI();
        RequestManagerAPI requestManagerAPI = new RequestManagerAPI();
        requestManagerAPI.equipmentChangeState(requestManagerAPI.equipmentDisconnectVPV());
        requestManagerAPI.notificationRuleCreate(requestManagerAPI.notificationRuleCreateConnectedVPV());
        requestManagerAPI.checkNotificationRuleIsCreated();
        requestManagerAPI.checkNotificationRuleNotTriggered();
        mqttManagerAPI.triggerVPVDisconected();
        requestManagerAPI.checkNotificationRuleNotTriggered();
        mqttManagerAPI.triggerVPVConnected();
        requestManagerAPI.checkNotificationRuleTriggeredLong();
        requestManagerAPI.notificationRuleDelete();

    }


    //    @Test //не работает принципиально
    public void disconnectedEquipmentGPV(){
        RequestManagerAPI requestManagerAPI = new RequestManagerAPI();
        requestManagerAPI.equipmentChangeState(requestManagerAPI.equipmentConnectGPV());
        requestManagerAPI.notificationRuleCreate(requestManagerAPI.notificationRuleCreateDisconnectedGPV());
        requestManagerAPI.checkNotificationRuleIsCreated();
        requestManagerAPI.checkNotificationRuleNotTriggered();


        requestManagerAPI.checkNotificationRuleTriggered();
        requestManagerAPI.notificationRuleDelete();
    }

    //    @Test //не работает принципиально
    public void connectedEquipmentGPV() {
        RequestManagerAPI requestManagerAPI = new RequestManagerAPI();
        requestManagerAPI.equipmentChangeState(requestManagerAPI.equipmentDisconnectGPV());
        requestManagerAPI.notificationRuleCreate(requestManagerAPI.notificationRuleCreateConnectedGPV());
        requestManagerAPI.checkNotificationRuleIsCreated();
        requestManagerAPI.checkNotificationRuleNotTriggered();


        requestManagerAPI.checkNotificationRuleTriggered();
        requestManagerAPI.notificationRuleDelete();
    }


}
