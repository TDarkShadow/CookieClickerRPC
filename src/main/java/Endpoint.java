import com.google.gson.Gson;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/update")
public class Endpoint
{
    @OnOpen
    public void onOpen(Session session) {
        Main.getLogger().info("Opened a connection.");
    }


    @OnClose
    public void onClose(Session session) {
        Main.getLogger().info("Closed connection.");
    }

    @OnMessage
    public void onMessage(String text) {
        Main.getLogger().debug("message:" + text);

        Gson gson = new Gson();
        CookieData c = gson.fromJson(text, CookieData.class);

        Main.updateRichPresence(c.getCookies(), c.getCPS(), c.getPrestigeLevel());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // TODO: Do error handling here
    }
}