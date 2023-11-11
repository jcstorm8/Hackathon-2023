package maven;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;

//import org.hl7.fhir.dstu3.model.DiagnosticReport;
//import org.hl7.fhir.r4.model.Patient;
//import org.hl7.fhir.instance.model.api.IBaseResource;
//
//import ca.uhn.fhir.context.FhirContext;
//import ca.uhn.fhir.model.valueset.BundleTypeEnum;
//import ca.uhn.fhir.parser.IParser;
public class Connected {
//	static FhirContext ctx = FhirContext.forR4();
//	//FhirContext ctx = FhirContext.forDstu3();
//    static IParser parser = ctx.newJsonParser();

	public static void main(String[] args) throws Exception {
		// Client credentials
		String clientId = "220";
		String clientSecret = "070446288f9b7cf052f55d4b731e0054";
		
		// Scope
		String scope = "system/*.*";
		
		// Create form params
		String formParams = "grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientSecret+ "&scope=" + scope;
		
		HttpClient client = HttpClient.newHttpClient();
		
		// Use form params as request body
		HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://app.azaleahealth.com/fhir/R4/142449/oauth/token"))
		.POST(HttpRequest.BodyPublishers.ofString(formParams))
		.header("Content-Type",
		"application/x-www-form-urlencoded")
		.build();
		
		HttpResponse<String> response = client.send(request,
		HttpResponse.BodyHandlers.ofString());
		//String accessToken = response.body();
		
		//Store the accessToken to use it future resposes Token is only vaild for 1 hour
		String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyMjAiLCJqdGkiOiI2YTVlNmQ1MDhhOGU4MmVhY2Y2MDBlODBkMDYxOTU3NmFjN2M1NzViYTQ0NjgyZDYxOWRhZjViYWZjMTM2NDA5ZDg2YmFjOGRiYjdmNjU4ZCIsImlhdCI6MTY5OTcyNjYzNS40Mjk4MDYsIm5iZiI6MTY5OTcyNjYzNS40Mjk4MSwiZXhwIjoxNjk5NzMwMjM1LjQxNjQ3OCwic3ViIjoiIiwic2NvcGVzIjp7InN5c3RlbS8qLioiOiJzeXN0ZW0vKi4qIiwidGVuYW50LzE0MjQ0OSI6InRlbmFudC8xNDI0NDkifX0.bL-Y9ml4jnmo3GJWHRwjlud2V-vaRRaYsjrHFx1R1LnlwoUtsehp3iqwom-269UAqWMbjdRrelmqKIwbjFfYLl-EvUDmUMoOkfnKE8pyWJwI2ZYJGP1OpGUGYSMfxEucgPkNoP3Aw2WocAOrsUZT7RjMWLtsX328TrLDxnv5joBIH7I-1gK3OJ-BqxJbKQbshGkLlHftMCqYmv0Fk2woQZbpAzrBpZ727it621MY9LTOukLMaMAuzgri2bAoJ2A8CYv1pxkBe8TWcF2fs8zIBYD-xV-T4a-Cr5ZhZMdsDGHknlxdd6xGTBE_euMr4EHxpkvzQIOHveaL-SfPjOLdnjBeb-f2CGOGY8lSZMONTSaW1jQ-jNen4D9BVtxbROqWjeX2pk5a56Jn3KMRJMeHxc_STKLbl-3xfh_FjlO424VzXPnXnr4e3XlqqWubB2urYxntrPgjY3DtpIyXmKSiFLEnxH6B7Pu844WgE02b5r8VQ-in8XmdmQueaQ7gW3-_N9RXAIKPL57OouA5EMsMrzHyWpIcCvqRjIAckEnaxV0vHqs4o6_I3YRmhDx192jPReQJkp3KI-kjDgYmKUkdCzFs8tkHaMHiZon08mi-5PDcVBdrFBfDqOIZUvqiDKiE-X43rJv8QwK9xFtb429qq8Zu81RHcHzZVhe8bqiKPzs";
		// Create client to send GET request
		HttpClient clientA = HttpClient.newHttpClient();
		
		// Build GET request with access token
		HttpRequest requestA = HttpRequest.newBuilder().header("Authorization", "Bearer " + accessToken).uri(URI.create("https://app.azaleahealth.com/fhir/R4/142449/Patient")).GET().build();
		//HttpRequest requestA = HttpRequest.newBuilder().header("Authorization", "Bearer " + accessToken).uri(URI.create("https://app.azaleahealth.com/fhir/R4/sandbox/Bundle?_id=NULL")).GET().build();

		// Send request
		HttpResponse<String> responseA = clientA.send(requestA,
		HttpResponse.BodyHandlers.ofString());

		// Handle response
	    
		if (responseA.statusCode() == 200) {
			
			String patient = responseA.body();
			String[] tokens = patient.split(",");
			
					
			//System.out.println(patient);
		    File myObj = new File("filename.txt");
		    File nameFile = new File("name.txt");
		    PrintWriter writer = new PrintWriter(myObj);
		    
		    PrintWriter names = new PrintWriter(nameFile);
		    
			
			try {
				int count = 0;
				for(int i=0; i<tokens.length; i++) {
		    		tokens[i] = tokens[i].replaceAll("[-+.^{},\"]","");
					writer.print(tokens[i] + "\n ");
//					if(tokens[i].equals("text")) {
//						count++;
//						String[] s = tokens[i].split(":");
//
//					}
//					System.out.println(count);
					//}
					//input.close();

				}
				
				Scanner input = new Scanner( myObj );
				String in = input.nextLine();
				//String[] s = in.split(":");
				//System.out.println(Arrays.toString(s));
				for(int i = 0; i < in.length(); i++) {
					if(in.equals("text")) {
						//count += 1;
						String[] s = in.split(":");
						System.out.println(s);

					}
					System.out.println(in);
					System.out.println(count);
					}
//						
////						for(int i = 0; i < s.length; i++) {
////							System.out.println(s[i]);
////						}
//						//System.out.println(count);
//
//					}

				 if (myObj.createNewFile()) {
				        System.out.println("File created: " + myObj.getName());
				 }
				      
			      if (nameFile.createNewFile()) {
				        System.out.println("File created: " + myObj.getName());
				  }
					      
			      else {
			        System.out.println("File already exists.");
			      }
	
			}
			
			 catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		      }
		}
		    
