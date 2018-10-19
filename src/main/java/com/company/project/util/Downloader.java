package com.company.project.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.company.project.core.ProjectConstant;
import com.company.project.model.Chapter;

public class Downloader {

	public static final String DDXSURL="https://www.x23us.com";
	
	public static List<Chapter> downloadChapters(String url){
		List<Chapter> chapters=new ArrayList<>();
		for(int i=0;i<ProjectConstant.DOWNLOADTRYTIMES;i++) {
			System.out.println("第"+(i+1)+"次下载开始");
			try {
				//System.out.println(Utils.getContent(url));
				String s=Utils.getContent(url);
				Document doc=Jsoup.parse(s);
				Elements elements=doc.getElementsByClass("L");
				elements.forEach(c->{
					System.out.println(c.getElementsByTag("a"));
					Elements as=c.getElementsByTag("a");
					if(as.size()>0) {
						Element chapterElement=as.get(0);
						Chapter chapter=new Chapter();
						chapter.setTitle(chapterElement.text());
						chapter.setUrl(url+chapterElement.attr("href"));
						chapters.add(chapter);
					}
				});
				return chapters;
			} catch (IOException e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("开始重新下载");
			}
		}
		System.out.println("下载失败");
		return null;
	}
	
	public static boolean downloadDetail(Chapter chapter) {
		for(int i=0;i<ProjectConstant.DOWNLOADTRYTIMES;i++) {
			System.out.println("第"+(i+1)+"次下载开始");
			try {
				String s=Utils.getContent(chapter.getUrl());
				//System.out.println(s);
				Document doc=Jsoup.parse(s);
				Element element=doc.getElementById("contents");
				chapter.setContent(element.text());
				return true;
			} catch (IOException e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("开始重新下载");
			}
		}
		return false;
	}
	
	//首席御医章节列表
	private static final String url="https://www.x23us.com/html/4/4371/";
	
	private static final String chapter1="https://www.x23us.com/html/4/4371/14432644.html";
	
	public static void main(String[] args) {
		Chapter chapter=new Chapter();
		chapter.setUrl(chapter1);
		downloadDetail(chapter);
		System.out.println(chapter.getContent());
		//System.out.println(downloadChapters(url).size());
	}
}
