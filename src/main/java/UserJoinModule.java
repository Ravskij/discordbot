import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.List;

public class UserJoinModule extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Guild guild = event.getGuild();
        User user = event.getUser();
        List<TextChannel> channels = guild.getTextChannelsByName("общий", true);
        for (TextChannel channel : channels) {
            channel.sendMessage("Свежий кабанчик - <@!" + user.getId() + ">").queue();
        }
    }

}