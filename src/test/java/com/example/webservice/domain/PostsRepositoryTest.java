package com.example.webservice.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.webservice.domain.Posts;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
	@Autowired
	PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		/**
		 * 테스트가 실제 영향을 미치지 않게 테스트가 끝나고 repository를 비우는 함수
		 */
		postsRepository.deleteAll();
	}
	
	@Test
	public void boardSave_Open() {
		//given
		postsRepository.save(Posts.builder()
				.title("테스트 게시물")
				.content("테스트 본문")
				.author("테스트 게시자")
				.build());
		
		//when
		List<Posts> PostsList = postsRepository.findAll();
		
		//then
		Posts posts = PostsList.get(0);
		assertThat(posts.getTitle(), is("테스트 게시물"));
		assertThat(posts.getContent(), is("테스트 본문"));
	}
	
	@Test
    public void BaseTimeEntity_regist () {
        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title("테스트 게시글")
                .content("테스트 본문")
                .author("jojoldu@gmail.com")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertTrue(posts.getCreatedDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));
    }
}
