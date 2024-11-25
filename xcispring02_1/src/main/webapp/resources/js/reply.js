/*
reply module

reply

rno : 댓글 번호
bno : 글 번호
reply : 댓글 내용
replyDate : UTC 기준 등록 시간
replyer : 댓글 작성자
updateDate : UTC 기준 마지막 수정 시간
*/


console.log("Reply Module.......");

let replyService = (function () {
	function add(reply, callback) {
		console.log("add reply......");
		$.ajax({
			type: 'post',
			url: '/replies/new',
			data: JSON.stryingify(reply),
			ContentType: "application/json; charset=utf-8",
			success: function (result, status, xhr) {
				if (callback)
					callback(result);
			},
			error: function (xhr, status, err) {
				if (error)
					error(err);
			}
		});
	}

	function getList(param, callback, error) {
		console.log("get list reply......");
		let bno = param.bno;
		console.log("bno: " + bno);
		let page = param.page || 1;
		$.getJSON(`/replies/pages/${bno}/${page}.json`,
			function (data) {
				if (callback) {
					callback(data);
				}
			}).fail(function (xhr, status, err) {
				if (error) {
					error();
				}
			});
	};

	function remove(rno, callback, error) {
		console.log("remove reply......");
		$.ajax({
			type: 'delete',
			url: `/replies/${rno}`,
			success: function (deleteResult, status, xhr) {
				if (callback) {
					callback(deleteResult);
				}
			}
		})
	};

	function update(reply, callback, error) {
		console.log("RNO: " + reply.rno);

		$.ajax({
			type: 'put',
			url: `/replies/${reply.rno}`,
			data: JSON.stringify(reply),
			contentType: "application/json; charset=utf-8",
			success: function (result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error: function (xhr, status, err) {
				if (error) {
					error(err);
				}
			}
		});
	}

	function get(rno, callback, error) {
		console.log("get reply......");
		$.get(`/replies/${rno}.json`, function (result) {
			if (callback) {
				callback(result);
			}
		}).fail(function (xhr, status, err) {
			if (error) {
				error();
			}
		});
	}

	function displayTime(timeValue) {
		let today = new Date();
		let gap = today.getTime() - timeValue;
		// change to localtime
		let dateObj = new Date(timeValue - new Date().getTimezoneOffset() * 60 * 1000);
		let str = "";
	
		if (gap < (1000 * 60 * 60 * 24)) {
			let hh = dateObj.getHours();
			let mi = dateObj.getMinutes();
			let ss = dateObj.getSeconds();
			return [(hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi, ':', (ss > 9 ? '' : '0') + ss].join('');
		} else {
			let yy = dateObj.getFullYear();
			let mm = dateObj.getMonth() + 1;
			let dd = dateObj.getDate();
			return [yy, '/', (mm > 9 ? '' : '0') + mm, '/', (dd > 9 ? '' : '0') + dd].join('');
		}
	}

	return { add: add, getList: getList, remove: remove, update: update, get: get, displayTime: displayTime };
})();