import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        JDABuilder.createLight("TOKEN", GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new Main())
                .setActivity(Activity.playing("Диплом"))
                .build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
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
                channel.sendMessage("<@!" + event.getMessage().getAuthor().getId() + "> гэй на " + (int)(Math.random() * 101.0D + 0.0D) + "%").queue();
            }
        }
        if (msg.getContentRaw().equals("я")) {
            channel = event.getChannel();
            channel.sendMessage("Ты всего лишь машина. Только имитация жизни. Робот сочинит симфонию? Робот превратит кусок холста в шедевр искусства?").queue();
        }
    }
}
