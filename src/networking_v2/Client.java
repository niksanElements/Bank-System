package networking_v2;

import networking_v2.messages.Message;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.SocketFactory;

/**
 * Client class. Initiates connection requests and creates and manages
 * connections client-side.
 * <p>
 * TO DO: fields and methods visibility, general improvements.
 *
 * @author iliyan-kostov <iliyan.kostov.gml@gmail.com>
 */
public class Client implements MessageHandler {

    public final SocketFactory socketFactory;
    public String hostName;
    public int port;
    public final MessageHandler messageHandler;
    public final ExecutorService executor;
    public Clientside clientside;

    public Client(SocketFactory socketFactory, String hostName, int port, MessageHandler messageHandler) {
        this.socketFactory = socketFactory;
        this.hostName = hostName;
        this.port = port;
        this.messageHandler = messageHandler;
        this.executor = Executors.newCachedThreadPool();
        this.clientside = null;
    }

    public synchronized boolean connect() {
        if (this.clientside == null) {
            try {
                Socket socket = this.socketFactory.createSocket(this.hostName, this.port);
                this.clientside = new Clientside(this, socket);
                this.clientside.start();
                return true;
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public synchronized Message handle(Message message) {
        return this.messageHandler.handle(message);
    }

    public synchronized boolean send(Message message) throws IOException {
        if (this.clientside != null) {
            if (this.clientside.canSend()) {
                return this.clientside.send(message);
            }
        }
        return false;
    }

    public synchronized boolean canSend() {
        if (this.clientside != null) {
            return this.clientside.canSend();
        } else {
            return false;
        }
    }
}
