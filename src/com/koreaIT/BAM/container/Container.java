package com.koreaIT.BAM.container;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.dto.Member;

@SuppressWarnings("unused")
public class Container {
	public static ArrayList<Member> members;
	public static ArrayList<Article> articles;
	
	static {
		members = new ArrayList<>();
		articles = new ArrayList<>();
	}
}
