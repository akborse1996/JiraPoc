package com.jira.jirapoc;

public class ThemePojo {

private int yetToBePicked;
private int pickedUpInCurrentStream;
private int completedStories;
private int totalStories;





public int getTotalStories() {
	return totalStories;
}
public void setTotalStories(int totalStories) {
	this.totalStories = totalStories;
}
public int getYetToBePicked() {
return yetToBePicked;
}
public void setYetToBePicked(int yetToBePicked) {
this.yetToBePicked = yetToBePicked;
}
public int getPickedUpInCurrentStream() {
return pickedUpInCurrentStream;
}
public void setPickedUpInCurrentStream(int pickedUpInCurrentStream) {
this.pickedUpInCurrentStream = pickedUpInCurrentStream;
}
public int getCompletedStories() {
return completedStories;
}
public void setCompletedStories(int completedStories) {
this.completedStories = completedStories;
}
@Override
public String toString() {
	return "ThemePojo [yetToBePicked=" + yetToBePicked + ", pickedUpInCurrentStream=" + pickedUpInCurrentStream
			+ ", completedStories=" + completedStories + ", totalStories=" + totalStories + "]";
}

}