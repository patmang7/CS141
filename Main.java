import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

import java.io.*;
import java.util.Hashtable;


public class Main extends Application {
	private static final int NUMBER_OF_PRINTERS = 3;
	private static final int NUMBER_OF_DISKS = 2;
	private static final int NUMBER_OF_USERS = 4;
	static final Disk[] Disks = new Disk[NUMBER_OF_DISKS];
	private static final UserThread[] UserThreads = new UserThread[NUMBER_OF_USERS];
	static final Printer[] Printers = new Printer[NUMBER_OF_PRINTERS];
	static final DirectoryManager Directory_Manager = new DirectoryManager();
	static final ResourceManager Disk_Resource_Manager = new ResourceManager(NUMBER_OF_DISKS);
	static final ResourceManager Printer_Resource_Manager = new ResourceManager(NUMBER_OF_PRINTERS);

	static Rectangle printer1;
	static Rectangle printer2;
	static Rectangle printer3;
	private Text printertext;
	private Text printer1tx;
	private Text printer2tx;
	private Text printer3tx;
	static Text printer1out;
	static Text printer2out;
	static Text printer3out;

	static Circle disk1;
	static Circle disk2;
	private Text disktext;
	private Text disk1tx;
	private Text disk2tx;
	static Text disk1status;
	static Text disk2status;


	private Text usertitle;
	private Text user1title;
	private Text user2title;
	private Text user3title;
	private Text user4title;
	static Text user1desc;
	static Text user2desc;
	static Text user3desc;
	static Text user4desc;


	private Text descriptionprintera;
	private Text descriptionprinterb;
	private Text descriptiondiska;
	private Text descriptiondiskb;
	private Text descriptiondiskc;

	static Button startbutton;

	
	public void start(Stage primaryStage) throws Exception{
		primaryStage.setTitle("141OS");

		//buttons
		startbutton = new Button("Start");
		startbutton.setLayoutX(330);
		startbutton.setLayoutY(190);
		startbutton.setOnAction(click -> {
			for (int i = 0; i < UserThreads.length; i++) {
				UserThreads[i].start();
			}
		});

		//descriptions
		descriptionprintera = new Text(10,110 , "GREEN = not in use.");
		descriptionprinterb = new Text(10, 130, "RED = in use.");
		descriptiondiska = new Text(500, 110, "Green = Waiting");
		descriptiondiskb = new Text(500, 130, "Yellow = Writing");
		descriptiondiskc = new Text(500, 150, "Cyan = Reading");



		//printers
		printer1= new Rectangle(10, 25, 50, 40);;
		printer1.setFill(Color.DARKGREEN);

		printer2 = new Rectangle(150, 25, 50, 40);
		printer2.setFill(Color.DARKGREEN);

		printer3 = new Rectangle(300, 25, 50, 40);
		printer3.setFill(Color.DARKGREEN);

		printertext = new Text(140, 18, "Printers");
		printertext.setFont(Font.font(20));

		printer1tx = new Text(33, 48 , "1");
		printer2tx = new Text(175, 48, "2");
		printer3tx = new Text(325, 48, "3");

		printer1out = new Text(5, 75, "Waiting");
		printer2out = new Text(147, 75, "Waiting");
		printer3out = new Text(295, 75, "Waiting");

		//Disks
		disk1 = new Circle(500,50 ,25 );
		disk1.setFill(Color.DARKGREEN);
		disk1tx = new Text(498, 55, "1");
		disk1status = new Text(480, 85, "Waiting");

		disk2 = new Circle(600, 50, 25);
		disk2.setFill(Color.DARKGREEN);
		disk2tx = new Text(598, 55, "2");
		disk2status = new Text(580, 85, "Waiting");

		disktext = new Text(525, 20, "Disks");
		disktext.setFont(Font.font(20));

		//users
		user1title = new Text(20, 350, "User 1: ");
		user1desc = new Text(20, 370, "Waiting");

		user2title= new Text(150, 350, "User 2: ");
		user2desc = new Text(150, 370, "Waiting");

		user3title = new Text(280, 350, "User 3: ");
		user3desc = new Text(280, 370, "Waiting");

		user4title = new Text(410, 350, "User 4: ");
		user4desc = new Text(410, 370, "Waiting");

		usertitle = new Text(210, 320, "Users");
		usertitle.setFont(Font.font(20));



		Pane layout = new Pane();
		layout.getChildren().add(descriptionprintera);
		layout.getChildren().add(descriptionprinterb);
		layout.getChildren().add(descriptiondiska);
		layout.getChildren().add(descriptiondiskb);
		layout.getChildren().add(descriptiondiskc);
		layout.getChildren().add(startbutton);


		layout.getChildren().add(printer1);
		layout.getChildren().add(printer2);
		layout.getChildren().add(printer3);
		layout.getChildren().add(printertext);
		layout.getChildren().add(printer1tx);
		layout.getChildren().add(printer2tx);
		layout.getChildren().add(printer3tx);
		layout.getChildren().add(printer1out);
		layout.getChildren().add(printer2out);
		layout.getChildren().add(printer3out);

		layout.getChildren().add(disk1);
		layout.getChildren().add(disk2);
		layout.getChildren().add(disktext);
		layout.getChildren().add(disk1tx);
		layout.getChildren().add(disk2tx);
		layout.getChildren().add(disk1status);
		layout.getChildren().add(disk2status);

		layout.getChildren().add(user1title);
		layout.getChildren().add(user2title);
		layout.getChildren().add(user3title);
		layout.getChildren().add(user4title);
		layout.getChildren().add(user1desc);
		layout.getChildren().add(user2desc);
		layout.getChildren().add(user3desc);
		layout.getChildren().add(user4desc);
		layout.getChildren().add(usertitle);



		primaryStage.setScene(new Scene(layout, 700, 400, Color.RED));
		primaryStage.show();
	}

