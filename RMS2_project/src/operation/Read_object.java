package operation;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.util.Properties;
	// no change in this file 
	public class Read_object {
		Properties p=new Properties();
		public Properties getobjectrepository() throws IOException
		{
			File src=new File("C://Users//chaman.preet//Documents//C data//git//RMS2_workspace//RMS2_project//src//objects//object.properties");
			FileInputStream fs=new FileInputStream(src);
			p.load(fs);
		//	p.setProperty("managecomps", "MANAGE COMPS");
		// upload comps by sweta 
		//	p.setProperty("submitbtn","button.blueButton.UtSubmit");
			return p;
}
	}
