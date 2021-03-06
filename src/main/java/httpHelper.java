import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;


public class httpHelper {
	public String userId = "579";
	public String teamId = "1110";
	public String gameId = "1313";
	public String api_key = "932bb0d705b3fc25d6c2";
	

	
	public String getMember()throws IOException {
		URL url = new URL("http://www.notexponential.com/aip2pgaming/api/index.php?type=team&teamId="+teamId);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("x-api-key", api_key);
		con.setRequestProperty("userid",userId);
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
				    content.append(inputLine);
		}
		in.close();
		con.disconnect();
		return content.toString();
	}
	
	public getMovesResult getMoves(int count)throws IOException {
		URL url = new URL("http://www.notexponential.com/aip2pgaming/api/index.php?type=moves&gameId="+gameId+"&count="+Integer.toString(count));
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("x-api-key", api_key);
		con.setRequestProperty("userid",userId);
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
				    content.append(inputLine);
		}
		in.close();
		con.disconnect();
		Gson gson = new Gson(); 
		getMovesResult ans = gson.fromJson(content.toString(), getMovesResult.class);
		return ans;
	}
	
	public String makeMove(String move) throws IOException{
		URL url = new URL("http://www.notexponential.com/aip2pgaming/api/index.php");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("x-api-key", api_key);
		con.setRequestProperty("userid",userId);
		con.setDoOutput(true);
		String parameters = "type=move&gameId="+gameId+"&teamId="+teamId+"&move="+move;
		DataOutputStream os = new DataOutputStream(con.getOutputStream());
		os.writeBytes(parameters);
		os.flush();
		os.close();
		
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
				    content.append(inputLine);
		}
		in.close();
		con.disconnect();
		return content.toString();
	}
	
	public String createGame(String tid1, String tid2) throws IOException{
		URL url = new URL("http://www.notexponential.com/aip2pgaming/api/index.php");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("x-api-key", api_key);
		con.setRequestProperty("userid",userId);
		con.setDoOutput(true);
		String parameters = "type=game&teamId1="+tid1+"&teamId2="+tid2;
		DataOutputStream os = new DataOutputStream(con.getOutputStream());
		os.writeBytes(parameters);
		os.flush();
		os.close();
		
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
				    content.append(inputLine);
		}
		in.close();
		con.disconnect();
		return content.toString();
	}
	
}
