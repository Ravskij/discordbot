import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReceived extends ListenerAdapter {
    GuildCasino guildCasino = new GuildCasino();
    JoinToVoiceChannel joinToVoiceChannel = new JoinToVoiceChannel();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        if (msg.getContentRaw().equals("!ping")) {
            long time = System.currentTimeMillis();
            event.getChannel().sendMessage("Pong!") /* => RestAction<Message> */
                    .queue(response /* => Message */ -> response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue());
        }
        if (msg.getContentRaw().equals("!гэй")) {
            if (event.getMessage().getAuthor().getName().equals("Serpsh")) {
                event.getChannel().sendMessage("<@!" + event.getMessage().getAuthor().getId() + "> гэй на 100%").queue();
            } else {
                event.getChannel().sendMessage("<@!" + event.getMessage().getAuthor().getId() + "> гэй на " + (int) (Math.random() * 101.0D + 0.0D) + "%").queue();
            }
        }
        if (msg.getContentRaw().startsWith("!дуфи") || msg.getContentRaw().startsWith("!leab")) {
            event.getChannel().sendMessage("Я всего лишь машина. Только имитация жизни. Робот сочинит симфонию? Робот превратит кусок холста в шедевр искусства?").queue();
        }
        if (msg.getContentRaw().startsWith("!join") && event.getMessage().getAuthor().getName().equals("discoyetti")) {
            joinToVoiceChannel.Join(event);
        }
        if (msg.getContentRaw().startsWith("!leave")) {
            event.getGuild().getAudioManager().closeAudioConnection();
        }
        if (msg.getContentRaw().startsWith("!хелп") || msg.getContentRaw().startsWith("!help")) {
            event.getChannel().sendMessage("Привет, я бот **Дуфи**. Вот несколько команд для взаимодействия со мной.\n" +
                    "> **!дуфи** - узнай обо мне подробнее\n" +
                    "> **!гэй** - оцени свою приверженность к pride\n" +
                    "> **!ping** - для умных\n" +
                    "> **!join** <название голосового канала> - если скучно одному\n" +
                    "> **!leave** - проваливай").queue();
        }
        if (msg.getContentRaw().startsWith("!add")) {
            guildCasino.Add(event);
        }
        if (msg.getContentRaw().startsWith("!flip")) {
            guildCasino.Flip(event);
        }
        if (msg.getContentRaw().startsWith("!score")) {
            guildCasino.Print(event);
        }
        if (msg.getContentRaw().startsWith("!кредит")) {
            guildCasino.Credit(event);
        }
    }

}
