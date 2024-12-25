package com.immomo.momo;

public final class ProtocolType {

    public static final byte AUDIO_REALTIME_FRAME_UP = 16;
    public static final byte BIZ_MSG_ACK = 20;
    public static final byte BIZ_RELIABLE_MSG_ACK = 40;
    public static final char CLIENT_CHOOSE_ANSWER = '\n';
    public static final char CLIENT_LINK = 11;
    public static final char CLIENT_SYNC_EVENT = '\t';
    public static final char CLIENT_UPLOAD_CHECK_LOG = '\r';
    public static final byte COMMON_ACTIVITY_BAR_TYPE = 38;
    public static final byte COMMON_FRAME_UP = 17;
    public static final char ENTER_GROUP = '\f';
    public static final byte FAMILY_BILI = 15;
    public static final char GROUPS = 5;
    public static final char KICK = '\b';
    public static final char MESSAGE_ACK = 4;
    public static final char MESSAGE_UP = 3;
    public static final char PING = 1;
    public static final char PONG = 6;
    public static final char RE_CONNECT = 7;
    public static final byte RICH_CLUB_BILI = 18;
    public static final char SAUTH = 2;
    public static final char SOH = 2;
    public static final byte VOTE_CHANGE_STAR = 39;

    private ProtocolType() {
        throw new UnsupportedOperationException();
    }
}
