package codeu.chat.client.maingui;

public class MessagePanelItem {
    public String message;
    public String file;

    public MessagePanelItem(String message, String file) {
        this.message = message;
        this.file = file;
    }

    @Override
    public String toString() {
        return message;
    }
}
