package pro.sky.telegrambot.scheduled;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.service.NotificationTaskService;

import java.util.Collection;

@Service
public class ScheduledTask {
    @Autowired
    NotificationTaskService notificationTaskService;
    @Autowired
    private TelegramBot telegramBot;

    @Scheduled(cron = "0 0/1 * * * *") //cron expression для запуска метода каждую секунду —  0 0/1 * * * *
    private void notification() {
        //создаем список подходящих напоминаний
        Collection<NotificationTask> list = notificationTaskService.getCurrentNotification();
        list.forEach(task -> {
            SendMessage message = new SendMessage(task.getChatId(), "НАПОМИНАНИЕ: " + task.getText());
            telegramBot.execute(message);
        });
    }
}
