package tdtu.final_mobile.home.notification;

public class Notification {
    String notificationTitle;
    String notificationContent;

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public Notification(String notificationTitle, String notificationContent) {
        this.notificationTitle = notificationTitle;
        this.notificationContent = notificationContent;
    }
}
