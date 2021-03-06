package se.dixum.ld28.one.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;


public class Conversation {
	
	FileHandle file;
	Array<String> persons;
	Array<String> value;
	
	public Conversation(String path){
		file = Gdx.files.internal(path);
		persons  = new Array<String>();
		value = new Array<String>();
		
		String text = file.readString();
		text = text.trim();
		
		if(file.exists()){
			for(int i = 0;i < text.length();i++){
				if(text.charAt(i)=='['){ 
					for(int j = i;j < text.length();j++){
						if(text.charAt(j)==']'){
							for(int k = j;k < text.length();k++){
								if(text.charAt(k)=='*'){
									persons.add(text.substring(i+1,j).trim());
									value.add(text.substring(j+1,k).trim());
									break;
								}
							}
							break;
						}
					}
				}
		
			}
		}else{
			System.out.println("No file");
		}
	}
	public Array<Array<String>> getConversationArray(){
		Array<Array<String>> arr = new Array<Array<String>>();
		arr.add(persons);
		arr.add(value);
		return arr;
	}
	public String printConversation(){
		String conversation = ""; 
		for(int i = 0; i < persons.size;i++){
			conversation += persons.get(i) + ":\n\t"+ value.get(1)+"\n";   
		}
		return conversation;
		
	}
	public String printConversation(int index){
		return persons.get(index) + ":\n\t"+ value.get(index)+"\n";
	}
}