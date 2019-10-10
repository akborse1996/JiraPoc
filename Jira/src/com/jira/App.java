package com.jira.jirapoc;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;


//@PropertySource("classpath:application.properties")
@PropertySource("src/main/resources/foo.properties")
public class App {
	
	@Autowired
	Environment env;
	
	

	private static final String JIRA_URL = "https://agile-jira.pearson.com";
	private static final String JIRA_ADMIN_USERNAME = "VBorsAm";
	private static final String JIRA_ADMIN_PASSWORD = "Dad@1996";

	public static void main(String[] args) throws Exception {
		Teamfeatures teamfeatures=new Teamfeatures();
	//	System.out.println("from properties file "+env.getProperty("user1"));
		// Construct the JRJC client
		System.out.println(String.format("Logging in to %s with username '%s' and password '%s'", JIRA_URL,
				JIRA_ADMIN_USERNAME, JIRA_ADMIN_PASSWORD));
		JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
		
	
		
		
		URI uri = new URI(JIRA_URL);
		JiraRestClient client = factory.createWithBasicHttpAuthentication(uri, JIRA_ADMIN_USERNAME,
				JIRA_ADMIN_PASSWORD);
		//************************************************************************//
//		Map<String,Set<String>> res=teamfeatures.getFeatureOFTeams(client);
//		
//		res.forEach((key,value)->{System.out.print(key);
//		   System.out.println(value);
//		  });
		//************************************************************************//
		
		Map<String, ThemePojo> res=Teamfeatures.storyCount(client);
		
		res.forEach((key,value)->{System.out.print(key);
		   System.out.println(value);
		  });
		
		
	
	}
}