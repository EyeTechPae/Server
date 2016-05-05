package demos;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by germangb on 26/04/16.
 */
public class Processing {

    private LinkedList<String> inside = new LinkedList<String>();
    private LinkedList<String> outside= new LinkedList<String>();

    public Processing () throws InterruptedException, IOException {
        System.out.println("[PROCESSING] Connecting to master server...");
        Socket socket = new Socket("46.101.132.172", 3332);
        System.out.println("[PROCESSING] Connected to master server");

        // wait until first byte
        //socket.getInputStream().read();

        for (int i = 0; i < Plates.Plates.length; ++i)
            outside.add(Plates.Plates[i]);

        Random rand = new Random(42);
        while (true) {
            if ((inside.isEmpty() || (rand.nextInt()&1) == 0) && inside.size() < 43) {
                String plate = outside.poll();
                System.out.println(plate+" IN!");
                inside.add(plate);
                // send to server
                socket.getOutputStream().write(0);
                socket.getOutputStream().write(plate.getBytes());
            } else {
                String plate = inside.poll();
                System.out.println(plate+" OUT!");
                outside.add(plate);

                // send to server
                socket.getOutputStream().write(1);
                socket.getOutputStream().write(plate.getBytes());
            }
            Thread.sleep(2000 + (long)(1000 * Math.random()));
        }
    }

    public static void main (String[] args) {
        try {
            new Processing();
        } catch (Exception e) {
            System.err.println("[PROCESSING] ERROR!");
            e.printStackTrace(System.err);
        }
    }
}
