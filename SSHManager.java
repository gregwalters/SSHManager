import java.util.Iterator;
import java.util.Properties;
import java.io.*;

class SSHManager {

	String propsFile = System.getProperty("user.home")+"/.sshmanager.properties";
	Properties props = new Properties();

	public static void main(String... args) {
		SSHManager manager = new SSHManager();
		manager.parseArgs(args);
		manager.loadProperties();
	}

	public void parseArgs(String... args) {
		int displayHelp = 0;
		for(int i=0; i < args.length; i++ ) {
			if ( args[i].equals("-p") ) {
				try {
					propsFile = args[i+1];
				} catch (ArrayIndexOutOfBoundsException e) {
					displayHelp = 1;
				}
			} else if ( args[i].equals("-h") ) {
					displayHelp = 2;
			}
		}
		if (displayHelp != 0) {
			displayHelp(displayHelp);
		}
	}

	public void loadProperties() {
		try {
			FileInputStream in = new FileInputStream(propsFile);
			props.load(in);
			in.close();
		} catch (IOException e) {
			System.err.println("Could not load properties file " + propsFile + ".");
			System.exit(1);
		}
	}

	public static void displayHelp(int status) {
		if(status == 1) {
			System.out.println("The -p option takes a path.");
		} else {
			System.out.println("This is the help message");
		}
		System.exit(1);
	}
}
