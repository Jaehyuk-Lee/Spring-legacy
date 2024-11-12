<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Tables</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Board List Page
                <button id='regBtn' type="button" class="btn btn-xs pull-right">Register New Board</button>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                    <thead>
                        <tr>
                            <th>#번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>수정일</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${list}" var="board">
                    	<tr>
                    		<td><c:out value="${board.bno}" /></td>
                    		<td><a href="/board/get?bno=${board.bno}">${board.title}</a></td>
                    		<td><c:out value="${board.writer}" /></td>
                    		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}" /></td>
                    		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}" /></td>
                    	</tr>
                    	</c:forEach>
                    </tbody>
                </table>
                <div class='pull-right'>
                	<ul class="pagination">
                		<c:if test="${pageMaker.prev }" >
                			<li class="paginate_button previous"><a href="#">Previous</a></li>
                		</c:if>
                		
                		<c:forEach var="num" begin="${pageMaker.startPage }"
                			end="${pageMaker.endPage}">
                			<li class="paginate_button"><a href="#">${num}</a></li>
                		</c:forEach>
                		
                		<c:if test="${pageMaker.next }" >
                			<li class="paginate_button next"><a href="#">Next</a></li>
                		</c:if>
                	</ul>
                </div>
            </div>
            <!-- end panel-body -->
        </div>
        <!-- end panel -->
    </div>
</div>
<!-- /.row -->
<script>
	$(()=> {
		let result = '<c:out value="${result}"/>';

		checkModal(result);
		
		history.replaceState({}, null, null);
		
		function checkModal() {
			if (result === '' || history.state) return;
			if (parseInt(result) > 0) {
				$('.modal-body').html('게시물 ' + parseInt(result) + '번이 등록되었습니다.');
			}
			else if (result == 'modify-success') {
				$('.modal-body').html('수정되었습니다.');
			}
			else if (result == 'remove-success') {
				$('.modal-body').html('삭제되었습니다.');
			}
			$('#myModal').modal("show");
		}
		
		$('#regBtn').on("click", () => {
			self.location = "/board/register";
		});
	});
</script>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<%@ include file="../includes/footer.jsp" %>