package com.example.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class IssuanceAskMainTabFragment extends Fragment
{
	private Integer userid;
	public IssuanceAskMainTabFragment(Integer userid){
		this.userid = userid;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.my_issuance_ask, container, false);
	}
	
}
