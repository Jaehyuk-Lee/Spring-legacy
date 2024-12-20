package net.developia.mapper;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.developia.domain.BoardVO;
import net.developia.domain.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:**/root-context.xml")
@Log4j
public class BoardMapperTests {
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
//		mapper.getList().forEach(board -> log.info("" + board));
		
		List<BoardVO> list = mapper.getList();
		assertNotEquals(null, list);
	}
	
	@Test
	public void testGetListWithPaging() {
//		mapper.getList().forEach(board -> log.info("" + board));
		
		List<BoardVO> list = mapper.getListWithPaging(new Criteria(3, 10));
		assertNotEquals(null, list);
	}
	
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		mapper.insert(board);
		
		log.info("" + board);
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 select key");
		board.setContent("새로 작성하는 내용 select key");
		board.setWriter("newbie");
		
		mapper.insertSelectKey(board);
		
		log.info("" + board);
	}
	
	@Test
	public void testRead() {
		// 존재하는 게시물 번호로 테스트
		BoardVO board = mapper.read(100L);
		
		log.info("" + board);
	}
	
	@Test
	public void testDelete() {
//		log.info("DELETE COUNT: "  + mapper.delete(11L));
		assertTrue(mapper.delete(25L) == 1);
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		// 실행 전 존재하는 번호인지 확인할 것
		board.setBno(12L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("user00");
		assertTrue(1 == mapper.update(board));
	}
	
	@Test
	public void testSearch() {
		Criteria cri = new Criteria();
		cri.setKeyword("테스트");
		cri.setType("TC");

		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board));
	}
}