	public static void main(String[] args) {
		for (int i = 0; i < Disks.length; i++) {
			Disks[i] = new Disk(i);
		}
		for (int i = 0; i < Printers.length; i++) {
			Printers[i] = new Printer(i);
		}
		for (int i = 0; i < UserThreads.length; i++) {
			UserThreads[i] = new UserThread(i);
		}


		launch(args);
	}
}


class Disk{
	static final int NUM_SECTORS = 1024;
	StringBuffer sectors[];
	int disk_number;

	public Disk(int number){
		this.disk_number = number;
		this.sectors = new StringBuffer[NUM_SECTORS];
		for(int i = 0; i < sectors.length; i++){
			sectors[i] = new StringBuffer();
		}
	}

	void write(int sector, StringBuffer data){
		if (disk_number == 0) {
			Platform.runLater(() -> {
				try {
					Main.disk1.setFill(Color.YELLOW);
					Main.disk1status.setText("Writing");
				}
				catch (NullPointerException e){
				}
			});
		} else if (disk_number == 1) {
			Platform.runLater(() -> {
				try {
					Main.disk2.setFill(Color.YELLOW);
					Main.disk2status.setText("Writing");
				}
				catch (NullPointerException e){
				}
			});
		}


		int x = sectors[sector].length();
		if(x == 0){
			sectors[sector].append(data.toString());
		}
		else{
			sectors[sector].replace(0, x - 1, data.toString());
		}

		try {
			Thread.sleep(150);
		}catch(InterruptedException e){
		}

		if (disk_number == 0) {
			Platform.runLater(() -> {
				try {
					Main.disk1.setFill(Color.DARKGREEN);
					Main.disk1status.setText("Waiting");
				}
				catch (NullPointerException e){
				}
			});
		} else if (disk_number == 1) {
			Platform.runLater(() -> {
				try {
					Main.disk2.setFill(Color.DARKGREEN);
					Main.disk2status.setText("Waiting");
				}
				catch (NullPointerException e){
				}
			});
		}



	}

