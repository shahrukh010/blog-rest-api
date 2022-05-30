package com.code.blog.payload;

import lombok.Data;
//dto:->data transfer object

@Data
public class PostDto {

	private long id;
	private String title;
	private String description;
	private String content;
}
