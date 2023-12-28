package be.mvpee.Command;

public enum CustomCommand {
    HELP("Help Menu", "Usage: /help", "This beautiful menu."),
    TOOLS("Tools Menu", "Usage: /tools", "Tools menu for everyone."),
    CONTACT("Contact Menu", "Usage: /contact", "link for contact me.");

    private final String title;
    private final String usage;
    private final String description;

    CustomCommand(String title, String usage, String description) {
        this.title = title;
        this.usage = usage;
        this.description = description;
    }

    public String getTitle(){ return title; }
    public String getUsage(){ return usage; }
    public String getDescription(){ return description; }
}
