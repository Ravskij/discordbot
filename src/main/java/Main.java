import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {

    public static void main(String[] args) throws LoginException {
        JDA jdaBuilder = JDABuilder.createLight("\"" + System.getenv("TOKEN") + "\"", GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new Main())
                .setActivity(Activity.playing("Диплом"))
                .build();
        UserJoinModule userJoinModule = new UserJoinModule();
        jdaBuilder.addEventListener(userJoinModule);
        MessageReceived messageReceived = new MessageReceived();
        jdaBuilder.addEventListener(messageReceived);
        UserLeaveModule userLeaveModule = new UserLeaveModule();
        jdaBuilder.addEventListener(userLeaveModule);
        
    }

}
