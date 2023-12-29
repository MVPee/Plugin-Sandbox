package be.mvpee.Command;

public enum CustomCommand {
    HELP("Help Command", "Usage: /help", "This beautiful command."),
    TOOLS("Tools Menu", "Usage: /tools", "Tools menu for everyone."),
    CONTACT("Contact Command", "Usage: /contact", "link for contact me."),
    PING("Ping Command", "Usage: /ping", "Display ping of the player.");

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