		    //ignore this part
//			try {
//				int count = 0;
//				Scanner input = new Scanner( myObj );
//					String in = input.next();
//					//String[] s = in.split(":");
//					//System.out.println(Arrays.toString(s));
//					for(int i = 0; i< .length; i++) {
//						String[] s = in.split(":");
//						if(s[i].equals("text")) {
//							count++;
//							
////							for(int i = 0; i < s.length; i++) {
////								System.out.println(s[i]);
////							}
//							//System.out.println(count);
//
//						}
				//	}
//					
////					for(int i = 0; i< in.length(); i++) {
////						if(in.equals("text")) {
////							String[] s = in.split(":");
////							System.out.println(s);
////							names.print(s);
////						}
////					}
////					
//					writer.print(in);
//					
//				}		      
//				System.out.println(count);

				
				      
			      //FileWriter myWriter = new FileWriter("filename.txt");
			     
	else {
		System.out.println("Error: " + responseA.statusCode());
	
	}
//		      catch (IOException e) {
//			      System.out.println("An error occurred.");
//			      e.printStackTrace();
//		      }
//			for(int i = 0; i < tokens.length; i++) {
//				System.out.println(tokens[i]);
//			}
			//System.out.println(Arrays.toString(tokens) + "\n");
//			Patient parsed = parser.parseResource(Patient.class, patient);
//			//Bundle parse = parser.parseResource(Bundle.class, patient);
//
//			System.out.println(parsed.getName().get(0).getFamily());
			//DiagnosticReport report = parser.parseResource(DiagnosticReport.class, patient);
//
//		    IBaseResource parsed = parser.parseResource(IBaseResource.class, patient);
//
//			System.out.println(parsed);
//			// Parse FHIR JSON patient resource
		
	
		//creating a text file
	
	
	
	
	
	
	

		

 // Instantiate a new parser
    
	}
	
	
}
