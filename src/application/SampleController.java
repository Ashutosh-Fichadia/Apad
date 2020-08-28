package application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class SampleController implements Initializable {

		File fopen=null;  
	
		@FXML
	    private AnchorPane leftanchor;

	    @FXML
	    private Button btnnew;

	    @FXML
	    private Button saveasbtn;

	    @FXML
	    private Button btnsave;

	    @FXML
	    private Button btnopen;

	    @FXML
	    private AnchorPane topanchor;

	    @FXML
	    private TextArea txtarea;
	    @FXML
	    private Label lblnm;

	    @FXML
	    private Label lblmsg;
	    
	    File tmp=null;
	   
	    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		//tmpfile();
		clicks();
	}
	void clicks()
	{
		btnnew.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		btnopen.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				open();
			}
		});
		btnsave.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				save();
			}
		});
		saveasbtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				saveAs();
			}
		});
	}
	void open()
	{
		try {
			FileChooser fc = new FileChooser();
			
			FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("TEXT Files (*.txt)", "*.txt");
            fc.getExtensionFilters().add(txtFilter);
            FileChooser.ExtensionFilter phpFilter = new FileChooser.ExtensionFilter("PHP Files (*.php)", "*.php");
            fc.getExtensionFilters().add(phpFilter);
            FileChooser.ExtensionFilter javaFilter = new FileChooser.ExtensionFilter("JAVA Files (*.java)", "*.java");
            fc.getExtensionFilters().add(javaFilter);
            FileChooser.ExtensionFilter cssFilter = new FileChooser.ExtensionFilter("CSS Files (*.css)", "*.css");
            fc.getExtensionFilters().add(cssFilter);
            FileChooser.ExtensionFilter htmlFilter = new FileChooser.ExtensionFilter("HTML Files (*.html)", "*.html");
            fc.getExtensionFilters().add(htmlFilter);
			
            tmpfile();
			String path = getpath();
			fc.setInitialDirectory(new File(path));
			fopen = fc.showOpenDialog(Main.mainstage);
			
			FileReader fr = new FileReader(fopen);
			String data= "";
			int n;
			while((n=fr.read())!=-1)
			{
				data = data + (char)n;
			}
			setpath(fopen.getParent());
			fr.close();
			
			txtarea.setText(data);
			lblnm.setText(fopen.getName());
			
		}
		catch(Exception e)
		{
			lblnm.setText("Retry");
			e.printStackTrace();
		}
	}
	void save() {
		try
		{
			if(fopen!=null)
			{
				
				FileWriter fw = new FileWriter(fopen);
				fw.write(txtarea.getText());
				lblnm.setText(fopen.getName());
				fw.close();
				lblmsg.setText("File Saved!");
				
			}
			else
			{
				saveAs();
			}
		}
		catch(Exception e)
		{
			lblnm.setText("Retry" + e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}
	void saveAs() {
		try{
			FileChooser fc = new FileChooser();
			
			
			//FileChooser fc = new FileChooser();
			
			FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("TEXT Files (*.txt)", "*.txt");
            fc.getExtensionFilters().add(txtFilter);
            FileChooser.ExtensionFilter phpFilter = new FileChooser.ExtensionFilter("PHP Files (*.php)", "*.php");
            fc.getExtensionFilters().add(phpFilter);
            FileChooser.ExtensionFilter javaFilter = new FileChooser.ExtensionFilter("JAVA Files (*.java)", "*.java");
            fc.getExtensionFilters().add(javaFilter);
            FileChooser.ExtensionFilter cssFilter = new FileChooser.ExtensionFilter("CSS Files (*.css)", "*.css");
            fc.getExtensionFilters().add(cssFilter);
            FileChooser.ExtensionFilter htmlFilter = new FileChooser.ExtensionFilter("HTML Files (*.html)", "*.html");
            fc.getExtensionFilters().add(htmlFilter);
            
            tmpfile();
			String path = getpath();
			fc.setInitialDirectory(new File(path));
			
			
            fopen = fc.showSaveDialog(Main.mainstage);
            
			FileWriter fw = new FileWriter(fopen);
			fw.write(txtarea.getText());
			setpath(fopen.getParent());
			lblnm.setText(fopen.getName());
			fw.close();
			lblmsg.setText("File Saved!");
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	String getpath() {
		String path="";
		try
		{
			FileReader fr = new FileReader(tmp);
			int n;
			
			while((n=fr.read())!=-1)
			{
				path = path + (char)n;
			}
			fr.close();
			
			byte[] actualByte = Base64.getDecoder().decode(path); 

			path = new String(actualByte); 
			
			return path;
		}
		catch(Exception e)
		{
			
		}
		return path;
		///return getpath();
	}
	void setpath(String path) {
		try {
			path= Base64.getEncoder().encodeToString(path.getBytes());
			FileWriter fw = new FileWriter(tmp);
			fw.write(path);
			fw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void tmpfile() {
		try {
			tmp = new File("temp.txt");
			//tmp.createNewFile();
			//System.out.println(data);
			
			
			//setpath(fopen.getParent());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
