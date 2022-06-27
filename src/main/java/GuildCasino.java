import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Objects;

public class GuildCasino {
    GuildBank guildBank = new GuildBank();
    String[][] nameAndScore = guildBank.getNameAndScore();

    public void Print(MessageReceivedEvent event) {
        int recentUser = RecentUserPosition(event);
        if (recentUser < 10) {
            event.getChannel().sendMessage("<@!" + event.getMessage().getAuthor().getId() + "> у тебя "  + nameAndScore[recentUser][1] + "₸").queue();
        } else {
            event.getChannel().sendMessage("<@!" + event.getMessage().getAuthor().getId() + "> у тебя нет ₸").queue();
        }
    }

    // если рандомное число от 0 до 39 то +100, иначе -100
    public void Flip(MessageReceivedEvent event) {
        int userPosition = RecentUserPosition(event);
        if (userPosition < 10) {
            String userScore = nameAndScore[userPosition][1];
            int scoreInt = Integer.parseInt(userScore);
            int randomNumber = (int)((Math.random() * 100) + 1);
            if (randomNumber < 40 && !Objects.equals(nameAndScore[userPosition][1], "0")) {
                scoreInt = scoreInt + 100;
                nameAndScore[userPosition][1] = Integer.toString(scoreInt);
                guildBank.setNameAndScore(nameAndScore);
                event.getChannel().sendMessage("Выиграл 100₸").queue();
            } else {
                if (scoreInt != 100 && !Objects.equals(nameAndScore[userPosition][1], "0")) {
                    scoreInt = scoreInt - 100;
                    nameAndScore[userPosition][1] = Integer.toString(scoreInt);
                    guildBank.setNameAndScore(nameAndScore);
                    event.getChannel().sendMessage("Проиграл 100₸").queue();
                } else {
                    scoreInt = 0;
                    nameAndScore[userPosition][1] = Integer.toString(scoreInt);
                    guildBank.setNameAndScore(nameAndScore);
                    event.getChannel().sendMessage("Ты все проиграл").queue();
                }
            }
        } else {
            event.getChannel().sendMessage("Тебя нет в списке геев").queue();
        }
    }

    public void Add(MessageReceivedEvent event) {
        int recentUser = RecentUserPosition(event);
        int lastUser = LastUserPosition();
        if (recentUser < 10) {
            event.getChannel().sendMessage("Ты уже есть в гейском списке!").queue();
        } else {
            if (lastUser < 10) {
                nameAndScore[lastUser][0] = event.getMessage().getAuthor().getId();
                nameAndScore[lastUser][1] = "500";
                guildBank.setNameAndScore(nameAndScore);
                event.getChannel().sendMessage("Добавил <@!" + nameAndScore[lastUser][0] + "> в список геев и выдал " + nameAndScore[lastUser][1] + "₸").queue();
            } else {
                event.getChannel().sendMessage("Список геев заполнен").queue();
            }
        }
    }

    // находит последнюю пустую ячейку
    public int LastUserPosition() {
        for (int i = 0; i < 10; i++) {
            if (Objects.isNull(nameAndScore[i][0])) {
                return i; //last user position
            }
        }
        return 10;
    }

    // находит текущее положение человека
    public int RecentUserPosition(MessageReceivedEvent event) {
        String userId = event.getMessage().getAuthor().getId();
        for (int i = 0; i < 10; i++) {
            if (Objects.equals(nameAndScore[i][0], userId)) {
                return i; //user position
            }
        }
        return 10;
    }

    public void Credit(MessageReceivedEvent event) {
        int recentUser = RecentUserPosition(event);
        if (recentUser < 10) {
            nameAndScore[recentUser][1] = "100";
            guildBank.setNameAndScore(nameAndScore);
            event.getChannel().sendMessage("Держи подачку 100₸").queue();
        } else {
            event.getChannel().sendMessage("Тебя нет в списке геев").queue();
        }
    }
}
