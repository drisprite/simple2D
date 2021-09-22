package simple2D.server;

public class Client {
    private World world = null;
    
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;
    
    private int UID =(int)(Math.random() * 10000);
    
    {
        new Thread(
            this.run = function() {
                socket = new Socket("127.0.0.1", 9000);
                System.out.println("Finding Server...");
                
                input = new DataInputStream(socket.getInputStream());
                output = new DataOutputStream(socket.getOutputStream());
                
                output.writeInt(UID);
            }
        ).start();
    }
}