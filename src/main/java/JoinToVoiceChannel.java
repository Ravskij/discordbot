import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.Objects;

public class JoinToVoiceChannel extends ListenerAdapter {

    public void Join(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        Message msg = event.getMessage();
        Guild guild = event.getGuild();
        String[] joinChannelName = msg.getContentRaw().split(" ");
        int countWordsChannelName = joinChannelName.length;
        if (countWordsChannelName == 2) {
            String notFoundVoiceChannel = guild.getVoiceChannelsByName(joinChannelName[1], true).toString();
            if (Objects.equals(notFoundVoiceChannel, "[]")) {
                channel.sendMessage("АДСУТНИЧАЕ").queue();
            } else {
                VoiceChannel myChannel = guild.getVoiceChannelsByName(joinChannelName[1], true).get(0);
                guild.getAudioManager().openAudioConnection(myChannel);
            }
        } else if (countWordsChannelName == 3) {
            String notFoundVoiceChannel = guild.getVoiceChannelsByName(joinChannelName[1] + " " + joinChannelName[2], true).toString();
            if (Objects.equals(notFoundVoiceChannel, "[]")) {
                channel.sendMessage("АДСУТНИЧАЕ").queue();
            } else {
                VoiceChannel myChannel = guild.getVoiceChannelsByName(joinChannelName[1] + " " + joinChannelName[2], true).get(0);
                guild.getAudioManager().openAudioConnection(myChannel);
            }
        } else {
            channel.sendMessage("Это где?").queue();
        }
    }
    
}
