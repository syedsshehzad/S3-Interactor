package s3tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.joda.time.DateTimeZone;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfilesConfigFile;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.amazonaws.services.s3.model.S3Object;

public class S3getter {

	public static void main(String[] args) {
		try {
//			File credentials = new File("rootkey");
//			Scanner in = new Scanner(credentials);
//			System.out.println(in.next());
//			ProfilesConfigFile config = new ProfilesConfigFile(credentials);
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
			.withRegion("us-east-1")
			.withCredentials(new ProfileCredentialsProvider())
			.build();
			
			S3Object object = s3Client.getObject(new GetObjectRequest("syedsshehzad", "sample_medical_data.txt"));
			
			displayTextInputStream(object.getObjectContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void displayTextInputStream(InputStream input) throws IOException {
		// Read the text input stream one line at a time and display each line.
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		System.out.println();
	}

}
