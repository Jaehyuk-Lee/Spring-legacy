package net.developia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.developia.domain.BoardVO;
import net.developia.domain.Criteria;
import net.developia.mapper.BoardMapper;

@Log4j
@Service
@AllArgsConstructor // 자동으로 모든 필드값에 대한 생성자를 생성 - Constructor Injection을 하겠다는 말
public class BoardServiceImpl implements BoardService{
	// @AllArgConstructor로 인해 자동으로 이 필드값을 매개변수로 받는 생성자를 생성해줌.
	private BoardMapper mapper;

	@Override
	public void register(BoardVO board) throws Exception {
		try {
			log.info("register..." + board);
			mapper.insertSelectKey(board);
		} catch (Exception e) {
			log.error(e.getMessage()); // 관리자 확인용 에러 로그
			throw e; // 컨트롤러에서 유저에게 에러메시지를 줄 때 에러가 헨들링되지 않은 것으로 넘겨주기 위해 씀
		} // 유저에 대한 메시지는 컨트롤러에서 처리해주는 것이 좋음
	}

	@Override
	public BoardVO get(Long bno) throws Exception {
		log.info("get....." + bno);
		try {
			BoardVO board = mapper.read(bno);
			if (board == null) throw new RuntimeException(bno + "번 게시물이 없음");
			return board;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean modify(BoardVO board) throws Exception {
		log.info("modify...." + board);
		try {
			if (mapper.update(board) == 0) throw new RuntimeException(board.getBno() + "번 게시물이 수정되지 않음");
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean remove(Long bno) throws Exception {
		log.info("remove...." + bno);
		try {
			if (mapper.delete(bno) == 0) throw new RuntimeException(bno +  "번 게시물이 삭제되지 않음");
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public List<BoardVO> getList() throws Exception {
		try {
			return mapper.getList();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public List<BoardVO> getList(Criteria cri) throws Exception {
		try {
			return mapper.getListWithPaging(cri);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
}
