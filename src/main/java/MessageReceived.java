import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.Objects;

public class MessageReceived extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        Guild guild = event.getGuild();
        if (msg.getContentRaw().equals("!ping")) {
            long time = System.currentTimeMillis();
            channel.sendMessage("Pong!") /* => RestAction<Message> */
                    .queue(response /* => Message */ -> response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue());
        }
        if (msg.getContentRaw().equals("!гэй")) {
            channel = event.getChannel();
            if (event.getMessage().getAuthor().getName().equals("Serpsh")) {
                channel.sendMessage("<@!" + event.getMessage().getAuthor().getId() + "> гэй на 100%").queue();
            } else {
                channel.sendMessage("<@!" + event.getMessage().getAuthor().getId() + "> гэй на " + (int) (Math.random() * 101.0D + 0.0D) + "%").queue();
            }
        }
        if (msg.getContentRaw().startsWith("!дуфи") || msg.getContentRaw().startsWith("!leab")) {
            channel = event.getChannel();
            channel.sendMessage("Я всего лишь машина. Только имитация жизни. Робот сочинит симфонию? Робот превратит кусок холста в шедевр искусства?").queue();
        }
        if (msg.getContentRaw().startsWith("join") && event.getMessage().getAuthor().getName().equals("discoyetti")) {
            channel = event.getChannel();
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
        if (msg.getContentRaw().startsWith("leave")) {
            event.getGuild().getAudioManager().closeAudioConnection();
        }
        if (msg.getContentRaw().startsWith("!хелп") || msg.getContentRaw().startsWith("!help")) {
            channel = event.getChannel();
            channel.sendMessage("Привет, я бот **Дуфи**. Вот несколько команд для взаимодействия со мной.\n" +
                    "> **!дуфи** - узнать обо мне подробнее\n" +
                    "> **!гэй** - оценить свою принадлежность к pride\n" +
                    "> **!ping** - для умных\n" +
                    "> **join** <название голосового канала> - если скучно одному\n" +
                    "> **leave** - проваливай").queue();
        }
    }

}
