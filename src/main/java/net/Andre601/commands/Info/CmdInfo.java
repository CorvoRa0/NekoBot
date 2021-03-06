package net.Andre601.commands.Info;

import net.Andre601.commands.Command;
import net.Andre601.core.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.Andre601.util.STATIC;

import java.util.concurrent.TimeUnit;

public class CmdInfo implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent e) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent e) {

        EmbedBuilder info = new EmbedBuilder();

        info.setAuthor("Info", STATIC.URL, e.getJDA().getSelfUser().
                getEffectiveAvatarUrl());
        info.setThumbnail(Main.jda.getSelfUser().getEffectiveAvatarUrl());

        info.addField("About the Bot:","Oh hi there!\n" +
                "I'm \\*purr*. A selfmade Bot for the ~Nya Discord.\n" +
                "I was made by <@204232208049766400> with the help of JDA " +
                "and some free time. ^.^", false);

        info.addField("Commands:", "Use `" + STATIC.PREFIX +
                "help` on the Discord, to see all commands", false);

        info.addField("Version:", STATIC.VERSION, true);
        info.addField("Library:", "[JDA](https://github.com/DV8FromTheWorld/JDA)",
                true);
        info.addField("GitHub:", "[NekoBot](https://github.com/Andre601/NekoBot)",
                true);

        e.getAuthor().openPrivateChannel().queue(pm -> {
            pm.sendMessage(info.build()).queue(msg -> {
                e.getTextChannel().sendMessage(String.format(
                        "Check your DMs %s",
                        e.getAuthor().getAsMention()
                )).queue(msg2 -> msg2.delete().completeAfter(10, TimeUnit.SECONDS));
            }, throwable -> {
                e.getTextChannel().sendMessage(String.format(
                        "I can't DM you %s :,(",
                        e.getAuthor().getAsMention()
                )).queue(msg -> msg.delete().completeAfter(10, TimeUnit.SECONDS));
            });
            }
        );
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent e) {

    }

    @Override
    public String help() {
        return null;
    }
}
