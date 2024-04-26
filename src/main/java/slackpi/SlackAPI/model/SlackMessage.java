package slackpi.SlackAPI.model;

public class SlackMessage {
	
    private String text;

    // Default constructor
    public SlackMessage() {
    }

    // Constructor with text parameter
    public SlackMessage(String text) {
        this.text = text;
    }

    // Getter and setter for text
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
