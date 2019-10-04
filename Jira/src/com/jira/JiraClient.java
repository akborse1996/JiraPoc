package com.jira;

import java.net.URI;


import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;

public class JiraClient {

	private static final String JIRA_URL = "https://agile-jira.pearson.com";
	private static final String JIRA_ADMIN_USERNAME = "VBorsAm";
	private static final String JIRA_ADMIN_PASSWORD = "Dad@1996";

	public static void main(String[] args) throws Exception {
		// Construct the JRJC client
		System.out.println(String.format("Logging in to %s with username '%s' and password '%s'", JIRA_URL,
				JIRA_ADMIN_USERNAME, JIRA_ADMIN_PASSWORD));
		JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
		URI uri = new URI(JIRA_URL);
		JiraRestClient client = factory.createWithBasicHttpAuthentication(uri, JIRA_ADMIN_USERNAME,
				JIRA_ADMIN_PASSWORD);

		// Invoke the JRJC Client
//		Promise<User> promise = client.getUserClient().getUser("admin");
//		User user = promise.claim();
//
//		for (BasicProject project : client.getProjectClient().getAllProjects().claim()) {
//			System.out.println(project.getKey() + ": " + project.getName());
//		}
		
		Promise<SearchResult> searchJqlPromise = client.getSearchClient().searchJql(
				"project = \"Global Learning Platform\" AND issuetype = Story");

		for (Issue issue : searchJqlPromise.claim().getIssues()) {
			System.out.println("done");
			System.out.println(issue.getDescription());
		}

		// Print the result
//		System.out.println(String.format("Your admin user's email address is: %s\r\n", user.getEmailAddress()));

		// Done
		System.out.println("Example complete. Now exiting.");
		System.exit(0);
	}
}
