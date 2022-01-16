package il.cshaifasweng.OCSFMediatorExample.server;

import antlr.debug.MessageAdapter;
import il.cshaifasweng.OCSFMediatorExample.entities.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MessagesToManagerController {

    public static List<MessageToManager> getUnreadMessagesOfManager(User user) {
        CriteriaBuilder builder = Main.session.getCriteriaBuilder();
        CriteriaQuery<MessageToManager> query = builder.createQuery(MessageToManager.class);
        Root<MessageToManager> root = query.from(MessageToManager.class);
        query.select(root);
        query.where(builder.equal(root.get("to"), user.getFullName()), builder.equal(root.get("read"), false));
        query.orderBy(builder.asc(root.get("date")));
        return Main.session.createQuery(query).getResultList();
    }
}
