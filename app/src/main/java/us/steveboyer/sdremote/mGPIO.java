package us.steveboyer.sdremote;

import android.util.Log;
import com.androidhive.jsonparsing.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by steve on 4/15/15.
 */
public class mGPIO {
    private Thread t;
    public static class mConnection {
        public String host;
        public int port;
        public String username;
        public String password;

        public mConnection(String host) {
            this.host = host;
            this.port = 8000;
            this.username = "webiopi";
            this.password = "raspberry";
        }

        public mConnection(String host, int port, String user, String pass) {
            this.host = host;
            this.port = port;
            this.username = user;
            this.password = pass;
        }
    }

    private mConnection conn;

    public mGPIO(mConnection conn){
        this.conn = conn;

    }

    /**
     * outputSequence sends a sequence of bits to the specified
     * GPIO port with the delay specified. The sequence is specified
     * as a string of 0 and 1 characters. It doesn't validate the
     * string sent. This method works in an asynchronous way
     * starting a new Thread to make the request and ends.
     * <p>
     * If there is any connection exceptions it generates
     * a "ConnectionEvent", this is useful to detect if
     * GPIO is disconnected from the target host.
     * @param macro Name of macro
     */
    public void sendMacro(final String macro) {
        Runnable r = new mRunnable();

        t = new Thread(r);
        t.start();
    }

    public class mRunnable implements Runnable{
        private boolean running = false;

        public void terminate() {
            running = false;
        }

        @Override
        public void run() {
            while (running) {
                try {
                    Thread.sleep((long) 15000);

                    SONParser parser = new JSONParser(conn.username,
                            conn.password, conn.host);
                    String url = "http://"
                            + conn.host + ":" + conn.port +
                            "/macros/"+macro;
                    Log.d("Sent", url);
                    JSONObject obj = parser.postJSONFromURL(url, conn.port);

                    if (obj != null) {
                    }


                } catch (InterruptedException e) {
                    running = false;
                } catch (JSONException e) {

                } catch (Exception e) {

                }


                }

            }

        public void
        }
    }


}


