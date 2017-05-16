package mechanics.api;

import ru.yandex.qatools.allure.annotations.Step;
import mechanics.system.http.JSONHandler;

import static mechanics.system.constant.EmailINBOX.*;
import static mechanics.system.constant.Things.*;

/**
 * Created by user on 20.04.2017.
 */
public class JSONManagerAPI extends JSONHandler{


    @Step("Creating JSON for Abnormal Vibration rule with Pump D11.")
    public String jsonRuleAbnormalVibrationVPV(){
        return notificationRuleCreateJSON(
                SubjectNotificationRuleAbnormalVibrationsVPV.getMessage(),
                "Pump D11",
                0,
                ThingVPV11.getValue(),
                0,
                "",
                ">=",
                30,
                0,
                1
        ).toString();
    }
    @Step("Creating JSON for Number of Alarms above threshold on Test OSS.")
    public String jsonRuleAlarmCountGPV(){
        return notificationRuleCreateJSON(
                SubjectNotificationRuleNumberOfAlarms.getMessage(),
                "Test OSS",
                3,
                ThingGPVBUC.getValue(),
                "",
                "",
                ">=",
                1,
                1,
                0
        ).toString();
    }
    @Step("Creating JSON for Number of Alarms above threshold on Pump D11.")
    public String jsonRuleAlarmCountVPV(){
        return notificationRuleCreateJSON(
                SubjectNotificationRuleNumberOfAlarms.getMessage(),
                "PUMP D11",
                3,
                ThingVPV11.getValue(),
                "",
                "",
                ">=",
                1,
                1,
                0
        ).toString();
    }
    @Step("Creating JSON for Number of Aborts above threshold on Pump D11.")
    public String jsonRuleAbortsCountVPV(){
        return notificationRuleCreateJSON(
                SubjectNotificationRuleNumberOfAborts.getMessage(),
                "PUMP D11",
                2,
                ThingVPV11.getValue(),
                "",
                "",
                ">=",
                1,
                1,
                0
        ).toString();
    }
    @Step("Creating JSON for Disconnected rule for PUMP D11.")
    public String jsonRuleDisconnectedVPV(){
        return notificationRuleCreateJSON(
                SubjectNotificationRuleDisconnectedVPV.getMessage(),
                "PUMP D11",
                4,
                ThingVPV11.getValue(),
                0,
                "0",
                "",
                0,
                0,
                0
        ).toString();
    }
    @Step("Creating JSON for Disconnected rule for BUC ARTRobot.")
    public String jsonRuleDisconnectedGPV(){
        return notificationRuleCreateJSON(
                SubjectNotificationRuleDisconnectedGPV.getMessage(),
                "BUC ARTRobot",
                4,
                ThingGPVBUC.getValue(),
                0,
                "0",
                "",
                0,
                0,
                0
        ).toString();
    }
    @Step("Creating JSON for Connected rule for PUMP D11.")
    public String jsonRuleConnectedVPV(){
        return notificationRuleCreateJSON(
                SubjectNotificationRuleConnectedVPV.getMessage(),
                "PUMP D11",
                4,
                ThingVPV11.getValue(),
                0,
                "1",
                "",
                0,
                0,
                0
        ).toString();
    }
    @Step("Creating JSON for Connected rule for BUC ARTRobot.")
    public String jsonRuleConnectedGPV(){
        return notificationRuleCreateJSON(
                SubjectNotificationRuleConnectedGPV.getMessage(),
                "BUC ARTRobot",
                4,
                ThingGPVBUC.getValue(),
                0,
                "1",
                "",
                0,
                0,
                0
        ).toString();
    }
    @Step("Creating JSON for Every Warning rule for Pump D11.")
    public String jsonRuleEveryWarningVPV(){
        return notificationRuleCreateJSON(
                SubjectNotificationRuleEveryWarningVPV.getMessage(),
                "PUMP D11",
                8,
                ThingVPV11.getValue(),
                0,
                "",
                ">=",
                0,
                0,
                0
        ).toString();
    }
    @Step("Creating JSON for Every Warning rule for Test OSS.")
    public String jsonRuleEveryWarningGPV(){
        return notificationRuleCreateJSON(
                SubjectNotificationRuleEveryWarningGPV.getMessage(),
                "Test OSS",
                9,
                ThingGPVBUC.getValue(),
                0,
                "",
                ">=",
                0,
                0,
                0
        ).toString();
    }


    public String jsonEquipmentDisconnectVPV(){
        return equipmentOnOff(ThingVPV11.getValue(),false);
    }
    public String jsonEquipmentConnectVPV(){
        return equipmentOnOff(ThingVPV11.getValue(),true);
    }
    public String jsonEquipmentDisconnectGPV(){
        return equipmentOnOff(ThingGPVBUC.getValue(),false);
    }
    public String jsonEquipmentConnectGPV(){
        return equipmentOnOff(ThingGPVBUC.getValue(),true);
    }

    private String reportTemplateId = "Vacuum-Pump-Vibration-Report---Optimized-for-Printing---Weekly";
    private String reportTemplateName = "Vacuum-Pump-Vibration-Report-List---Optimized-for-Printing---Weekly";

    public String JSONReportCreate(){
        return reportCreate(reportTemplateId, ThingVPV11.getValue(), reportTemplateName).toString();
    }

    public String JSONReportSendNow(String id){
        return reportSendNow(id, reportTemplateId, ThingVPV11.getValue(), reportTemplateName).toString();
    }

    public String JSONReportDelete(){
        System.out.println("DELETING OF SKEDLER RULERS IS NOT IMPLEMENTED");
        return null;
    }

    private static final String Name = "Automatically Created Dashboard";
    private static final String Description = "You should not see this one";
    public String jsonDashboardCreateVPVCanvas(){
        int dashboardType = 7;
        String equipmentId = ThingVPVForDashboard.getValue();
        String name = Name;
        String description = Description;
        return dashboardCreate(dashboardType, equipmentId, name, description).toString();
    }

    public String jsonDashboardCreateVPVKibana(){
        int dashboardType = 6;
        String equipmentId = ThingVPVForDashboard.getValue();
        String name = Name;
        String description = Description;
        return dashboardCreate(dashboardType, equipmentId, name, description).toString();
    }
    public String jsonDashboardCreateGPVKibana025(){
        int dashboardType = 4;
        String equipmentId = ThingGPVForDashboard.getValue();
        String name = Name;
        String description = Description;
        return dashboardCreate(dashboardType, equipmentId, name, description).toString();
    }
    public String jsonDashboardCreateGPVKibana0255(){
        int dashboardType = 5;
        String equipmentId = ThingGPVForDashboard.getValue();
        String name = Name;
        String description = Description;
        return dashboardCreate(dashboardType, equipmentId, name, description).toString();
    }
    public String jsonDashboardCreateGPVCanvas025(){
        int dashboardType = 8;
        String equipmentId = ThingGPVForDashboard.getValue();
        String name = Name;
        String description = Description;
        return dashboardCreate(dashboardType, equipmentId, name, description).toString();
    }
    public String jsonDashboardCreateGPVCanvas0255(){
        int dashboardType = 9;
        String equipmentId = ThingGPVForDashboard.getValue();
        String name = Name;
        String description = Description;
        return dashboardCreate(dashboardType, equipmentId, name, description).toString();
    }

}
