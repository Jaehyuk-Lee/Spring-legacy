package net.developia.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	private int startPage;
	private int endPage;
	private boolean prev, next;

	private int total;
	private Criteria cri;

	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;

		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		// ceil로 올림을 통해 단순히 /10*10 할 때는 -1처리를 해줘야 했던 부분 없게 함
		this.startPage = this.endPage - 9;

		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

		if (realEnd < this.endPage)
			this.endPage = realEnd;

		this.prev = this.startPage > 1;

		this.next = this.endPage < realEnd;
	}
}
