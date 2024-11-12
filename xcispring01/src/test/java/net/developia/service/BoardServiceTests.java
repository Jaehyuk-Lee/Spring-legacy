package net.developia.service;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import net.developia.domain.BoardVO;
import net.developia.domain.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:**/root-context.xml")
@Log4j
public class BoardServiceTests {
	// @Setter(onMethod_ = @Autowired)
	@Autowired
	private BoardService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		service.register(board);
		log.info("생성된 게시물의 번호: " + board.getBno());
	}
	
	@Test
	public void testGetList() throws Exception {
		// service.getList().forEach(board -> log.info(board));
		service.getList(new Criteria(2, 10)).forEach(board -> log.info(board));
	}
	
	@Test
	public void testGet() throws Exception {
		log.info(service.get(10L));
	}
	
	@Test
	public void testModify() throws Exception {
		BoardVO board = service.get(12L);
		if(board == null)
			return;
		// 실행 전 존재하는 번호인지 확인할 것
		board.setBno(12L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("user00");
		log.info("MODIFY RESULT: " + service.modify(board));
	}
	
	@Test
	public void testDelete() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("추가된 제목");
		board.setContent("추가된 내용");
		board.setWriter("user00");
		service.register(board);
		log.info("REMOVE RESULT: " + service.remove(board.getBno()));
	}
}