	void read(int sector, StringBuffer data){
		if (disk_number == 0) {
			Platform.runLater(() -> {
				try {
					Main.disk1.setFill(Color.CYAN);
					Main.disk1status.setText("Reading");
				}
				catch (NullPointerException e){
				}
			});
		} else if (disk_number == 1) {
			Platform.runLater(() -> {
				try {
					Main.disk2.setFill(Color.CYAN);
					Main.disk2status.setText("Reading");


				}
				catch (NullPointerException e){
				}
			});
		}

		data.append(sectors[sector]);

		try {
			Thread.sleep(150);
		}catch(InterruptedException e){
		}

		if (disk_number == 0) {
			Platform.runLater(() -> {
				try {
					Main.disk1.setFill(Color.DARKGREEN);
					Main.disk1status.setText("Waiting");
				}
				catch (NullPointerException e){
				}
			});
		} else if (disk_number == 1) {
			Platform.runLater(() -> {
				try {
					Main.disk2.setFill(Color.DARKGREEN);
					Main.disk2status.setText("Waiting");
				}
				catch (NullPointerException e){
				}
			});
		}



	}
}


class Printer{
	private final File file;
	Printer(int i){
		file = new File("outputs/PRINTER"+i);
	}

	void print(StringBuffer output){
		try(BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(file, true)))
		{
			String stringout = output.toString();
			bufferedwriter.write(stringout + "\n");
			bufferedwriter.flush();
		}

		catch(IOException e){
			System.out.println("IOERROR: " +e);
		}
	}
}



class UserThread extends Thread {

	String filename;
	int thisDisk = 0;
	StringBuffer thisLine = new StringBuffer();
	StringBuffer thisFile = null;

	int usernum;
	UserThread(int i) {
		i++;
		usernum = i;
		filename = ("inputs/USER" + i);
	}


	public void run() {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String s = new String();

			while ((s = br.readLine()) != null) {
				thisLine.setLength(0);
				thisLine.append(s);

				if (thisLine.toString().startsWith(".print")) {
					new PrintJobThread(new StringBuffer(thisLine.substring(7))).start();
				}
				else if (thisLine.toString().startsWith(".save")) {
					thisDisk = Main.Disk_Resource_Manager.request();
					thisFile = new StringBuffer(thisLine.substring(6));


				}
				else if (thisLine.toString().startsWith(".end")) {
					Main.Disk_Resource_Manager.release(thisDisk);
					thisFile = null;

				}
				else if (thisFile != null) {
					Disk disk = Main.Disks[thisDisk];
					FileInfo info = Main.Directory_Manager.lookup(thisFile);

					if (info != null) {
						int x = thisDisk +1;
						if(usernum == 1) {
							Platform.runLater(() -> {
								Main.user1desc.setText("Writing to Disk " + x);
							});
						} else if (usernum == 2) {
							Platform.runLater(() -> {
								Main.user2desc.setText("Writing to Disk " + x);
							});
						} else if (usernum == 3){
							Platform.runLater(() -> {
								Main.user3desc.setText("Writing to Disk " + x);
							});
						}else if (usernum == 4){
							Platform.runLater(() -> {
								Main.user4desc.setText("Writing to Disk " + x);
							});
						}



						Thread.sleep(200);
						int next = Main.Directory_Manager.get_increment_nextsector(thisDisk);
						info.fileLength = info.fileLength + 1;
						disk.write(next, thisLine);

						if(usernum == 1) {
							Platform.runLater(() -> {
								Main.user1desc.setText("Waiting");
							});
						} else if (usernum == 2) {
							Platform.runLater(() -> {
								Main.user2desc.setText("Waiting");
							});
						} else if (usernum == 3){
							Platform.runLater(() -> {
								Main.user3desc.setText("Waiting");
							});
						}else if (usernum == 4){
							Platform.runLater(() -> {
								Main.user4desc.setText("Waiting");
							});
						}

					}
					else {
						int x = thisDisk +1;
						if(usernum == 1) {
							Platform.runLater(() -> {
								Main.user1desc.setText("Writing to Disk " + x);
							});
						} else if (usernum == 2) {
							Platform.runLater(() -> {
								Main.user2desc.setText("Writing to Disk " + x);
							});
						} else if (usernum == 3){
							Platform.runLater(() -> {
								Main.user3desc.setText("Writing to Disk " + x);
							});
						}else if (usernum == 4){
							Platform.runLater(() -> {
								Main.user4desc.setText("Writing to Disk " + x);
							});
						}


						Thread.sleep(200);
						info = new FileInfo();
						info.fileLength = 1;
						info.diskNumber = thisDisk;
						info.startingSector = Main.Directory_Manager.get_increment_nextsector(thisDisk);
						Main.Directory_Manager.enter(thisFile, info);
						disk.write(info.startingSector, thisLine);


						if(usernum == 1) {
							Platform.runLater(() -> {
								Main.user1desc.setText("Waiting");
							});
						} else if (usernum == 2) {
							Platform.runLater(() -> {
								Main.user2desc.setText("Waiting");
							});
						} else if (usernum == 3){
							Platform.runLater(() -> {
								Main.user3desc.setText("Waiting");
							});
						}else if (usernum == 4){
							Platform.runLater(() -> {
								Main.user4desc.setText("Waiting");
							});
						}

					}
				}

			}

		} catch (InterruptedException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

	}
}


