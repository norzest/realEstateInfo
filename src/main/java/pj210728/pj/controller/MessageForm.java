package pj210728.pj.controller;

public class MessageForm {
    private String message = "";
    private String href = "";

    public MessageForm() {}

    public MessageForm(String message, String href) {
        this.message = message;
        this.href = href;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
