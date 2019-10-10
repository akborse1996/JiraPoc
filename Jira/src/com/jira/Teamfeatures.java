package com.jira.jirapoc;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.BasicComponent;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;

public class Teamfeatures {


	
	
public Map<String,Set<String>> getFeatureOFTeams(JiraRestClient client){
			String teamName=null;
			
		    Promise<SearchResult> searchJqlPromise = client.getSearchClient().searchJql(
				"project = \"Global Learning Platform\" AND issuetype = Story",51,0,null);

		// searchJqlPromise.
		List<Issue> res = (List<Issue>) searchJqlPromise.claim().getIssues();
		
		Map<String,Set<String>> teamFeatures = new HashMap<>();
		
	    int i=0;
		//get team features
		for(Issue issue: res) {
			i=i+1;
			Iterator<BasicComponent> itr=issue.getComponents().iterator();
			while(itr.hasNext()) {
				teamName=itr.next().getName();
			}
			
			if(teamFeatures.get(teamName)==null) {
				
				 Set<String> teamFeatureSetnew =new HashSet<String>();
				 if(issue.getFieldByName("Theme").getValue()!=null) {
				 teamFeatureSetnew.add(issue.getFieldByName("Theme").getValue().toString());
				 }
				 teamFeatures.put(teamName, teamFeatureSetnew);
			}
			else {
				 Set<String> teamFeatureSetnew = teamFeatures.get(teamName);
				if(issue.getFieldByName("Theme").getValue()!=null) {
					teamFeatureSetnew.add(issue.getFieldByName("Theme").getValue().toString());
				}
				teamFeatures.put(teamName, teamFeatureSetnew);
			}
		}
		System.out.println(i);
		return teamFeatures;
	}


public static Map<String, ThemePojo> storyCount(JiraRestClient client) {
int a= 0;
Map<String, ThemePojo> featureStory = new HashMap<>();
ThemePojo themePojo = null;

Promise<SearchResult> searchJqlPromise = client.getSearchClient().searchJql(
"project = \"Global Learning Platform\" AND issuetype = Story");

List<Issue> res = (List<Issue>) searchJqlPromise.claim().getIssues();

for (Issue issue : res) {
	a+=1;
if (issue.getFieldByName("Theme").getValue() != null) {
String featureId = issue.getFieldByName("Theme").getValue().toString();

if (!featureStory.containsKey(featureId)) {
themePojo = new ThemePojo();
themePojo.setTotalStories(themePojo.getTotalStories() + 1);

} else {
themePojo = featureStory.get(featureId);
themePojo.setTotalStories(themePojo.getTotalStories() + 1);

}

featureStory.put(featureId, themePojo);
}
}
System.out.println("total Stories : " + a+"\n\n");
return featureStory;
}
		
		
		
		
		
		 
}


