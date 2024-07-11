package eatwell.app.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TwillioModel {
    private String MediaContentType0;
    private String SmsMessageSid;
    private String MessageSid;
    private String SmsSid;
    private String SmsStatus;
    private String ProfileName;
    private String AccountSid;
    private String MessageType;
    private String WaId;
    private String Body;
    private String To;
    private String From;
    private String MediaUrl0;
    private String ApiVersion;
    private int NumMedia;
    private int NumSegments;
    private int ReferralNumMedia;

    @Override
    public String toString() {
        return "TwillioModel [MediaContentType0=" + MediaContentType0 + ", SmsMessageSid=" + SmsMessageSid
                + ", MessageSid=" + MessageSid + ", SmsSid=" + SmsSid + ", SmsStatus=" + SmsStatus + ", ProfileName="
                + ProfileName + ", AccountSid=" + AccountSid + ", MessageType=" + MessageType + ", WaId=" + WaId
                + ", Body=" + Body + ", To=" + To + ", From=" + From + ", MediaUrl0=" + MediaUrl0 + ", ApiVersion="
                + ApiVersion + ", NumMedia=" + NumMedia + ", NumSegments=" + NumSegments + ", ReferralNumMedia="
                + ReferralNumMedia + "]";
    }
}
