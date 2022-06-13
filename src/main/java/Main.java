import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        JDA jdaBuilder = JDABuilder.createLight("OTg0ODYyMTk3NDMzODkyODg1.GR-A4R._YFvttG2wHn8SwGS1rHEj8dt-kNsqIUzjFS0z4", GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new Main())
                .setActivity(Activity.playing("Диплом"))
                .build();
        UserJoinModule userJoinModule = new UserJoinModule();
        jdaBuilder.addEventListener(userJoinModule);
        MessageReceived messageReceived = new MessageReceived();
        jdaBuilder.addEventListener(messageReceived);
    }
}
