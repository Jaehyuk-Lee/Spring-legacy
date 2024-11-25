<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
								Board Read Page
							</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="form-group">
									<label>Bno</label> <input class="form-control" name="bno"
										value='<c:out value="${board.bno}" />' readonly="readonly">
								</div>
								<div class="form-group">
									<label>Title</label> <input class="form-control" name="title"
										value='<c:out value="${board.title}" />' readonly="readonly">
								</div>
								<div class="form-group">
									<label>Text area</label> <textarea class="form-control" rows="3" name="content"
										readonly="readonly"><c:out value="${board.content}" /></textarea>
								</div>
								<div class="form-group">
									<label>Writer</label> <input class="form-control" name="writer"
										value='<c:out value="${board.writer}" />' readonly="readonly">
								</div>
								<button data-oper='modify' class="btn btn-default">Modify</button>
								<button data-oper='list' class="btn btn-default">List</button>
							</div>
							<!-- end panel-body -->
						</div>
						<!-- end panel -->
					</div>
				</div>
				<!-- /.row -->
				<div class='row'>
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<i class="fa fa-comments fa-fw"></i> Reply
							</div>
							<div class="panel-body">
								<ul class="chat">
									<li class="left clearfix" data-rno='12'>
										<div>
											<div class="header">
												<strong class="primary-font">user00</strong>
												<small class="pull-right text-muted">2018-01-01 13:!3</small>
											</div>
											<p>Good job!</p>
										</div>
									</li>
									<!-- end reply -->
								</ul>
								<!-- ./ end ul -->
							</div>

						</div>
						<!-- end panel -->
					</div>
				</div>
				<!-- /.row -->
				<form id='operForm' action="/board/modify" method="get">
					<input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno }"/>'>
					<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum }"/>'>
					<input type='hidden' name='amount' value='<c:out value="${cri.amount }"/>'>
					<input type='hidden' name='type' value='<c:out value="${cri.type }"/>'>
					<input type='hidden' name='keyword' value='<c:out value="${cri.keyword }"/>'>
				</form>
				<script src="/resources/js/reply.js"></script>
				<script>
					let bnoValue = '<c:out value="${board.bno}"/>';
					console.log("${board.bno}");

					/*
					replyService.getList({bno:bnoValue, page:1}, function(list) {
						console.log(list);
						for (let i=0, len=list.length||0;i<len;i++){
							console.log(list[i]);
						}
						
					});*/

					$().ready(() => {
						let replyUL = $(".chat");

						showList(1);

						function showList(page) {
							replyService.getList({ bno: bnoValue, page: page || 1 }, function (list) {
								let str = "";
								console.log(list);
								if (list == null || list.length == 0) {
									replyUL.html("");
									return;
								}
								list = list.map(item => ({
									...item,
									replyDate: replyService.displayTime(item.replyDate)
								}));
								for (let i = 0, len = list.length || 0; i < len; i++) {
									str += `<li class='left clearfix' data-rno='\${list[i].rno}'>
<div><div class='header'><strong class='primary-font'>\${list[i].replyer}</strong>
<small class='pull-right text-muted'>\${list[i].replyDate}</small></div>
<p>\${list[i].reply}</p></div></li>`;
								}
								replyUL.html(str);
								console.log(str);
							}) // end function
						} // end showList
					});
					$().ready(() => {
						$('button[data-oper="modify"]').on("click", function (e) {
							let operForm = $("#operForm");
							operForm.submit();
						});
						$('button[data-oper="list"]').on("click", function (e) {
							let operForm = $("#operForm");
							operForm.attr("action", "/board/list");
							operForm.submit();
						});
					});
				</script>
				<%@ include file="../includes/footer.jsp" %>