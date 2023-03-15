package com.example.fundamental1.Api;

import java.util.List;

public class FollowersResponse{
	private List<FollowersResponseItem> followersResponse;

	public void setFollowersResponse(List<FollowersResponseItem> followersResponse){
		this.followersResponse = followersResponse;
	}

	public List<FollowersResponseItem> getFollowersResponse(){
		return followersResponse;
	}
}