class ResourceManager {
	private final boolean[] isFree;
	ResourceManager(int numberOfItems)
	{
		isFree = new boolean[numberOfItems];
		for (int i=0; i<isFree.length; ++i)
			isFree[i] = true;
	}

	synchronized int request()
	{
		while (true)
		{
			for (int i = 0; i < isFree.length; ++i)
				if ( isFree[i] )
				{
					isFree[i] = false;
					return i;
				}
			try {
				this.wait(); // block until someone releases a Resource
			}
			catch(InterruptedException e){
				System.out.println(e);
			}
		}
	}
	synchronized void release( int index )
	{
		isFree[index] = true;
		this.notify(); // let a waiting thread run
	}
}


class PrintJobThread extends Thread{

	private final StringBuffer thisfile;
	PrintJobThread(StringBuffer file){
		this.thisfile = new StringBuffer(file);
	}


	public void run(){

		FileInfo thisInfo = Main.Directory_Manager.lookup(thisfile);
		Disk thisDisk = Main.Disks[thisInfo.diskNumber];
		int printerNo = Main.Printer_Resource_Manager.request();

		Printer printer = Main.Printers[printerNo];
		StringBuffer sector = new StringBuffer();

		for(int i = 0; i < thisInfo.fileLength; i++){
			sector.setLength(0);

			thisDisk.read(thisInfo.startingSector+i, sector);
			try{
				Thread.sleep(200);
			}
			catch(InterruptedException e){
				System.out.println(e);
			}

			if(printerNo == 0) {
				Platform.runLater(() -> {
					Main.printer1.setFill(Color.RED);
					Main.printer1out.setText("Printing " + sector.toString());
				});
			} else if(printerNo == 1){
				Platform.runLater(() -> {
					Main.printer2.setFill(Color.RED);
					Main.printer2out.setText("Printing " + sector.toString());
				});
			} else if(printerNo == 2){
				Platform.runLater(() -> {
					Main.printer3.setFill(Color.RED);
					Main.printer3out.setText("Printing " + sector.toString());
				});
			}

			printer.print(sector);
			try{
				Thread.sleep(2750);
			}
			catch(InterruptedException e){
				System.out.println(e);
			}

		}
		Main.Printer_Resource_Manager.release(printerNo);
		if(printerNo == 0) {
			Platform.runLater(() -> {
				Main.printer1.setFill(Color.DARKGREEN);
				Main.printer1out.setText("Waiting");
			});
		}
		else if(printerNo == 1){
			Platform.runLater(() -> {
				Main.printer2.setFill(Color.DARKGREEN);
				Main.printer2out.setText("Waiting");
			});
		}
		else if(printerNo == 2){
			Platform.runLater(() -> {
				Main.printer3.setFill(Color.DARKGREEN);
				Main.printer3out.setText("Waiting");
			});
		}
	}

}


class DirectoryManager
{
	private Hashtable<String, FileInfo> T = new Hashtable<>();
	int[] nextSector;

	DirectoryManager(){
		nextSector = new int[2];
		//Hashtable<String, FileInfo> T = new Hashtable<>();
	}

	void enter(StringBuffer key, FileInfo file){
		T.put(key.toString(), file);
	}

	FileInfo lookup(StringBuffer key){
		return this.T.get(key.toString());
	}

	int get_increment_nextsector(int disk){
		int n = nextSector[disk];
		nextSector[disk] = nextSector[disk] + 1;
		return n;
	}

}


class FileInfo
{
	int diskNumber;
	int startingSector;
	int fileLength;
}

